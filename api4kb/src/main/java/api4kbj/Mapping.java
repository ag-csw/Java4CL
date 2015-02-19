package api4kbj;

import fj.F;

public interface Mapping<T, R> {

	Class<? extends T> startClass();

	Class<? extends R> endClass();

	F<T, R> function();

	default R f(T arg) {
		if (startClass().isAssignableFrom(arg.getClass())) {
			return function().f(arg);
		}
		throw new IllegalArgumentException("The argument" + arg
				+ " is not a member of the start class" + startClass());
	}

}
