package api4kbj;

import krconfigured.BasicKnowledgeResourceConfigured;

public interface BasicAtomicKnowledgeResource extends BasicKnowledgeResourceConfigured {

	@Override
	default boolean isAtomic() {
		return true;
	}

}
