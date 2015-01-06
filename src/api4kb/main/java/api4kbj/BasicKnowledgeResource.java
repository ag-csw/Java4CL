package api4kbj;

public interface BasicKnowledgeResource extends KnowledgeResource {

	@Override
	default boolean isBasic() {
		return true;
	}

	boolean isAtomic();

}
