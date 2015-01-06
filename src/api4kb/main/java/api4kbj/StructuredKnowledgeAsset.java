package api4kbj;


public interface StructuredKnowledgeAsset extends KnowledgeAsset,
		StructuredKnowledgeResource, Decomposable<KnowledgeAsset> {

	@Override
	StructuredKnowledgeExpression canonicalExpression();

	default// getter for canonical expressions as iterable
	Iterable<KnowledgeExpression> canonicalExpressions() {
		return canonicalExpression().components();
	}

}