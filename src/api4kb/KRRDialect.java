package api4kb;

public class KRRDialect<T> {

	public KRRDialect(String name, KRRLanguage lang) {
		this.name = name;
		this.lang = lang;
	}

	public final String name;
	public final KRRLanguage lang;
}
