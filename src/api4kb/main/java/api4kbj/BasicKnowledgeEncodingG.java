package api4kbj;

public interface BasicKnowledgeEncodingG<T, S> extends KnowledgeEncoding,
		BasicKnowledgeResource {

	// getter for wrapped stream
	// S should be some type of stream
	S value();

	// getter for encoding system
	CodecSystem<T, S> codecSystem();

	// getter for dialect type
	KRRDialectType<T> dialectType();

}
