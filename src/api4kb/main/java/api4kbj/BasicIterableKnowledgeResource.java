package api4kbj;

import krconfigured.BasicKnowledgeResourceConfigured;

/**
 * A BasicKnowledgeResourceConfigured which is composed of an iterable of
 * BasicKnowledgeResources.
 * 
 * @author taraathan
 *
 */
public interface BasicIterableKnowledgeResource extends BasicKnowledgeResourceConfigured {

	@Override
	default boolean isAtomic() {
		return false;
	}

	Iterable<BasicKnowledgeResourceConfigured> iterable();
}
