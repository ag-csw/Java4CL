package api4kbj;

import functional.Some;

public interface FocusedLanguageEnvironment extends
		KRRLanguageEnvironment {


	/**
	 * Return the focus language of the focused environment.
	 * 
	 * @return the focus language of the environment
	 * @see #isFocused()
	 */
	@Override
	Some<? extends KRRLanguage> focusMember();

}
