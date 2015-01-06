package api4kbj;


public interface StructuredKnowledgeResource extends KnowledgeResource {

	@Override
	default boolean isBasic() {
		return false;
	}

	int numComponents();

}