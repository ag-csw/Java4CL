package api4kbj;

import fj.F;


public interface Mapping<T extends S, R extends S, S> extends F<T, R> {
	
	Class<T> startClass();
	
	Class<R> endClass();

	@Override
	R f(T arg);


}
