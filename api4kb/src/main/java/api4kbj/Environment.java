package api4kbj;

import functional.EquivalenceRelation;
import functional.None;
import functional.Option;
import functional.Some;

/**
 * Environment is a structure composed of an Iterable of Mappings,
 * along with an Iterable of members that are the domains and ranges of the
 * mappings, as ClassWrappers.
 * 
 * Accessor methods should describe what mappings and members are in the
 * structure.
 *
 * Containment test methods should return true if and only if (all of) the
 * mappings or members are contained in the structure.
 * 
 * Compatibility tests for classes should return true if and only if the class
 * is a subset of the class of some member.
 * 
 * Compatibility tests for objects should return true if and only if the object
 * is an instance of of the class of some member.
 * 
 * There is in general no assumption made that the composition of mappings is
 * also a mapping in the environment, although implementing classes may make
 * that assumption.
 * 
 * Because this is an Immutable class, there should be no mutator methods.
 * 
 * The start and end classes of each mapping should be compatible with the
 * environment.
 * 
 * It general it should be possible to have members that do not correspond to
 * the domain or range of any mapping.
 * 
 * The environment should always have a default member //TODO Is this necessary?
 * 
 * The environment should have at least one member. //TODO Is this necessary?
 * 
 * If the environment is focused, then it should have some focus member,
 * otherwise it should have none.
 * 
 * If the environment is focused, it should contain a mapping from each
 * non-focus member to the focus member.
 *
 * The functionality of this interface is embodied in the apply method, which
 * performs the mapping of an argument into a target ClassWrapper.
 * 
 * If the argument is an instance of the class of the target ClassWrapper, then
 * the apply method should return the original argument.
 * 
 * In general, it is not assumed that mappings may be composed in order to
 * attain the target ClassWrapper.
 * 
 * Two environments may be compared according to containment of their
 * collections of mappings and members.
 * 
 * If the environment is preserving, then it should have some preserved
 * property, otherwise it should have none.
 * 
 * If the environment is preserving, then the input and output values of a
 * mapping application should satisfy the equivalence relation that defines the
 * preserved property.
 * 
 * @author taraathan
 *
 * @param <T extends ClassWrapper<? extends S> the kind of environment, e.g. for
 *        a LanguageEnvironment, T is KRRLanguage
 * @param <S>
 *            a superclass for members of the ClassWrappers, e.g. for a
 *            LanguageEnvironemnt, S is KnowledgeExpression
 */
