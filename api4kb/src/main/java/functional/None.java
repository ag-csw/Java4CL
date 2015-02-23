package functional;

public class None<T> implements Option<T> {

	public None() {
	}
	
	private boolean isEmpty = true;

	@Override
	public Boolean isEmpty() {
		return isEmpty;
	}

	@Override
	public T value() {
		return null;
	}

	@Override
	public None<T> clone() {
		return new None<T>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isEmpty ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

}
