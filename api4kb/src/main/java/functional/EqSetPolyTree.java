package functional;

import static fj.Function.curry;

import java.util.Iterator;
import java.util.function.BiFunction;
import fj.F;

public class EqSetPolyTree<A> implements Iterable<A> {

	private EqSetPolyTree(EqSet<EqEither<A, EqSetPolyTree<A>>> components) {
		super();
		this.components = components;
	}

	private EqSet<EqEither<A, EqSetPolyTree<A>>> components;

	public static <B> EqSetPolyTree<B> tree(
			EqSet<EqEither<B, EqSetPolyTree<B>>> components) {
		return new EqSetPolyTree<B>(components);
	}

	public static <B> EqSetPolyTree<B> unit(B x) {
		return new EqSetPolyTree<B>(EqSet.unit(EqEither.unitLeft(x)));
	}

	// first-class version of unit
	public static <B> F<B, EqSetPolyTree<B>> unit_() {
		return b -> unit(b);
	}

	public static <B> EqSetPolyTree<B> empty() {
		return new EqSetPolyTree<B>(EqSet.empty());
	}

	public static <B> EqSetPolyTree<B> fromLeftSet(EqSet<B> s) {
		return EqSetPolyTree.tree(s.map(t -> EqEither.unitLeft(t)));
	}

	public EqSetPolyTree<A> insertLeaf(A a) {
		return EqSetPolyTree.tree(components.insert(EqEither.unitLeft(a)));
	}

	public EqSetPolyTree<A> insertBranch(EqSetPolyTree<A> t) {
		return EqSetPolyTree.tree(components.insert(EqEither.unitRight(t)));
	}

	public static <B> EqSetPolyTree<B> join(EqSetPolyTree<EqSetPolyTree<B>> y) {
		return tree(y.components.bind(x -> x.bimap(s -> s.components,
				s -> EqSet.unit(EqEither.unitRight(join(s))))));
	}

	public <B> B foldl(B z, BiFunction<B, A, B> G) {
		B result = z;
		for (EqEither<A, EqSetPolyTree<A>> m : components) {
			if (m.isLeft())
				result = G.apply(result, m.left());
			result = m.right().foldl(result, G);
		}
		return result;

	}

	public static <B> F<EqSetPolyTree<EqSetPolyTree<B>>, EqSetPolyTree<B>> join_() {
		return s -> join(s);
	}

	// extract value from a node of the tree and map
	private static <B, C> EqEither<C, EqSetPolyTree<C>> extractmap(
			EqEither<B, EqSetPolyTree<B>> x, F<B, C> G) {
		if (x.isLeft()) {
			return EqEither.unitLeft(G.f(x.left()));
		}
		return EqEither.unitRight(x.right().map(G));
	}

	public <B> EqSetPolyTree<B> map(F<A, B> G) {
		return tree(components.map(s -> extractmap(s, G)));
	}

	public static <B, C> EqSetPolyTree<C> map(F<B, C> g, EqSetPolyTree<B> treex) {
		return treex.map(g);
	}

	public static <B, C> F<F<B, C>, F<EqSetPolyTree<B>, EqSetPolyTree<C>>> map_() {
		return curry((f, as) -> as.map(f));
	}

	public <B> EqSetPolyTree<B> bind(F<A, EqSetPolyTree<B>> H) {
		return join(map(H));
	}

	// static version of bind
	public static <B, C> EqSetPolyTree<C> bind(final F<B, EqSetPolyTree<C>> f,
			final EqSetPolyTree<B> x) {
		return x.bind(f);
	}

	// first-class version of bind
	public static <B, C> F<F<B, EqSetPolyTree<C>>, F<EqSetPolyTree<B>, EqSetPolyTree<C>>> bind_() {
		return curry((f, as) -> as.bind(f));
	}

	public Boolean contains(Object m) {
		return toLeftEqSet().contains(m);
	}

	public Boolean containsAll(Iterable<A> c) {
		for (A m : c) {
			if (!contains(m))
				return false;
		}
		return true;
	}

	public EqSet<A> toLeftEqSet() {
		return flatten().components.map(EqEither::left);
	}

	@Override
	public Iterator<A> iterator() {
		return toLeftEqSet().iterator();
	}

	public int size() {
		return flatten().components.size();
	}

	public static <B> EqEither<EqSetPolyTree<B>, EqSetPolyTree<EqSetPolyTree<B>>> level(
			final EqEither<B, EqSetPolyTree<B>> x) {
		if (x.isLeft())
			return EqEither.unitLeft((unit(x.left())));
		return EqEither.unitLeft(x.right());
	}

	public boolean isFlat() {
		return EqEither.isAllLeft(components);
	}

	public static <B> EqSetPolyTree<B> flatten(EqSetPolyTree<B> x) {
		if (x.isFlat())
			return x;
		return flatten(join(EqSetPolyTree.tree(x.components.map(s -> level(s)))));
	}

	public EqSetPolyTree<A> flatten() {
		return flatten(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((components == null) ? 0 : components.hashCode());
		return result + size();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		//if (obj.hashCode() != hashCode())
		//	return false;
		if (!(obj instanceof EqSetPolyTree))
			return false;
		EqSetPolyTree<?> other = (EqSetPolyTree<?>) obj;
		if (components == null) {
				return false;
		}
		if (components.size() != other.components.size()) {
			return false;
		}
		return components.equals(other.components);
	}
}
