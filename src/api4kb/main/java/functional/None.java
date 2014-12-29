package functional;

public class None<T> implements Option<T> {

	public None() {
	}

	@Override
	public Boolean isEmpty() {
		return true;
	}

	@Override
	public T value() {
		return null;
	}
}
