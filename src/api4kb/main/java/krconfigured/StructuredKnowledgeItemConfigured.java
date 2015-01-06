package krconfigured;

import api4kbj.Decomposable;
import api4kbj.KnowledgeItem;

public interface StructuredKnowledgeItemConfigured extends KnowledgeItem,
		StructuredKnowledgeResourceConfigured, Decomposable<KnowledgeItem> {

}
