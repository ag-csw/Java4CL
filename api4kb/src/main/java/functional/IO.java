package functional;

import java.io.InputStream;

/**
 * This type represents an action, yielding type R
 */
public interface IO<R extends InputStream> {
	/**
	 * Warning! May have arbitrary side-effects!
	 */
	void unsafePerformIO();

	InputStream input();
}
