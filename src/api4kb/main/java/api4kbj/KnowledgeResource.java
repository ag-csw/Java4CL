package api4kbj;

import krconfigured.KnowledgeSource;

/**
 * Interface for API4KB knowledge resources, which are immutable knowledge
 * sources.
 * 
 * @author taraathan
 *
 */
public interface KnowledgeResource extends ImmutableSource, KnowledgeSource {

	/**
	 * Returns the abstraction level.
	 * 
	 * @return the abstraction level
	 * @see KnowledgeAsset, KnowledgeExpression, KnowledgeManifestation,
	 *      KnowledgeEncoding, KnowledgeItem
	 */
	KnowledgeSourceLevel level();

	/**
	 * Returns <tt>true</tt> if this knowledge resource is basic (not
	 * structured).
	 * 
	 * @return <tt>true</tt> if this knowledge resource is basic (not
	 *         structured)
	 * @see BasicKnowledgeResource, StructuredKnowledgeResource
	 */
	boolean isBasic();

}
