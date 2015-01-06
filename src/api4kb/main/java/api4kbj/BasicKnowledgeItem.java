package api4kbj;

import krconfigured.BasicKnowledgeResourceConfigured;
import functional.IO;

public interface BasicKnowledgeItem extends KnowledgeItem,
		BasicKnowledgeResourceConfigured {

	// getter for destination
	Object destination();

	// getter for format
	KRRFormat format();

	<S> IO<S> build(KRRFormatType<S> formatType);

}
