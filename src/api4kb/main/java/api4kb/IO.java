package api4kb;

/**
 * This type represents an action, yielding type R
 */
interface IO<R> {
	/**
	 * Warning! May have arbitrary side-effects!
	 */
	R unsafePerformIO();
}
