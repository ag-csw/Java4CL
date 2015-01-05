package api4kbj;

public interface KRRDialectType<T> {

	String name();

	Class<T> type();

	KRRDialect dialect();

	default KRRLanguage language() {
		return dialect().language();
	}

}
