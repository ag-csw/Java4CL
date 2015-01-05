package functional;

/**
 * This type represents an action, yielding type R
 */
public interface IO<R> {
	/**
	 * Warning! May have arbitrary side-effects!
	 */
	R unsafePerformIO();

	Object destination();
}
