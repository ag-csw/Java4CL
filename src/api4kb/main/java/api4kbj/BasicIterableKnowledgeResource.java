package api4kbj;


public interface BasicIterableKnowledgeResource extends BasicKnowledgeResource {

	@Override
	default boolean isAtomic() {
		return false;
	}

	Iterable<BasicKnowledgeResource> iterable();

}