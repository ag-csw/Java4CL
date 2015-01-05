package api4kbj;

import functional.IO;

public interface BasicKnowledgeItem extends KnowledgeItem,
		BasicKnowledgeResource {

	// getter for destination
	Object destination();

	// getter for format
	KRRFormat format();

	<S> IO<S> build(KRRFormatType<S> formatType);

}
