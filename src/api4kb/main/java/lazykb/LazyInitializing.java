package lazykb;

public interface LazyInitializing<T> {
	// public method to clear cache of initial value
	public void clearInitialValue();
	// package private getter for cached initial value
	// T getInitialValue();
	// package private setter for cached initial value
	// protected void setInitialValue(T value);

}
