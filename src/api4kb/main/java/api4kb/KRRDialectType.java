package api4kb;

public interface KRRDialectType<T> {

	public String getName();
	
	public KRRDialect getDialect();

	public KRRLanguage getLanguage();

	public Class<T> getType();
	
}
