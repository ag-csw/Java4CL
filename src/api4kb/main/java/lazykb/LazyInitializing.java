package lazykb;

public interface LazyInitializing<T> {
	// optional public method to clear cache of initial value
	// if not supported, throws an Exception
	public void clearInitialValue() throws Exception;
	// package private getter for cached initial value
	// T initialValue();
	// package private setter for cached initial value
	// protected void setInitialValue(T value);

}
