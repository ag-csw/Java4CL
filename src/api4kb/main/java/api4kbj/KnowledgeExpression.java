package api4kbj;

import api4kbj7.IKnowledgeExpression;
import elevation.Liftable;
import elevation.Lowerable;

public interface KnowledgeExpression extends KnowledgeResource, Liftable,
		Lowerable, IKnowledgeExpression {


	@Override
	default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.EXPRESSION;
	}

}
