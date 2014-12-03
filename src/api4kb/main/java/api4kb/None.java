package api4kb;

public class None<T> implements Option<T> {
	
	public None() {
	}

	public Boolean isEmpty() {
		return true;
	}
}
