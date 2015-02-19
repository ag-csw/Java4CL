package api4kbj;

public interface BasicAtomicKnowledgeResource extends BasicKnowledgeResource {

	default boolean isAtomic() {
		return true;
	}

}