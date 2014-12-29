package api4kbj;

public interface KnowledgeResource extends ImmutableSource, KnowledgeSource {

	// returns the abstraction level
	KnowledgeSourceLevel level();

	// check if basic
	Boolean isBasic();

	// get the default environment
	ImmutableEnvironment defaultEnvironment();

	// get the default language
	KRRLanguage defaultLanguage();

	// get the default dialect
	KRRDialect defaultDialect();

	// get the default dialect type
	KRRDialectType<?> defaultDialectType();

	// get the default codec system
	CodecSystem<?, ?> defaultCodecSystem();

	// get the default receiver
	Object defaultReceiver();

	// get the default sender
	Object defaultSender();

}
