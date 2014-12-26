package api4kbj;

public interface KRRDialect {

	public String name();

	public KRRLanguage language();

	public KRRDialectType<?> defaultDialectType();

}
