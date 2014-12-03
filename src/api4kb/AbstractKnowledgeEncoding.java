package api4kb;

public abstract class AbstractKnowledgeEncoding<T, S> implements
		KnowledgeEncoding<T, S> {
	// Initializing-only constructor
	public AbstractKnowledgeEncoding(KRRDialect<T> dialect,
			EncodingSystem<T, S> system) {
		this.dialect = dialect;
		this.system = system;
	}

	// Wrapper-based constructor
	public AbstractKnowledgeEncoding(S stream, KRRDialect<T> dialect,
			EncodingSystem<T, S> system) {
		this.stream = stream;
		this.dialect = dialect;
		this.system = system;
	}

	// Lazy lowering constructor - argument is manifestation and encoding system
	public AbstractKnowledgeEncoding(KnowledgeManifestation<T> manifestation,
			EncodingSystem<T, S> system) {
		    this.manifestation = manifestation;
			this.dialect = manifestation.getDialect();
			this.system = system;
	}

	// Lazy lifting constructor - argument is a KnowledgeItem
	public <R> AbstractKnowledgeEncoding(KnowledgeItem<T, S, R> input) {
		this.dialect = input.getDialect();
		this.system = input.getEncodingSystem();
		this.stream = input.run();
	}

	// protected fields
	protected S stream;
	protected final KRRDialect<T> dialect;
	protected final EncodingSystem<T, S> system;
	protected KnowledgeManifestation<T> manifestation;

	@Override
	public S getStream() {
		if (stream == null) {
			if (!(manifestation == null)) {
				try {
					S manistream = manifestation.encode(system).getStream();
					stream = manistream;
				} catch (EncodingSystemIncompatibleException e) {
					assert false : "Faulty lazy lowering constructor";
				}
			} else {
				assert false: "Inconsistent state";
			}
		}
		return stream;
	}

	@Override
	public KRRDialect<T> getDialect() {
		return dialect;
	}

	@Override
	public EncodingSystem<T, S> getEncodingSystem() {
		return system;
	}

	@Override
	public String toString() {
		return this.getStream().toString();
	}

	// public reproduce
	@Override
	abstract public <R> KnowledgeItem<T, S, R> reproduce(R destination);

	// public parse implemented
	@Override
	public KnowledgeManifestation<T> decode() {
		if (manifestation == null) {
			manifestation = evalManifestation();
			return manifestation;
		} else {
			return manifestation;
		}

	}

	// nonpublic lifting evaluation method
	protected abstract KnowledgeManifestation<T> evalManifestation();	
	
	@Override
	public void clearDecode() {
		// If the manifestation cache is empty, do nothing
			if (manifestation != null) {
				// Before clearing the expression cache, be sure that this
				// will not put the object into an invalid state
				if (stream == null) {
					stream = getStream();
				}
				manifestation = null;
			}
	}

	@Override
	public void clear() {
		clearDecode();
	}
	

}
