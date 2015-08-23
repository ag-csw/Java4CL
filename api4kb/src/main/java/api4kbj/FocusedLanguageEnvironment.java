package api4kbj;

public interface FocusedLanguageEnvironment extends
		KRRLanguageEnvironment, FocusedEnvironment<KRRLanguage, KnowledgeExpressionLike> {


	/**
	 * Return the focus language of the focused environment.
	 * 
	 * @return the focus language of the environment
	 * @see #isFocused()
	 */
	@Override
	KRRLanguage focusMember();

}
