package api4kbj;

import elevation.Liftable;
import functional.IO;

public interface KnowledgeItem<T, S, R> extends KnowledgeResource, Liftable {
	public KnowledgeSourceLevel level = KnowledgeSourceLevel.ITEM;

	// getter for wrapped IO object
	IO<S> value();

	// getter for destination
	R destination();

	// getter for dialect type
	KRRDialectType<T> dialectType();

	// getter for codec system
	CodecSystem<T, S> codecSystem();

}
