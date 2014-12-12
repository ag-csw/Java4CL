package api4kb;

public abstract class AbstractKRRDialect implements KRRDialect {
	
	private String name;
	private AbstractKRRLanguage lang;

	public AbstractKRRDialect(String name, AbstractKRRLanguage lang) {
		this.name = name;
		this.lang = lang;
	}

	@Override
	public String getName(){
		return name;
	}
	@Override
	public AbstractKRRLanguage getLanguage(){
		return lang;
	}

}
