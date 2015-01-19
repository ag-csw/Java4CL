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

	@Override
	public int hashCode() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		return true;
	}
	
	public None<T> clone(){
		return new None<T>();
	}

}
