package functional;

public interface Option<T> extends Cloneable {
	Boolean isEmpty();

	T value();

	Option<T> clone();

}
