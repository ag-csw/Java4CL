package api4kb;

public abstract class AbstractKRRDialect {
	
	public abstract <T> KRRDialectType<T> getDialectType() throws DialectTypeIncompatibleException;

}
