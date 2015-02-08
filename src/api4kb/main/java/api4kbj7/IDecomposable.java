package api4kbj7;

public interface IDecomposable<T> {

	// getter for components of type T
	public Iterable<T> components();

}