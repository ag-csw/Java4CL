package api4kbj;

public interface StructuredKnowledgeResource extends KnowledgeResource {
	@Override
	default Boolean isBasic() {
		return false;
	}

}
