package krconfigured;


/**
 * A BasicKnowledgeResourceConfigured which is composed of an iterable of
 * BasicKnowledgeResources.
 * 
 * @author taraathan
 *
 */
public interface BasicIterableKnowledgeResourceConfigured extends BasicKnowledgeResourceConfigured {

	@Override
	default boolean isAtomic() {
		return false;
	}

	Iterable<BasicKnowledgeResourceConfigured> iterable();
}
