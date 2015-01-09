package krconfigured;

import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeExpression;
import api4kbj.StructuredKnowledgeAsset;

public interface StructuredKnowledgeAssetConfigured<T extends KnowledgeAsset, S extends KnowledgeExpression> extends
		StructuredKnowledgeResourceConfigured<T>, StructuredKnowledgeAsset<T, S> {

}
