package api4kbj;

public interface Decomposable<T> {

	// getter for components of type T
	public Iterable<? extends T> components();

	int numComponents();

}