package api4kb;

public interface KnowledgeItem<T, S, R> extends KnowledgeResource {
	public KnowedgeSourceLevel level = KnowedgeSourceLevel.ITEM;

	// getter for wrapped data
	IO<S> getValue();

	//getter for encoding system
	EncodingSystem<T, S> getEncodingSystem();	
	
	//getter for dialect
	KRRDialect<T> getDialect();
	
	// getter for destination
	R getDestination();
	
	// action
	S run();
	
	//lifting method
	KnowledgeEncoding<T, S> read();

}
