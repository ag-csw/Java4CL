package api4kbj;

import krconfigured.StructuredKnowledgeResourceConfigured;

public interface StructuredKnowledgeEncoding extends KnowledgeEncoding,
		StructuredKnowledgeResourceConfigured, Decomposable<KnowledgeEncoding> {

	// getter for formats
	Iterable<KRRFormat> formats();

}
