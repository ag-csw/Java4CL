package api4kb;

public interface Immutable {
	// An interface for immutable classes that memoize some of their methods.
	// All public fields should be final.
	// Private fields need not be final, but should not have public setter methods.
	// Lazy evaluation of these private fields is allowed, but their getters
	// should behave as if the fields were final, always returning the same result.
	
	// clear memoization cache of all methods
	void clear();

}
