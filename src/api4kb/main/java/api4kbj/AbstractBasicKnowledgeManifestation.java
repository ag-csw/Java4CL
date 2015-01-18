package api4kbj;

public abstract class AbstractBasicKnowledgeManifestation implements
		BasicKnowledgeManifestation {

	// Wrapper-based constructor
	public <T> AbstractBasicKnowledgeManifestation(T value,
			KRRDialectType<T> dialectType) {
		// TODO add a validation flag to indicate that
		// value should be checked for validity relative to dialect
		this.dialect = dialectType.dialect();
		this.wrappedDialectType = dialectType;
		this.wrappedValue = value;
	}

	private final KRRDialect dialect;
	private final KRRDialectType<?> wrappedDialectType;
	protected final Object wrappedValue;

	@Override
	public KRRDialect dialect() {
		return dialect;
	}

	@Override
	public <T> T build(KRRDialectType<T> dialectType) {
		if (dialectType.equals(wrappedDialectType)) {
			return (T) wrappedValue;
		}
		throw new IllegalArgumentException("Requested dialect type"
				+ dialectType + " is not supporte");
	}

}
