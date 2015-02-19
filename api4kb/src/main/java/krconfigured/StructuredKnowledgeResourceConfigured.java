package krconfigured;

import api4kbj.KnowledgeResource;
import api4kbj.StructuredKnowledgeResource;

public interface StructuredKnowledgeResourceConfigured<T extends KnowledgeResource>
		extends KnowledgeResourceConfigured, StructuredKnowledgeResource<T> {

}
