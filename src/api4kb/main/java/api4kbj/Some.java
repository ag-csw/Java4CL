package api4kbj;

public class Some<T> implements Option<T> {

	public Some(T value) {
		this.value = value;
	}

	private final T value;

	@Override
	public Boolean isEmpty() {
		return false;
	}

	public T getValue() {
		return value;
	}

}
