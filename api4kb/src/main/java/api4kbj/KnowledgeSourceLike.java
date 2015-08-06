package api4kbj;

import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = "http://www.omg.org/spec/API4KB/API4KBTerminology/KnowledgeSourceLike")
public interface KnowledgeSourceLike {
	/**
	 * Returns the abstraction level.
	 * 
	 * @return the abstraction level
	 * @see KnowledgeSourceLevel
	 */
	KnowledgeSourceLevel level();

}
