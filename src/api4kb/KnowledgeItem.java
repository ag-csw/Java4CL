package api4kb;

public interface KnowledgeItem<S> extends KnowledgeResource {
	public KnowedgeSourceLevel level = KnowedgeSourceLevel.ITEM;

	// getter for wrapped data
	IO<S> getValue();

	//getter for encoding system
	EncodingSystem<?, S> getEncodingSystem();	
	
	//getter for dialect
	KRRDialect<?> getDialect();

}
