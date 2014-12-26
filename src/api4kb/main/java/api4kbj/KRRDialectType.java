package api4kbj;

public interface KRRDialectType<T> {

	public String name();

	public KRRDialect dialect();

	public KRRLanguage language();

	public Class<T> type();

	public CodecSystem<T, ?> defaultSystem();

}
