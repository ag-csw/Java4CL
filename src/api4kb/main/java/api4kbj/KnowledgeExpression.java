package api4kbj;

import krconfigured.KnowledgeResourceConfigured;
import elevation.Liftable;
import elevation.Lowerable;

public interface KnowledgeExpression extends KnowledgeResourceConfigured, Liftable,
		Lowerable {

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.EXPRESSION;
	}

}
