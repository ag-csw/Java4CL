package api4kbj;

import api4kbj7.ISource;

public interface Source extends ISource {
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
