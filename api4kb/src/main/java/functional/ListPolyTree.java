package functional;

import static fj.Function.curry;

import java.util.Iterator;

import fj.F;
import fj.data.List;

public class ListPolyTree<A> implements Iterable<A> {

	
	private ListPolyTree(List<EqEither<A, ListPolyTree<A>>> components) {
		super();
		this.components = components;
	}

	private List<EqEither<A, ListPolyTree<A>>> components;
	
	public static <B> ListPolyTree<B> tree (List<EqEither<B, ListPolyTree<B>>> components) {
		return new ListPolyTree<B>(components);
	}
	
	public static <B> ListPolyTree<B> 
	unit(B x){
		return new 
		   ListPolyTree<B>(
				   List.cons(
				      EqEither.unitLeft(x), List.nil()));
	}
	
	//first-class version of unit
	public static <B> F<B, ListPolyTree<B>> unit_(){
		return b -> unit(b);
	}

	public static <B> ListPolyTree<B> 
	empty(){
		return new 
		   ListPolyTree<B>(
				   List.nil());
	}
	
	public static <B> ListPolyTree<B>
	fromLeftSet(List<B> s) {
		return tree(s.map(t -> EqEither.unitLeft(t)));
	}

	public ListPolyTree<A>
	insertLeaf(A a) {
			return tree(List.cons(EqEither.unitLeft(a), components));
	}

	public ListPolyTree<A>
	insertBranch(ListPolyTree<A> t) {
			return tree(List.cons(EqEither.unitRight(t), components));
	}

	// extract component set for an M[B] tree from a node of an M[M[B]] tree
	// maintaing left/right orientation
	private static <B> List<EqEither<B, ListPolyTree<B>>> 
	extract(EqEither<ListPolyTree<B>, ListPolyTree<ListPolyTree<B>>> x){
		if (x.isLeft()) {
			return x.left().components;
		}
		return List.cons(EqEither.unitRight( join( x.right() )), List.nil());
	}
		
	public static <B> ListPolyTree<B> 
	join(ListPolyTree<ListPolyTree<B>> y){
		return new 
		   ListPolyTree<B>(
				   y.components.bind(s -> extract(s))); 
	}

	public static <B> F<ListPolyTree<ListPolyTree<B>>, ListPolyTree<B>> join_() {
		return s -> join(s);
	}

	// extract value from a node of the tree and map
	private static <B, C> EqEither<C, ListPolyTree<C>> 
	extractmap(EqEither<B, ListPolyTree<B>> x, F< B,  C> G){
		if (x.isLeft()) {
			return	EqEither.unitLeft(
					     G.f(
							x.left()));
		}
		return EqEither.unitRight(
					  x.right().map(G));
	}

	public <B> ListPolyTree<B> 
	map(F< A,  B> G) {
		return tree(components.map(s -> extractmap(s, G)));
	}

	public static <B, C> ListPolyTree<C> 
	map(F<B, C> g,
			ListPolyTree<B> treex) {
		return treex.map(g);
	}

	public static <B, C> F<F<B, C>, F<ListPolyTree<B>, ListPolyTree<C>>> 
	map_() {
		return curry((f, as) -> as.map(f));
	}


	public <B> ListPolyTree<B> 
	bind(F< A,  ListPolyTree<B>> H) {
		return join(map(H));
	}

	//static version of bind
	public static <B, C> ListPolyTree<C> bind(final F< B,  ListPolyTree<C>> f, final ListPolyTree<B> x) {
		return x.bind(f);
	}

	// first-class version of bind
	public static <B, C> F< F< B,  ListPolyTree<C>>,  F< ListPolyTree<B>,  ListPolyTree<C>>> bind_() {
		return curry((f, as) -> as.bind(f));
	}
	
	public Boolean contains(Object m) {
		return toLeftList().exists(s -> s.equals(m));
	}
	
	public Boolean containsAll(Iterable<A> c){
		for (A m : c){
			if (!contains(m)) return false;
		}
		return true;
	}

	public List<A> toLeftList() {
		return flatten().components.map(EqEither::left);
	}
	
	@Override
	public Iterator<A> iterator() {
		return toLeftList().iterator();
	}
	
	public int size() {
		return flatten().components.length();
	}

	public static <B> EqEither<ListPolyTree<B>, ListPolyTree<ListPolyTree<B>>> 
	level(final EqEither<B, ListPolyTree<B>> x) {
			if (x.isLeft()) return EqEither.unitLeft((unit(x.left())));
			return EqEither.unitLeft(x.right());
	}
	
	public boolean isFlat() {
		return EqEither.isAllLeft(components);
	}
	
	public static <B>  ListPolyTree<B> 
	flatten(ListPolyTree<B> x){
		if (x.isFlat()) return x;
		return flatten( join(tree(x.components.map(s -> level(s)))));
	}
	
	public ListPolyTree<A> flatten(){
		return flatten(this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((components == null) ? 0 : components.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ListPolyTree))
			return false;
		ListPolyTree<?> other = (ListPolyTree<?>) obj;
		if (components == null) {
			if (other.components != null)
				return false;
		} else if (!components.equals(other.components))
			return false;
		return true;
	}
}
