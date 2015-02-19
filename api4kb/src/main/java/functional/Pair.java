package functional;

public class Pair<T> {
	// TODO use a tuple class from some library
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}

	private T first;
	private T second;

	T _1() {
		return first;
	}

	T _2() {
		return second;
	}

}