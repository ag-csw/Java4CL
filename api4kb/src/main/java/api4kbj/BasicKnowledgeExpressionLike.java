package api4kbj;

public interface BasicKnowledgeExpressionLike extends KnowledgeExpressionLike,
		BasicSource {

	/**
	 * Returns the unique language of the basic knowledge expression.
	 * 
	 * @return the language of the basic knowledge expression.
	 */
	KRRLanguage language();

	@Override
	default boolean usesLanguage(final KRRLanguage language) {
		return language().equals(language);
	}

}