package api4kbj7;

import fj.F;

public interface IMapping<T, R> extends IImmutable {

	Class<? extends T> startClass();

	Class<? extends R> endClass();

	F<T, R> function();

}