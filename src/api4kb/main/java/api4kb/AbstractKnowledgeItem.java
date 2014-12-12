package api4kb;

import lazykb.LazyInitializing;


public abstract class AbstractKnowledgeItem<T, S, R> implements KnowledgeItem<T, S, R>, LazyInitializing<IO<S>>{

	private AbstractKRRDialectType<T> dialectType;

	public AbstractKnowledgeItem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public KnowledgeSourceLevel getLevel() {
		return level;
	}

	//getter for encoding system
	abstract CodecSystem<T, S> getCodecSystem();	

	//lifting method
	abstract KnowledgeEncoding<T, S> prototype();

	// action
	abstract void write();

	// action
	abstract S read();

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IO<S> getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public R getDestination() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractKRRDialectType<T> getDialectType() {
		return dialectType;
	}



}
