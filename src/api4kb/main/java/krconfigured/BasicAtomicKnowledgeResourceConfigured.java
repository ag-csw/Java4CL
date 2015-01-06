package krconfigured;


public interface BasicAtomicKnowledgeResourceConfigured extends BasicKnowledgeResourceConfigured {

	@Override
	default boolean isAtomic() {
		return true;
	}

}
