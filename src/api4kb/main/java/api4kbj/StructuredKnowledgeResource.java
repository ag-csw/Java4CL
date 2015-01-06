package api4kbj;

import krconfigured.KnowledgeResourceConfigured;

public interface StructuredKnowledgeResource extends KnowledgeResourceConfigured {
	@Override
	default boolean isBasic() {
		return false;
	}

	int numComponents();

}
