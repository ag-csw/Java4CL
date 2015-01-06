package krconfigured;

import api4kbj.KRRFormat;
import api4kbj.KRRFormatType;
import api4kbj.KnowledgeItem;
import functional.IO;

public interface BasicKnowledgeItemConfigured extends KnowledgeItem,
		BasicKnowledgeResourceConfigured {

	// getter for destination
	Object destination();

	// getter for format
	KRRFormat format();

	<S> IO<S> build(KRRFormatType<S> formatType);

}
