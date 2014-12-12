package api4kb;

public interface KnowledgeItem<T, S, R> extends KnowledgeResource {
	public KnowedgeSourceLevel level = KnowedgeSourceLevel.ITEM;

	// getter for wrapped IO object
	IO<S> getValue();
	
	// getter for destination
	R getDestination();
	
	//getter for dialect
	KRRDialectType<T> getDialectType();

	

}
