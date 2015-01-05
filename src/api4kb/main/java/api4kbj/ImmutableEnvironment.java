package api4kbj;

import functional.Option;

/**
 * Interface for immutable environments of KRR languages.
 * 
 * @author taraathan
 *
 */
public interface ImmutableEnvironment extends Immutable {

	/**
	 * Return the collection of KRR languages contained in the environment.
	 * 
	 * @return the collection of KRR languages contained in the environment
	 */
	Iterable<KRRLanguage> languages();

	/**
	 * Return true if the KRR language <tt>lang</tt> is contained in the
	 * environment.
	 * 
	 * @param lang
	 *            a KRR language
	 * @return true if <tt>lang</tt> is contained in the environment
	 */
	boolean containsLanguage(KRRLanguage lang);

	/**
	 * Return <tt>true</tt> if the environment has a focus. If <tt>true</tt>,
	 * the method {@link #focusLanguage()} must return a Some, otherwise it must
	 * return a None.
	 * 
	 * @return <tt>true</tt> if the environment has a focus
	 */
	boolean isFocused();

	/**
	 * Return the translation of a knowledge expression <tt>expression</tt> into
	 * the target KRR language <tt>language</tt>.
	 * 
	 * @param expression
	 *            is the input expression
	 * @param language
	 *            is the target language
	 * @return the translation of <tt>expression</tt> into <tt>language</tt>
	 */
	KnowledgeExpression translate(KnowledgeExpression expression,
			KRRLanguage language);

	/**
	 * Return the default KRR language of the environment.
	 * 
	 * @return the default KRR language of the environment
	 */
	KRRLanguage defaultLanguage();

	/**
	 * Return the optional focus language of the environment.
	 * 
	 * @return the optional focus language of the environment
	 * @see #isFocused()
	 */
	Option<KRRLanguage> focusLanguage();

	/**
	 * Return <tt>true</tt> if this environment contains the environment
	 * <tt>other</tt>.
	 * 
	 * @param other
	 *            an environment
	 * @return <tt>true</tt> if this environment contains <tt>other</tt>
	 */
	boolean contains(ImmutableEnvironment other);

}
