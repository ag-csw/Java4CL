package krconfigured;

import api4kbj.Decomposable;
import api4kbj.KRRFormat;
import api4kbj.KnowledgeEncoding;

public interface StructuredKnowledgeEncodingConfigured extends KnowledgeEncoding,
		StructuredKnowledgeResourceConfigured, Decomposable<KnowledgeEncoding> {

	// getter for formats
	Iterable<KRRFormat> formats();

}
