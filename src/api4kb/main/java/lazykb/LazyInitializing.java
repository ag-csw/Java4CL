package lazykb;

public interface LazyInitializing<T> {
	// getter for wrapped initial value
	T getValue();

}
