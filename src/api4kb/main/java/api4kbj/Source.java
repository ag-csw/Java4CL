package api4kbj;

public interface Source {
	/**
	 * Returns <tt>true</tt> if this knowledge resource is basic (not
	 * structured).
	 * 
	 * @return <tt>true</tt> if this knowledge resource is basic (not
	 *         structured)
	 * @see BasicKnowledgeResourceConfigured, StructuredKnowledgeResourceConfigured
	 */
	boolean isBasic();

}
