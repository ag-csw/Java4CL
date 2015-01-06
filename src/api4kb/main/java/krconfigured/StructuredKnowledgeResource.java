package krconfigured;

import api4kbj.KnowledgeResource;

public interface StructuredKnowledgeResource extends KnowledgeResource {

	default boolean isBasic(){
		return false;
	}

	int numComponents();

}