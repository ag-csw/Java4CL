package api4kbj;

import krconfigured.StructuredKnowledgeResourceConfigured;

public interface StructuredKnowledgeExpression extends KnowledgeExpression,
		StructuredKnowledgeResourceConfigured, Decomposable<KnowledgeExpression> {

	// getter for languages
	Iterable<KRRDialect> languages();

}
