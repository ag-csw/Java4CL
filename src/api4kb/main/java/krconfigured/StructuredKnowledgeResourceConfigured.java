package krconfigured;


public interface StructuredKnowledgeResourceConfigured extends KnowledgeResourceConfigured {
	@Override
	default boolean isBasic() {
		return false;
	}

	int numComponents();

}