import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = "http://www.omg.org/spec/API4KB/API4KBTerminology/Environment")
public interface Environment<T extends ClassWrapper<? extends S>, S>
		extends Immutable {

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
	Iterable<? extends Mapping<? extends S, ? extends S>> mappings();

	/**
	 * Return the optional focus member of the environment.
	 * 
	 * @return the optional focus member of the environment
	 * @see #isFocused()
	 */
	Option<? extends T> focusMember();

	/**
	 * Return the equivalence relation that is preserved by all mappings.
	 * 
	 * @return the equivalence relation that is preserved by all mappings
	 * @see #isPreserving()
	 */
	Option<EquivalenceRelation> preserves();

	/**
	 * Return <tt>true</tt> if and only if the class wrapper is a member
	 * contained in the environment.
	 * 
	 * @param t the class wrapper to be tested
	 * @return <tt>true</tt> if <tt>t</tt> is contained in the environment
	 */
	default boolean containsMember(final T t) {
		for (T member : members()) {
			if (member.equals(t)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return <tt>true</tt> if and only if all of the items in the collection
	 * are members contained in the environment.
	 * 
	 * @param t the collection to be tested
	 * @return <tt>true</tt> if and only if all <tt>t</tt> belongs to the member
	 *         collection
	 */
	default boolean containsMembers(final Iterable<? extends T> t) {
		for (T s : t) {
			if (!containsMember(s)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return <tt>true</tt> if and only if the argument is a mapping contained
	 * in the environment.
	 * 
	 * @param t the mapping to be tested
	 * @return <tt>true</tt> if <tt>t</tt> is contained in the environment
	 */
	default boolean containsMapping(final Mapping<? extends S, ? extends S> t) {
		for (Mapping<? extends S, ? extends S> map : mappings()) {
			if (map.equals(t)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return <tt>true</tt> if and only if all of the items in the collection
	 * are mappings contained in the environment.
	 * 
	 * The default method assumes that all mappings are explicitly represented
	 * in the
	 * 
	 * @param t   the collection to be tested
	 * @return <tt>true</tt> if and only if all <tt>t</tt> belongs to the
	 *         mapping collection
	 */
	default boolean containsMappings(
			final Iterable<? extends Mapping<? extends S, ? extends S>> t) {
		for (Mapping<? extends S, ? extends S> s : t) {
			if (!containsMapping(s)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return <tt>true</tt> if the environment has a focus. If <tt>true</tt>,
	 * the method {@link #focusMember()} should return a Some, otherwise it
	 * should return a None.
	 * 
	 * @return <tt>true</tt> if the environment has a focus
	 */
	boolean isFocused();

	/**
	 * Return <tt>true</tt> if the environment has a property that is preserved
	 * by the mappings. If <tt>true</tt>, the method {@link #preserves()} should
	 * return a Some, otherwise it should return a None.
	 * 
	 * @return <tt>true</tt> if the environment has a focus
	 */
	boolean isPreserving();

	/**
	 * 
	 * @param arg an argument to be tested for compatibility
	 * @return <tt>true</tt> if the argument is an instance of the class of some
	 *         member class wrapper.
	 */
	default boolean isCompatibleWith(final S arg) {
		if (arg == null) {
			return false;
		}
		Class<? extends Object> argClass = arg.getClass();
		for (T member : members()) {
			Class<? extends S> memberClass = member.asClass();
			if (memberClass.isAssignableFrom(argClass)) {
				return true;
			}
		}
		return false;
	}

	default <S1 extends S> S apply(final S1 arg, final T member) {
		if (arg == null) {
			throw new NullPointerException("Cannot map null");
		}
		Class<? extends Object> argClass = arg.getClass();
		Class<?> memberClass = member.asClass();
		if (memberClass.isAssignableFrom(argClass)) {
			return arg;
		}
		for (Mapping<? extends S, ? extends S> mp : mappings()) {
			Class<? extends S> clazzIn = mp.startClass();
			Class<? extends S> clazzOut = mp.endClass();
			if (clazzIn.isAssignableFrom(argClass)) {
				if (memberClass.isAssignableFrom(clazzOut)) {
					@SuppressWarnings("unchecked")
					final Mapping<S1, ? extends S> mp2 = (Mapping<S1, ? extends S>) mp;
					return mp2.f(arg);
				}
			}
		}
		throw new IllegalArgumentException(
				"A mapping is not registered to requested target member from the member associated with the input argument");
	}

	/**
	 * Return <tt>true</tt> if this environment contains the environment
	 * <tt>other</tt>.
	 * 
	 * @param other an environment
	 * @return <tt>true</tt> if this environment contains <tt>other</tt>
	 */

	default <T1 extends ClassWrapper<S1>, S1 extends S> boolean contains(
			Environment<T1, S1> other) {
		if (other == null)
			return false;
		@SuppressWarnings("unchecked")
		Iterable<? extends T> otherMembers = (Iterable<? extends T>) other
				.members();
		if (!containsMembers(otherMembers))
			return false;
		if (!containsMappings(other.mappings()))
			return false;
		return true;
	}

	default boolean isCompatibleWithClass(final Class<?> clazz) {
		if (clazz == null)
			return false;
		for (T member : members()) {
			if (member.asClass().isAssignableFrom(clazz))
				return true;
		}
		return false;
	}

	default Option<Mapping<? extends S, ? extends S>> findMapping(
			final Class<?> startClazz, final Class<?> endClazz) {
		if (startClazz == null || endClazz == null)
			return new None<Mapping<? extends S, ? extends S>>();
		for (Mapping<? extends S, ? extends S> mp : mappings()) {
			Class<? extends S> clazzIn = mp.startClass();
			Class<? extends S> clazzOut = mp.endClass();
			if (clazzIn.isAssignableFrom(startClazz)
					&& endClazz.isAssignableFrom(clazzOut)) {
				return new Some<Mapping<? extends S, ? extends S>>(mp);
			}
		}
		return new None<Mapping<? extends S, ? extends S>>();
	}

}