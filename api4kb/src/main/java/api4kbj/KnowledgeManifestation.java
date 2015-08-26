package api4kbj;

import elevation.Liftable;
import elevation.Lowerable;

/**
 * 
 * @author taraathan
 * @api4kp.OntologyClass <a href="http://www.omg.org/spec/API4KB/API4KBTerminology/KnowledgeManifestation">API4KBTerminology/KnowledgeManifestation</a>
 */
public interface KnowledgeManifestation extends KnowledgeResource, Liftable,
		Lowerable {

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.MANIFESTATION;
	}
	
	boolean usesDialectTypeEnvironment(final DialectTypeEnvironment environment);

	boolean usesDialect(final KRRDialect dialect);

}
