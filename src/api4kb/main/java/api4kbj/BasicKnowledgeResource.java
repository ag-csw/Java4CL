package api4kbj;

import api4kbj7.IBasicKnowledgeResource;

public interface BasicKnowledgeResource extends KnowledgeResource, IBasicKnowledgeResource {

	@Override
	default boolean isBasic() {
		return true;
	}

}
