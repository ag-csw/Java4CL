package api4kbj;

import functional.Some;

public interface FocusedImmutableLanguageEnvironment extends
		ImmutableLanguageEnvironment {

	@Override
	default boolean isFocused() {
		return true;
	}

	/**
	 * Return the focus language of the focused environment.
	 * 
	 * @return the focus language of the environment
	 * @see #isFocused()
	 */
	@Override
	Some<KRRLanguage> focusMember();

}
