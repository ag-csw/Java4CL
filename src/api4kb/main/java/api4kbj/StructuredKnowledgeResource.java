package api4kbj;

import api4kbj7.IDecomposable;
import api4kbj7.IStructuredKnowledgeResource;

public interface StructuredKnowledgeResource<T extends KnowledgeResource>
		extends KnowledgeResource, IDecomposable<T>, IStructuredKnowledgeResource<T> {

	@Override
	default boolean isBasic() {
		return false;
	}

}