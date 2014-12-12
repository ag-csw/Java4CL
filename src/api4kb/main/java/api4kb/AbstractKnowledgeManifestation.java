package api4kb;

import java.util.HashMap;

import lazykb.LazyInitializing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKnowledgeManifestation<T> implements
		KnowledgeManifestation<T>, LazyInitializing<T> {
	// Initializing-only constructor
	public AbstractKnowledgeManifestation(KRRDialectType<T> dialectType) {
		this.dialectType = dialectType;
		mapEncoding = new HashMap<CodecSystem<T, ?>, AbstractKnowledgeEncoding<T, ?>>();
	}

	// Wrapper-based constructor
	public AbstractKnowledgeManifestation(T value, KRRDialectType<T> dialect) {
		//TODO add a validation flag to indicate that
		// value should be checked for validity relative to dialect
		this.value = value;
		this.dialectType = dialect;
		mapEncoding = new HashMap<CodecSystem<T, ?>, AbstractKnowledgeEncoding<T, ?>>();
	}

	// Lazy lowering constructor - argument is expression and dialect
	public AbstractKnowledgeManifestation(AbstractKnowledgeExpression expression,
			KRRDialectType<T> dialectType) throws DialectTypeIncompatibleException {
		if (expression.getLanguage().equals(dialectType.getDialect().getLanguage())) {
			this.expression = expression;
			this.dialectType = dialectType;
			mapEncoding = new HashMap<CodecSystem<T, ?>, AbstractKnowledgeEncoding<T, ?>>();
		} else {
			throw new DialectTypeIncompatibleException();
		}
	}

	// Lazy lifting constructor - argument is an Encoding
	public <S> AbstractKnowledgeManifestation(AbstractKnowledgeEncoding<T, S> encoding) {
		this.dialectType = encoding.getDialectType();
		mapEncoding = new HashMap<CodecSystem<T, ?>, AbstractKnowledgeEncoding<T, ?>>();
		encodingSafePut(encoding.getCodecSystem(), encoding);
	}

	// protected fields
	protected T value;
	protected final KRRDialectType<T> dialectType;
	protected Configuration<?> configuration;
	protected final HashMap<CodecSystem<T, ?>, AbstractKnowledgeEncoding<T, ?>> mapEncoding;
	protected AbstractKnowledgeExpression expression;
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public T getValue() {
		if (value == null) {
			if (!(expression == null)) {
				try {
					// type compatibility is ensured within the
					// expression object, making the unchecked cast safe
					T exprvalue = expression.manifest(dialectType).getValue();
					value = exprvalue;
				} catch (DialectTypeIncompatibleException e) {
					assert false : "Faulty lazy lowering constructor";
				}
			} else {
				if (!mapEncoding.isEmpty()) {
					synchronized (mapEncoding) {
						CodecSystem<T, ?> system = mapEncoding.keySet()
								.iterator().next();
						value = mapEncoding.get(system).decode().getValue();
					}
				} else {
					assert false : "AbstractKnowledgeExpression is in inconsistent state";
				}

			}
		}
		return value;
	}

	@Override
	public KRRDialectType<T> getDialectType() {
		return dialectType;
	}

	// provides a canonical String representation of the Manifestation based on the
	// default configuration
	@Override
	public String toString() {
		return this.getValue().toString();
	}


	// lowering method accepts a parameter indicating the encoding system
	// with generic T for the format (e.g. ByteSequence, byte{}, ...)
	public <S> AbstractKnowledgeEncoding<T, S> encode(CodecSystem<T, S> system)
			throws EncodingSystemIncompatibleException {
		if (!mapEncoding.containsKey(system)) {
			AbstractKnowledgeEncoding<T, S> encoding = evalEncoding(system);
			encodingSafePut(system, encoding);
			return encoding;
		} else {
			// TODO type compatibility should be checked before caching
			// so that the type case is safe
			@SuppressWarnings("unchecked")
			AbstractKnowledgeEncoding<T, S> encoding = (AbstractKnowledgeEncoding<T, S>) mapEncoding.get(system);
			return encoding;
		}
	}

	// non-public lowering evaluation method
	protected abstract <S> AbstractKnowledgeEncoding<T, S> evalEncoding(
			CodecSystem<T, S> system) throws EncodingSystemIncompatibleException;

	// lifting method
	public AbstractKnowledgeExpression parse() {
		if (expression == null) {
			expression = evalExpression();
			return expression;
		} else {
			return expression;
		}

	}

	// nonpublic lifting evaluation method
	protected abstract AbstractKnowledgeExpression evalExpression();
	
	
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
	
	<S> void encodingSafePut(CodecSystem<T, S> system, AbstractKnowledgeEncoding<T,S> encoding) {
		mapEncoding.put(system, encoding);
	}

	// TODO
	//getter for configuration describing the transformation from the
	// canonical value ( of type T) to the actual value as returned by getValue()
	// The format of the configuration is represented as a wildcard because
	// in general it will depend on the dialect.
	// For example, an XML-based dialect would have an XSLT-based configuration.
	//public Configuration<?> getConfiguration() {
	//	// check the cache
	//	if (configuration == null) {
			// TODO create and cache
	//	}
	//	return configuration;
	//}

}
