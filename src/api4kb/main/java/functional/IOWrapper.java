package functional;

import java.io.InputStream;

public class IOWrapper<R extends InputStream> implements IO<R> {

	public IOWrapper(InputStream wrappedInput) {
		this.wrappedInput = wrappedInput;
	}

	private InputStream wrappedInput;

	public void unsafePerformIO() {		
	}

	@Override
	public InputStream input() {
		return wrappedInput;
	}

}
