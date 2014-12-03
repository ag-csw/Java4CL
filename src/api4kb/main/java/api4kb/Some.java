package api4kb;

public class Some<T> implements Option<T> {

	public Some(T value) {
		this.value = value;
	}
	private final T value;
	public Boolean isEmpty() {
		return false;
	}
	public T getValue() {
		return value;
	}

}
