package api4kb;

import lazykb.LazyInitializing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKnowledgeEncoding<T, S> implements
		KnowledgeEncoding<T, S>, LazyInitializing<S> {
	// Initializing-only constructor
	public AbstractKnowledgeEncoding(AbstractKRRDialectType<T> dialectType,
			CodecSystem<T, S> system) {
		this.dialectType = dialectType;
		this.system = system;
	}

	// Wrapper-based constructor
	public AbstractKnowledgeEncoding(S value, AbstractKRRDialectType<T> dialectType,
			CodecSystem<T, S> system) {
		this(dialectType, system);
		this.value = value;
	}

	// Lazy lowering constructor - argument is manifestation and encoding system
	public AbstractKnowledgeEncoding(AbstractKnowledgeManifestation<T> manifestation,
			CodecSystem<T, S> system) {
    		this(manifestation.getDialectType(), system);
		    this.manifestation = manifestation;
	}

	// Lazy lifting constructor - argument is a KnowledgeItem
	public <R> AbstractKnowledgeEncoding(AbstractKnowledgeItem<T, S, R> input) {
		this(input.getDialectType(), input.getCodecSystem());
		// TODO this is not lazy
		this.value = input.read();
	}

	// protected fields
	protected S value;
	protected final AbstractKRRDialectType<T> dialectType;
	protected final CodecSystem<T, S> system;
	protected AbstractKnowledgeManifestation<T> manifestation;
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public KnowledgeSourceLevel getLevel() {
		return level;
	}

	@Override
	public S getValue() {
		// add get for lazy constructor from Item
		if (value == null) {
			if (!(manifestation == null)) {
				try {
					value = manifestation.encode(system).getValue();
				} catch (EncodingSystemIncompatibleException e) {
					assert false : "Faulty lazy lowering constructor";
				}
			} else {
				assert false: "Inconsistent state";
			}
		}
		return value;
	}

	@Override
	public AbstractKRRDialectType<T> getDialectType() {
		return dialectType;
	}

	@Override
	public CodecSystem<T, S> getCodecSystem() {
		return system;
	}

	@Override
	public String toString() {
		return this.getValue().toString();
	}

	// lowering method
	//TODO implement here, as this is not specific to language
	abstract public <R> KnowledgeItem<T, S, R> reproduce(R destination);

	// lifting method
	public AbstractKnowledgeManifestation<T> decode() {
		if (manifestation == null) {
			manifestation = evalManifestation();
			return manifestation;
		} else {
			return manifestation;
		}

	}

	// nonpublic lifting evaluation method
	protected abstract AbstractKnowledgeManifestation<T> evalManifestation();	
	
	// clear memoization cache of the decode method
	public void clearDecode() {
		// If the manifestation cache is empty, do nothing
			if (manifestation != null) {
				// Before clearing the expression cache, be sure that this
				// will not put the object into an invalid state
				if (value == null) {
					value = getValue();
				}
				manifestation = null;
			}
	}

	@Override
	public void clear() {
		clearDecode();
	}
	

}
