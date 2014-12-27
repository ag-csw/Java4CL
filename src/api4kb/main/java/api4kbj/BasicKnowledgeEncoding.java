package api4kbj;

import elevation.Liftable;
import elevation.Lowerable;

public interface BasicKnowledgeEncoding<T, S> extends KnowledgeResource,
		Liftable, Lowerable {
	public KnowledgeSourceLevel level = KnowledgeSourceLevel.ENCODING;

	// getter for wrapped stream
	// S should be some type of stream
	S value();

	// getter for encoding system
	CodecSystem<T, S> codecSystem();

	// getter for dialect type
	KRRDialectType<T> dialectType();

}
