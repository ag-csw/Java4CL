package api4kbj;

import functional.Some;

public interface FocusedEnvironment<T extends ClassWrapper<? extends S>, S> extends Environment<T, S> {

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
	Some<? extends T> focusMember();

}
