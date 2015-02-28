package api4kbj;

public interface Mapping<T, R> {

	Class<? extends T> startClass();

	Class<? extends R> endClass();

	Object function();

	R f(final T arg);

}
