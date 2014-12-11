package api4kb;

import java.util.HashMap;

import lazykb.LazyInitializing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKnowledgeManifestation<T> implements
		KnowledgeManifestation<T>, LazyInitializing<T> {
	// Initializing-only constructor
	public AbstractKnowledgeManifestation(KRRDialect<T> dialect) {
		this.dialect = dialect;
		mapEncoding = new HashMap<EncodingSystem<T, ?>, AbstractKnowledgeEncoding<T, ?>>();
	}

	// Wrapper-based constructor
	public AbstractKnowledgeManifestation(T value, KRRDialect<T> dialect) {
		//TODO add a validation flag to indicate that
		// value should be checked for validity relative to dialect
		this.value = value;
		this.dialect = dialect;
		mapEncoding = new HashMap<EncodingSystem<T, ?>, AbstractKnowledgeEncoding<T, ?>>();
	}

	// Lazy lowering constructor - argument is expression and dialect
	public AbstractKnowledgeManifestation(AbstractKnowledgeExpression expression,
			KRRDialect<T> dialect) throws DialectIncompatibleException {
		if (expression.getLanguage().equals(dialect.getLanguage())) {
			this.expression = expression;
			this.dialect = dialect;
			mapEncoding = new HashMap<EncodingSystem<T, ?>, AbstractKnowledgeEncoding<T, ?>>();
		} else {
			throw new DialectIncompatibleException();
		}
	}

	// Lazy lifting constructor - argument is an Encoding
	public <S> AbstractKnowledgeManifestation(AbstractKnowledgeEncoding<T, S> encoding) {
		this.dialect = encoding.getDialect();
		mapEncoding = new HashMap<EncodingSystem<T, ?>, AbstractKnowledgeEncoding<T, ?>>();
		encodingSafePut(encoding.getEncodingSystem(), encoding);
	}

	// protected fields
	protected T value;
	protected final KRRDialect<T> dialect;
	protected Configuration<?> configuration;
	protected final HashMap<EncodingSystem<T, ?>, AbstractKnowledgeEncoding<T, ?>> mapEncoding;
	protected AbstractKnowledgeExpression expression;
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public T getValue() {
		if (value == null) {
			if (!(expression == null)) {
				try {
					// type compatibility is ensured within the
					// expression object, making the unchecked cast safe
					T exprvalue = (T) expression.manifest(dialect).getValue();
					value = exprvalue;
				} catch (DialectIncompatibleException e) {
					assert false : "Faulty lazy lowering constructor";
				}
			} else {
				if (!mapEncoding.isEmpty()) {
					synchronized (mapEncoding) {
						EncodingSystem<T, ?> system = mapEncoding.keySet()
								.iterator().next();
						value = (T) mapEncoding.get(system).decode().getValue();
					}
				} else {
					assert false : "AbstractKnowledgeExpression is in inconsistent state";
				}

			}
		}
		return value;
	}

	@Override
	public KRRDialect<T> getDialect() {
		return dialect;
	}

	@Override
	public String toString() {
		return this.getValue().toString();
	}

	@Override
	public Configuration<?> getConfiguration() {
		// check the cache
		if (configuration == null) {
			// TODO create and cache
		}
		return configuration;
	}

	// public encode implemented
	@Override
	public <S> AbstractKnowledgeEncoding<T, S> encode(EncodingSystem<T, S> system)
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
			EncodingSystem<T, S> system) throws EncodingSystemIncompatibleException;

	// public parse implemented
	@Override
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
	
	
	@Override
	public void clearEncode(EncodingSystem<T, ?> system) {
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

	@Override
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
			for (EncodingSystem<T, ?> system : mapEncoding.keySet()) {
				clearEncode(system);
			}
		}
		clearParse();
	}
	
	<S> void encodingSafePut(EncodingSystem<T, S> system, AbstractKnowledgeEncoding<T,S> encoding) {
		mapEncoding.put(system, encoding);
	}


}
