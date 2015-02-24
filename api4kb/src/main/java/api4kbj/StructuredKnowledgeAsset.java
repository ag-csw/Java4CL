package api4kbj;

public interface StructuredKnowledgeAsset extends KnowledgeAsset,
		StructuredKnowledgeResource<KnowledgeAsset> {

	@Override
	StructuredKnowledgeExpression canonicalExpression();


}