package krconfigured;

import api4kbj.Decomposable;
import api4kbj.KnowledgeIO;

public interface StructuredKnowledgeIOConfigured extends KnowledgeIO,
		StructuredKnowledgeResourceConfigured, Decomposable<KnowledgeIO> {

}
