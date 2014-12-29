package functional;

public interface Option<T> {
	Boolean isEmpty();

	T value();

}
