package api4kbj;

import functional.Option;

public interface ImmutableEnvironment<T, S> {

	/**
	 * Return all members contained in the environment as an Iterable.
	 * 
	 * @return all members (which are of type T) contained contained in the environment
	 */
	Iterable<T> members();

	Iterable<? extends Mapping<? extends S,? extends S, S>> mappings();

	/**
	 * Return <tt>true</tt> if the argument is a member contained in the
	 * environment.
	 * 
	 * @param t
	 * @return <tt>true</tt> if <tt>t</tt> is contained in the environment
	 */
	boolean containsMember(T t);

	boolean containsMembers(Iterable<? extends T> t);

	boolean containsMapping(Mapping<? extends S,? extends S, S> t);

	boolean containsMappings(Iterable<? extends Mapping<? extends S,? extends S, S>> t);

	/**
	 * Return <tt>true</tt> if the environment has a focus. If <tt>true</tt>,
	 * the method {@link #focusMember()} must return a Some, otherwise it must
	 * return a None.
	 * 
	 * @return <tt>true</tt> if the environment has a focus
	 */
	boolean isFocused();

	
	<S1 extends S> S apply(S1 arg, T member) throws ClassNotFoundException;
	/*
	 default R apply(S arg, T member){
		 for (Mapping<? extends S, ? extends S, S> mp: mappings()){
			 Class<? extends S> clazz = mp.startClass();
			 if(arg.getClass().isAssignableFrom(clazz)){
				 if (mp.endClass().isAssignableFrom(member)){
					 return (R) mp.apply(arg);
				 }
			 }
		 }
		throw new IllegalArgumentException("A mapping is not registered to requested target member from the member associated with the input argument");
	 }
	 */

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
	<T1 extends T, S1 extends S, R extends ImmutableEnvironment<T1, S1>> boolean contains(R other);

}