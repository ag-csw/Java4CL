package api4kbj;


public interface StructuredKnowledgeResource<T extends KnowledgeResource> extends KnowledgeResource, Decomposable<T> {

	@Override
	default boolean isBasic() {
		return false;
	}

	int numComponents();

}