package api4kbj;

import elevation.Liftable;
import elevation.Lowerable;

public interface KnowledgeManifestation extends KnowledgeResource, Liftable,
		Lowerable {

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.MANIFESTATION;
	}
	
	boolean usesDialectTypeEnvironment(DialectTypeEnvironment environment);

	boolean usesDialect(KRRDialect dialect);

}
