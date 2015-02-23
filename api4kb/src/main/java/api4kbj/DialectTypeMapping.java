package api4kbj;

public interface DialectTypeMapping<T, R> extends Mapping<T, R> {
	
	 KRRDialectType<T> startType();
	 KRRDialectType<R> endType();

}
