package krconfigured;

import api4kbj.Decomposable;
import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeExpression;

public interface StructuredKnowledgeAsset extends KnowledgeAsset, StructuredKnowledgeResource, Decomposable<KnowledgeAsset> {

	@Override
	StructuredKnowledgeExpression canonicalExpression();

	default // getter for canonical expressions as iterable
	Iterable<KnowledgeExpression> canonicalExpressions(){
		return canonicalExpression().components();
	}

}