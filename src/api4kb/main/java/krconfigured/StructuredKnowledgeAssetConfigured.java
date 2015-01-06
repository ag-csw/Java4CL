package krconfigured;

import api4kbj.Decomposable;
import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeExpression;

public interface StructuredKnowledgeAssetConfigured extends KnowledgeAsset,
		StructuredKnowledgeResourceConfigured, Decomposable<KnowledgeAsset> {

	@Override
	StructuredKnowledgeExpressionConfigured canonicalExpression();

	// getter for canonical expressions
	default Iterable<KnowledgeExpression> canonicalExpressions() {
		return canonicalExpression().components();
	}

}
