package api4kbj;

import krconfigured.StructuredKnowledgeResourceConfigured;

public interface StructuredKnowledgeManifestation extends
		KnowledgeManifestation, StructuredKnowledgeResourceConfigured,
		Decomposable<KnowledgeManifestation> {

	// getter for dialects
	Iterable<KRRDialect> dialects();

}
