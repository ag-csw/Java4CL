package api4kb;

public abstract class AbstractKRRDialect<T> implements KRRDialect<T> {

	public AbstractKRRDialect(String name, KRRLanguage lang) {
		this.name = name;
		this.lang = lang;
	}

	private final String name;
	private final KRRLanguage lang;
	
	public String getName(){
		return name;
	}
	public KRRLanguage getLanguage(){
		return lang;
	}


}
