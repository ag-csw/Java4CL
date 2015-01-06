package api4kbj;

import krconfigured.BasicKnowledgeResourceConfigured;

public interface BasicKnowledgeEncodingG<T, S> extends KnowledgeEncoding,
		BasicKnowledgeResourceConfigured {

	// getter for wrapped stream
	// S should be some type of stream
	S value();

	// getter for encoding system
	CodecSystem<T, S> codecSystem();

	// getter for dialect type
	KRRDialectType<T> dialectType();

}
