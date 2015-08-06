package api4kbj;

public interface SourceLike {
	/**
	 * Returns <tt>true</tt> if this knowledge resource is basic (not
	 * structured).
	 * 
	 * @return <tt>true</tt> if this knowledge resource is basic (not
	 *         structured)
	 * @see BasicKnowledgeResourceConfigured,
	 *      StructuredKnowledgeResourceConfigured
	 */
	boolean isBasic();

}
