package api4kbj;

public interface KRRLanguage {

	public String name();

	// TODO
	// public KRRLogic logic();

	public KRRDialect defaultDialect();

	public KRRDialectType<?> defaultDialectType();

	public ImmutableEnvironment defaultEnvironment();

	CodecSystem<?, ?> defaultSystem();

}
