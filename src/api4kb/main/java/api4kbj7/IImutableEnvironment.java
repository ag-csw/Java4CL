package api4kbj7;

import functional.EquivalenceRelation;
import functional.Option;

public interface IImutableEnvironment<T, S> extends IImmutable {

	/**
	 * Return all members contained in the environment as an Iterable.
	 * 
	 * @return all members (which are of type T) contained in the environment
	 */
	Iterable<? extends T> members();

	/**
	 * Return all mappings contained in the environment as an Iterable.
	 * 
	 * @return all mappings contained in the environment
	 */
	Iterable<? extends IMapping<? extends S, ? extends S>> mappings();

	/**
	 * Return the optional focus member of the environment.
	 * 
	 * @return the optional focus member of the environment
	 * @see #isFocused()
	 */
	Option<T> focusMember();

	/**
	 * Return the equivalence relation that is preserved by all mappings.
	 * 
	 * @return the equivalence relation that is preserved by all mappings
	 * @see #isPreserving()
	 */
	Option<EquivalenceRelation> preserves();

}