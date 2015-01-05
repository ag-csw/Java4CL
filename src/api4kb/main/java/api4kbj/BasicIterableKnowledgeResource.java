package api4kbj;

/**
 * A BasicKnowledgeResource which is composed of an iterable of
 * BasicKnowledgeResources.
 * 
 * @author taraathan
 *
 */
public interface BasicIterableKnowledgeResource extends BasicKnowledgeResource {

	@Override
	default boolean isAtomic() {
		return false;
	}

	Iterable<BasicKnowledgeResource> iterable();
}
