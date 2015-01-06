package api4kbj;

import krconfigured.KnowledgeResourceConfigured;

public interface BasicKnowledgeResource extends KnowledgeResourceConfigured {
	@Override
	default boolean isBasic() {
		return true;
	}

	boolean isAtomic();

}
