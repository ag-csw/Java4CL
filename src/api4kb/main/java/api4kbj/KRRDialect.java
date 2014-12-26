package api4kbj;

public interface KRRDialect {

	public String getName();

	public KRRLanguage getLanguage();

	public KRRDialectType<?> getDefaultDialectType();

}
