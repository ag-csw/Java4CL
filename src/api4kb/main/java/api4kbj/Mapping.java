package api4kbj;

import api4kbj7.IMapping;


public interface Mapping<T, R> extends IMapping<T, R> {

	default R f(T arg) {
		if (startClass().isAssignableFrom(arg.getClass())) {
			return function().f(arg);
		}
		throw new IllegalArgumentException("The argument" + arg
				+ " is not a member of the start class" + startClass());
	}

}
