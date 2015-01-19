package api4kbj;

public interface KRRFormatType<S> extends ClassWrapper<S> {

	KRRFormat format();

	default KRRDialect dialect() {
		return format().dialect();
	}

	default KRRLanguage language() {
		return format().language();
	}

}
