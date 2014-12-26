package api4kb;

import elevation.Liftable;
import elevation.Lowerable;

public interface KnowledgeEncoding<T, S> extends KnowledgeResource, Liftable,
		Lowerable {
	public KnowledgeSourceLevel level = KnowledgeSourceLevel.ENCODING;

	// getter for wrapped stream
	// S should be some type of stream
	S getValue();

	// getter for encoding system
	CodecSystem<T, S> getCodecSystem();

	// getter for dialect type
	KRRDialectType<T> getDialectType();

}
