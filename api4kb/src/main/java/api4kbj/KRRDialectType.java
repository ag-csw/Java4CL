package api4kbj;

public interface KRRDialectType<T> extends ClassWrapper<T> {

	KRRDialect dialect();

	default KRRLanguage language() {
		return dialect().language();
	}

}
