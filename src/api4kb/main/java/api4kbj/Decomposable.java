package api4kbj;

public interface Decomposable<T> {

	// getter for components of type T
	// if empty, is basic
	// TODO change type to immutable set
	public Iterable<T> components();

}