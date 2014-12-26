package api4kbj;

public interface KRRDialectType<T> {

	public String getName();

	public KRRDialect getDialect();

	public KRRLanguage getLanguage();

	public Class<T> getType();

	public CodecSystem<T, ?> defaultSystem();

}
