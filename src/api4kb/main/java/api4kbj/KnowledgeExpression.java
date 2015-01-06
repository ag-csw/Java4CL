package api4kbj;

import krconfigured.KnowledgeResource;
import elevation.Liftable;
import elevation.Lowerable;

public interface KnowledgeExpression extends KnowledgeResource, Liftable,
		Lowerable {

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.EXPRESSION;
	}

}
