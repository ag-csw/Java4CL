package api4kbj;

public interface StructuredKnowledgeAsset extends KnowledgeAsset,
		StructuredKnowledgeResource, Decomposable<KnowledgeAsset> {

	@Override
	StructuredKnowledgeExpression canonicalExpression();

	// getter for canonical expressions
	default Iterable<KnowledgeExpression> canonicalExpressions() {
		return canonicalExpression().components();
	}

}
