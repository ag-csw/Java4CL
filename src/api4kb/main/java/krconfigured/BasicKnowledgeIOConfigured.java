package krconfigured;

import api4kbj.KRRFormat;
import api4kbj.KRRFormatType;
import api4kbj.KnowledgeIO;
import functional.IO;

public interface BasicKnowledgeIOConfigured extends KnowledgeIO,
		BasicKnowledgeResourceConfigured {

	// getter for item
	Object item();

	// getter for format
	KRRFormat format();

	<S> IO<S> build(KRRFormatType<S> formatType);

}
