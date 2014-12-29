package api4kbj;

/**
 * Interface for API4KB knowledge resources, which are immutable knowledge
 * sources.
 * 
 * @author taraathan
 *
 */
public interface KnowledgeResource extends ImmutableSource, KnowledgeSource {

	/**
	 * Returns the abstraction level.
	 * 
	 * @return the abstraction level
	 * @see KnowledgeAsset, KnowledgeExpression, KnowledgeManifestation,
	 *      KnowledgeEncoding, KnowledgeItem
	 */
	KnowledgeSourceLevel level();

	/**
	 * Returns <tt>true</tt> if this knowledge resource is basic (not
	 * structured).
	 * 
	 * @return <tt>true</tt> if this knowledge resource is basic (not
	 *         structured)
	 * @see BasicKnowledgeResource, StructuredKnowledgeResource
	 */
	Boolean isBasic();

	/**
	 * Returns the default environment, which must be compatible with the
	 * knowledge resource.
	 * 
	 * @return the default environment
	 */
	ImmutableEnvironment defaultEnvironment();

	/**
	 * Returns the default language, which must be contained in the default
	 * environment.
	 * 
	 * @return the default language
	 */
	KRRLanguage defaultLanguage();

	/**
	 * Returns the default dialect, which must be a dialect of the default
	 * language.
	 * 
	 * @return the default dialect
	 */
	KRRDialect defaultDialect();

	/**
	 * Returns the default dialect type, which must be a dialect type of the
	 * default dialect.
	 * 
	 * @return the default dialect type
	 */
	KRRDialectType<?> defaultDialectType();

	/**
	 * Returns the default codec system, whose decoding type must be the same as
	 * (or extend) the generic type parameter of the default dialect type.
	 * 
	 * @return the default codec system
	 */
	CodecSystem<?, ?> defaultCodecSystem();

	/**
	 * Return the default receiver.
	 * 
	 * @return the default receiver
	 */
	Object defaultReceiver();

	/**
	 * Return the default sender.
	 * 
	 * @return the default sender
	 */
	Object defaultSender();

}
