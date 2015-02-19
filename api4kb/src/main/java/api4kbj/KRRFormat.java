package api4kbj;

public interface KRRFormat {

	String name();

	default KRRLanguage language() {
		return dialect().language();
	}

	KRRDialect dialect();

}
