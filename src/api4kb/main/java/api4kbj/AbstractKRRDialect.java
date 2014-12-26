package api4kbj;

public abstract class AbstractKRRDialect implements KRRDialect {

	private String name;
	private AbstractKRRLanguage lang;
	private AbstractKRRDialectType<?> defaultDialectType;

	public AbstractKRRDialect(String name, AbstractKRRLanguage lang) {
		this.name = name;
		this.lang = lang;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public AbstractKRRLanguage language() {
		return lang;
	}

	@Override
	public AbstractKRRDialectType<?> defaultDialectType() {
		return defaultDialectType;
	}

}
