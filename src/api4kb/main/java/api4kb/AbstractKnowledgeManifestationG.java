package api4kb;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKnowledgeManifestationG<T> extends
		AbstractKnowledgeResource implements KnowledgeManifestationG<T> {
	// Initializing-only constructor
	public AbstractKnowledgeManifestationG(AbstractKRRDialectType<T> dialectType) {
		LOG.debug("Starting initializing constructor for dialect type: {}", dialectType);
		this.dialectType = dialectType;
	}

	// Wrapper-based constructor
	public AbstractKnowledgeManifestationG(T value,
			AbstractKRRDialectType<T> dialectType) {
		// TODO add a validation flag to indicate that
		// value should be checked for validity relative to dialect
		this(dialectType);
		this.value = value;
	}
	
	// Lazy lifting constructor - argument is an Encoding
	public <S> AbstractKnowledgeManifestationG(
			AbstractKnowledgeEncoding<T, S> encoding) {
		this(encoding.getDialectType());
		LOG.debug(
				"Starting lazy lifting expression construtor with encoding: {}",
				encoding);
        initialValue = encoding;
		// TODO make memoization of encode optional?
		encodingSafePut(encoding);
	}

	// Lazy lowering constructor - argument is expression and dialect
	public AbstractKnowledgeManifestationG(
			AbstractKnowledgeExpression expression,
			AbstractKRRDialectType<T> dialectType)
			throws DialectTypeIncompatibleException {
		this(dialectType);
		LOG.debug("Starting lazy lowering manifestation constructor");
		if (!expression.getLanguage().equals(dialectType.getLanguage())) {
			throw new DialectTypeIncompatibleException("Requested dialect type is not compatible with the language.");
		}
		LOG.debug("Language compatibility verified");
		initialValue = expression;
		this.expression = expression;
	}


	// protected fields
    // final properties 
	protected final AbstractKRRDialectType<T> dialectType;
	// mutable internal properties
	protected T value;
	// TODO configuration not yet untilized
	protected Configuration<?> configuration;
	// cache for lifting and lowering methods
	protected final HashMap<AbstractCodecSystem<T, ?>, AbstractKnowledgeEncoding<T, ?>> mapEncoding = new HashMap<AbstractCodecSystem<T, ?>, AbstractKnowledgeEncoding<T, ?>>();
	protected AbstractKnowledgeExpression expression;
	//
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public KnowledgeSourceLevel getLevel() {
		LOG.debug("Getting level: {}", level);
		return level;
	}

	@Override
	public AbstractKRRDialectType<T> getDialectType() {
		return dialectType;
	}
	
	@Override
	public Class<T> getType(){
		return dialectType.getType();
	}


	@Override
	public T getValue() {
		if (value != null) {
			return value;
		}
		if (expression != null) {
			try {
				value = expression.manifest(dialectType).getValue();
				return value;
			} catch (DialectTypeIncompatibleException e) {
				assert false : "Faulty lazy lowering constructor";
			}
		}
		if ((initialValue != null) && (initialValue.getLevel() == KnowledgeSourceLevel.ENCODING)) {
			value = ((AbstractKnowledgeEncoding<T, ?>) initialValue).decode().getValue();
			return value;
		}
		if (!mapEncoding.isEmpty()) {
			synchronized (mapEncoding) {
				CodecSystem<T, ?> system = mapEncoding.keySet().iterator()
						.next();
				value = mapEncoding.get(system).decode().getValue();
			}
		} 
		try {
			value = eval();
		} catch (DialectTypeIncompatibleException e) {
			assert false : "Faulty component constructor";
		}
		return value;
	}
	
	protected abstract T eval() throws DialectTypeIncompatibleException;


	// default lowering method returns an encoding in the default codec system
	// for that dialect type
	public AbstractKnowledgeEncoding<T, byte[]> encode()
			throws DialectTypeIncompatibleException, EncodingSystemIncompatibleException {
		return (AbstractKnowledgeEncoding<T, byte[]>) encode(dialectType.defaultSystem());
	}

	// lowering method accepts a parameter indicating the encoding system
	// with generic T for the format (e.g. ByteSequence, byte{}, ...)
	public <S> AbstractKnowledgeEncoding<T, S> encode(AbstractCodecSystem<T, S> system)
			throws EncodingSystemIncompatibleException {
		LOG.debug("Starting encode of manifestation");
		LOG.debug("  Codec system: {}", system);
		LOG.debug("  Dialect of the manifestation: {}", dialectType);
		LOG.debug("  Language of the expression: {}", dialectType.getLanguage());
		// TODO consider replacing level check with instanceof
		if ((initialValue != null) && (initialValue.getLevel() == KnowledgeSourceLevel.ENCODING)){
			@SuppressWarnings("unchecked")
			AbstractKnowledgeEncoding<T, ?> encoding = (AbstractKnowledgeEncoding<T, ?>) initialValue;
			LOG.debug("Found cached intial value for encoding: {}", encoding);
			if (encoding.getCodecSystem() == system){
			  LOG.debug("Using cached intial value");
			  return (AbstractKnowledgeEncoding<T, S>) encoding;
			}
		}
		if (mapEncoding.containsKey(system)) {
			LOG.debug("Found cached encoding for requested codec");
			@SuppressWarnings("unchecked")
			AbstractKnowledgeEncoding<T, S> encoding = (AbstractKnowledgeEncoding<T, S>) mapEncoding.get(system);
			LOG.debug("Using cached encoding: {}", encoding);
			return encoding;
		}
		LOG.debug("Found no cached encoding for: {}", system);
		// Last resort: create a new expression
		return newEncoding(system);

	}

	// eager lowering
	protected abstract <S> AbstractKnowledgeEncoding<T,S> newEncoding(AbstractCodecSystem<T, S> system);
	//	return new AbstractKnowledgeEncoding<T, S>(dialectType, system){};

	// lifting method
	public AbstractKnowledgeExpression parse() {
		LOG.debug("Starting parse");
		if (expression != null) {
			LOG.debug("Found cached value for expression: {}", expression);
			return expression;
		} 
		LOG.debug("Found no cached expression");
		// Last resort: create a new expression
		return newExpression();
	}

	// eager lifting
	protected abstract AbstractKnowledgeExpression newExpression();
	//	return new AbstractKnowledgeExpression(this){};



	<S> void encodingSafePut(AbstractKnowledgeEncoding<T, S> encoding) {
		mapEncoding.put(encoding.getCodecSystem(), encoding);
	}

	// verify that some other equivalent property has been set
	// before forgetting initial value, to avoid leaving object
	// in inconsistent "state".
	@Override
	public void clearInitialValue() {
		if ((value != null) | (!mapEncoding.isEmpty()) | (expression != null)) {
			super.unsafeClearInitialValue();
		}
	}

	// clear memoization cache of the manifest method for the particular dialect
	public void clearEncode(CodecSystem<T, ?> system) {
		// If the encoding cache does not contain this key, do nothing
		synchronized (mapEncoding) {
			if ((mapEncoding.containsKey(system))) {
				// Before removing this encoding entry, be sure that this
				// will not put the object into an invalid state
				if ((value == null) && (expression == null)
						&& (mapEncoding.size() == 1)) {
					value = this.getValue();
				}
				mapEncoding.remove(system);
			}
		}
	}

	// clear memoization cache of the parse method
	public void clearParse() {
		// If the expression cache is empty, do nothing
		synchronized (mapEncoding) {
			if (expression != null) {
				// Before clearing the expression cache, be sure that this
				// will not put the object into an invalid state
				if ((value == null) && mapEncoding.isEmpty()) {
					value = this.getValue();
				}
				expression = null;
			}
		}
	}

	@Override
	public void clear() {
		synchronized (mapEncoding) {
			for (CodecSystem<T, ?> system : mapEncoding.keySet()) {
				clearEncode(system);
			}
		}
		clearParse();
	}

	// TODO
	// getter for configuration describing the transformation from the
	// canonical value ( of type T) to the actual value as returned by
	// getValue()
	// The format of the configuration is represented as a wildcard because
	// in general it will depend on the dialect.
	// For example, an XML-based dialect would have an XSLT-based configuration.
	// public Configuration<?> getConfiguration() {
	// // check the cache
	// if (configuration == null) {
	// TODO create and cache
	// }
	// return configuration;
	// }

}
