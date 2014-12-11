package api4kb;

import lazykb.LazyInitializing;


public abstract class AbstractKnowledgeItem<T, S, R> implements KnowledgeItem<T, S, R>, LazyInitializing<IO<S>>{

	public AbstractKnowledgeItem() {
		// TODO Auto-generated constructor stub
	}

}
