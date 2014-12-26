package api4kb;

public interface KnowledgeResource extends ImmutableSource, KnowledgeSource {

	// returns the abstraction level
	KnowledgeSourceLevel getLevel();

	// get the default environment
	ImmutableEnvironment getDefaultEnvironment();

	// get the default language
	KRRLanguage getDefaultLanguage();

	// get the default dialect
	KRRDialect getDefaultDialect();

	// get the default dialect type
	KRRDialectType<?> getDefaultDialectType();

	// get the default codec system
	CodecSystem<?, ?> getDefaultCodecSystem();

	// get the default receiver
	Object getDefaultReceiver();

	// get the default sender
	Object getDefaultSender();

}
