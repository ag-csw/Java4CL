package api4kbj;

public interface KRRFormatType<S> {

	String name();

	Class<S> type();

	KRRFormat format();

	default KRRDialect dialect() {
		return format().dialect();
	}

	default KRRLanguage language() {
		return format().language();
	}

}
