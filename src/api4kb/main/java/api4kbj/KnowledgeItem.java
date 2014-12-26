package api4kbj;

import elevation.Liftable;
import functional.IO;

public interface KnowledgeItem<T, S, R> extends KnowledgeResource, Liftable {
	public KnowledgeSourceLevel level = KnowledgeSourceLevel.ITEM;

	// getter for wrapped IO object
	IO<S> getValue();

	// getter for destination
	R getDestination();

	// getter for dialect type
	KRRDialectType<T> getDialectType();

	// getter for codec system
	CodecSystem<T, S> getCodecSystem();

}
