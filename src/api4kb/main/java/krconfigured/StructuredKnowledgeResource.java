package krconfigured;

import api4kbj.KnowledgeResource;

public interface StructuredKnowledgeResource extends KnowledgeResource {

	@Override
	default boolean isBasic() {
		return false;
	}

	int numComponents();

}