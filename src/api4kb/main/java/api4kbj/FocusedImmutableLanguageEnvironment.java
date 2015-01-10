package api4kbj;

import functional.Some;

public interface FocusedImmutableEnvironment extends ImmutableEnvironment {

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
	Some<KRRLanguage> focusLanguage();

	boolean containsLanguages(Iterable<KRRLanguage> langs);
}
