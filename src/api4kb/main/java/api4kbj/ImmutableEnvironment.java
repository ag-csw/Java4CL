package api4kbj;

import functional.Option;

public interface ImmutableEnvironment<T extends ClassWrapper<S>, S> extends Immutable {


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
	Iterable<? extends Mapping<? extends S,? extends S>> mappings();

	/**
	 * Return <tt>true</tt> if the argument is a member contained in the
	 * environment.
	 * 
	 * @param t
	 * @return <tt>true</tt> if <tt>t</tt> is contained in the environment
	 */
	default boolean containsMember(T t){
		for (T member:members()){
			if(member.equals(t)){
				return true;
			}
		}
		return false;
	}

	default boolean containsMembers(Iterable<? extends T> t){
		for (T s:t){
			if(!containsMember(s)){ return false; }
		}
		return true;
	}

	//boolean containsMapping(Mapping<? extends S,? extends S> t);
	default boolean containsMapping(
			Mapping<? extends S, ? extends S> t) {
		for (Mapping<? extends S, ? extends S> map : mappings()) {
			if (map.equals(t)) {
				return true;
			}
		}
		return false;
	}


	default boolean containsMappings(Iterable<? extends Mapping<? extends S,? extends S>> t){
		for (Mapping<? extends S,? extends S> s:t){
			if(!containsMapping(s)){ return false; }			
		}
		return true;
	}

	/**
	 * Return <tt>true</tt> if the environment has a focus. If <tt>true</tt>,
	 * the method {@link #focusMember()} must return a Some, otherwise it must
	 * return a None.
	 * 
	 * @return <tt>true</tt> if the environment has a focus
	 */
	boolean isFocused();

	
	default <S1 extends S> S apply(S1 arg, T member){
		 for (Mapping<? extends S, ? extends S> mp: mappings()){
			 Class<? extends S> clazz = mp.startClass();
			 if(clazz.isAssignableFrom(arg.getClass())){
				 @SuppressWarnings("unchecked")
				Mapping<S1, ? extends S> mp2 = (Mapping<S1, ? extends S>) mp;
				 if (member.asClass().isAssignableFrom(mp2.endClass())){
					 return mp2.f(arg);
				 }
			 }
		 }
		throw new IllegalArgumentException("A mapping is not registered to requested target member from the member associated with the input argument");
	 }

	/**
	 * Return the default member of the environment.
	 * 
	 * @return the default member of the environment
	 */
	T defaultMember();

	/**
	 * Return the optional focus member of the environment.
	 * 
	 * @return the optional focus member of the environment
	 * @see #isFocused()
	 */
	Option<T> focusMember();

	/**
	 * Return <tt>true</tt> if this environment contains the environment
	 * <tt>other</tt>.
	 * 
	 * @param other
	 *            an environment
	 * @return <tt>true</tt> if this environment contains <tt>other</tt>
	 */
	default <T1 extends ClassWrapper<S1>, S1 extends S> boolean contains(ImmutableEnvironment<T1, S1> other){
		if (other == null) return false;
		@SuppressWarnings("unchecked")
		Iterable<? extends T> otherMembers = (Iterable<? extends T>) other.members();
		if (!containsMembers(otherMembers)) return false;
        if (!containsMappings( other.mappings())) return false;
		return true;
	}
	
}