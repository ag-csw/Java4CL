package api4kbj;

import krconfigured.BasicKnowledgeExpressionConfigured;

public interface BasicKnowledgeAsset extends KnowledgeAsset, BasicKnowledgeResource {

	/**
	 * Returns the canonical expression of the asset.
	 * 
	 * @return the canonical expression of the asset
	 */
	BasicKnowledgeExpressionConfigured canonicalExpression();

}