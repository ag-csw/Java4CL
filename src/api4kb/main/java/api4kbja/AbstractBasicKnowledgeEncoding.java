package api4kbja;

import api4kbj.BasicKnowledgeEncoding;
import api4kbj.KRRFormat;
import api4kbj.KRRFormatType;

public abstract class AbstractBasicKnowledgeEncoding implements
		BasicKnowledgeEncoding {

	// Wrapper-based constructor
	public <T> AbstractBasicKnowledgeEncoding(T value,
			KRRFormatType<T> formatType) {
		// TODO add a validation flag to indicate that
		// value should be checked for validity relative to dialect
		this.format = formatType.format();
		this.wrappedFormatType = formatType;
		this.wrappedValue = value;
	}

	private final KRRFormat format;
	private final KRRFormatType<?> wrappedFormatType;
	protected final Object wrappedValue;

	@Override
	public KRRFormat format() {
		return format;
	}

	@Override
	public <T> T build(KRRFormatType<T> formatType) {
		if (formatType.equals(wrappedFormatType)) {
			return (T) wrappedValue;
		}
		throw new IllegalArgumentException("Requested format type" + formatType
				+ " is not supported");
	}

}
