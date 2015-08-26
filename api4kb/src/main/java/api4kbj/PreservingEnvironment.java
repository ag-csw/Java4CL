package api4kbj;

import functional.EquivalenceRelation;

public interface PreservingEnvironment<T extends ClassWrapper<? extends S>, S> extends Environment<T, S> {

	@Override
	default boolean isPreserving() {
		return true;
	}

	/**
	 * Return the preserving relation of the environment.
	 * 
	 * @return the preserving language of the environment
	 * @see #isPreserving()
	 */
		public EquivalenceRelation requiredPreserves();

}
