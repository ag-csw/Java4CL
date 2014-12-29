package api4kbj;

import functional.IO;

public interface BasicKnowledgeItem<T, S, R> extends KnowledgeItem,
		BasicKnowledgeResource {

	// getter for wrapped IO object
	IO<S> value();

	// getter for destination
	R destination();

	// getter for dialect type
	KRRDialectType<T> dialectType();

	// getter for codec system
	CodecSystem<T, S> codecSystem();

}
