package api4kbc;

import api4kbj.Mapping;
import fj.F;

public interface FJMapping<T, R> extends Mapping<T, R> {


	@Override
	F<T, R> function();

	@Override
	default R f(T arg) {
		if (startClass().isAssignableFrom(arg.getClass())) {
			return function().f(arg);
		}
		throw new IllegalArgumentException("The argument" + arg
				+ " is not a member of the start class" + startClass());
	}

}
