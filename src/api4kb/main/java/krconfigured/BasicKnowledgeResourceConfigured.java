package krconfigured;


public interface BasicKnowledgeResourceConfigured extends KnowledgeResourceConfigured {
	@Override
	default boolean isBasic() {
		return true;
	}

	boolean isAtomic();

}
