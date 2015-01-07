package api4kbj;


public interface BasicIterableKnowledgeResource extends BasicKnowledgeResource {

	default boolean isAtomic() {
		return false;
	}

	Iterable<BasicKnowledgeResource> iterable();

}