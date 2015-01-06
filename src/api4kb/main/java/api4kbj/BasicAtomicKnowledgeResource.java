package api4kbj;


public interface BasicAtomicKnowledgeResource extends BasicKnowledgeResource {

	@Override
	default boolean isAtomic() {
		return true;
	}

}