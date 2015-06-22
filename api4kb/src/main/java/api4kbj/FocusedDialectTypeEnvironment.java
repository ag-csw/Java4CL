package api4kbj;

import functional.Some;

public interface FocusedDialectTypeEnvironment<T> extends FocusedEnvironment<KRRDialectType<T>, T> {


	/**
	 * Return the focus language of the focused environment.
	 * 
	 * @return the focus language of the environment
	 * @see #isFocused()
	 */
	@Override
	Some<? extends KRRDialectType<T>> focusMember();

}
