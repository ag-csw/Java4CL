package api4kb;

import lazykb.LazyInitializing;


public abstract class AbstractKnowledgeItem<T, S, R> implements KnowledgeItem<T, S, R>, LazyInitializing<IO<S>>{

	public AbstractKnowledgeItem() {
		// TODO Auto-generated constructor stub
	}


	//getter for encoding system
	abstract EncodingSystem<T, S> getEncodingSystem();	

	//lifting method
	abstract KnowledgeEncoding<T, S> prototype();

	// action
	abstract void write();

	// action
	abstract S read();



}
