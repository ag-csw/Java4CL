package api4kb;

import elevation.Liftable;
import elevation.Lowerable;

public interface KnowledgeManifestation<T> extends KnowledgeResource, Liftable, Lowerable {
	KnowledgeSourceLevel level = KnowledgeSourceLevel.MANIFESTATION;
	
	//getter for wrapped object
	T getValue();
	
	//getter for dialect type
	KRRDialectType<T> getDialectType();
	
	Class<T> getType();
	
	
	
}
