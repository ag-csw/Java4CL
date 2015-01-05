package api4kbj;

public interface EnvironmentConfigured {

	/**
	 * Returns the default environment, which must be compatible with the
	 * knowledge resource.
	 * 
	 * @return the default environment
	 */
	FocusedImmutableEnvironment defaultEnvironment();

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
	 * Returns the default format, which must be a format of the default dialect
	 * .
	 * 
	 * @return the default format
	 */
	KRRFormat defaultFormat();

	/**
	 * Returns the default format type, which must be a format type of the
	 * default format.
	 * 
	 * @return the default format type
	 */
	KRRFormatType<?> defaultFormatType();

	/**
	 * Returns the default codec system, which must be a codec system of the
	 * default format, and whose decoding type must be the same as (or extend)
	 * the generic type parameter of the default dialect type.
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