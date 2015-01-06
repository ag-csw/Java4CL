package api4kbj;

import krconfigured.StructuredKnowledgeResourceConfigured;

public interface StructuredKnowledgeAsset extends KnowledgeAsset,
		StructuredKnowledgeResourceConfigured, Decomposable<KnowledgeAsset> {

	@Override
	StructuredKnowledgeExpression canonicalExpression();

	// getter for canonical expressions
	default Iterable<KnowledgeExpression> canonicalExpressions() {
		return canonicalExpression().components();
	}

}
