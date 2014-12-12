package api4kb;

import elevation.Liftable;

public interface KnowledgeItem<T, S, R> extends KnowledgeResource, Liftable {
	public KnowledgeSourceLevel level = KnowledgeSourceLevel.ITEM;

	// getter for wrapped IO object
	IO<S> getValue();
	
	// getter for destination
	R getDestination();
	
	//getter for dialect
	KRRDialectType<T> getDialectType();

	

}
