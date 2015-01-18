package api4kbj;

import fj.F;

public interface Mapping<T, R> extends F<T, R> {

	Class<T> startClass();

	Class<R> endClass();

	@Override
	R f(T arg);

}
