package functional;

import java.io.IOException;

/**
 * This type represents an action, yielding type R
 */
public abstract class IOWriter {

	public abstract void write() throws IOException;
}
