package api4kbj;

import elevation.Liftable;
import elevation.Lowerable;
import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = "http://www.omg.org/spec/API4KB/API4KBTerminology/KnowledgeManifestation")
public interface KnowledgeManifestation extends KnowledgeResource, Liftable,
		Lowerable {

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.MANIFESTATION;
	}
	
	boolean usesDialectTypeEnvironment(final DialectTypeEnvironment environment);

	boolean usesDialect(final KRRDialect dialect);

}
