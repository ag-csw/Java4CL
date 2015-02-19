package api4kbj;

public interface BasicKnowledgeAsset extends KnowledgeAsset,
		BasicKnowledgeResource {

	@Override
	/**
	 * Returns the canonical expression of the asset.
	 * 
	 * @return the canonical expression of the asset
	 */
	KnowledgeExpression canonicalExpression();

}