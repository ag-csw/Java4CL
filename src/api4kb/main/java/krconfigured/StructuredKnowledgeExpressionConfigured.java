package krconfigured;

import api4kbj.Decomposable;
import api4kbj.KnowledgeExpression;
import api4kbj.StructuredKnowledgeExpression;

public interface StructuredKnowledgeExpressionConfigured extends
		KnowledgeExpression, StructuredKnowledgeResourceConfigured,
		Decomposable<KnowledgeExpression>, StructuredKnowledgeExpression {

}
