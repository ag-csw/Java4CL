package api4kbj;

public interface BasicKnowledgeResource extends KnowledgeResource {
	@Override
	default Boolean isBasic() {
		return true;
	}

}
