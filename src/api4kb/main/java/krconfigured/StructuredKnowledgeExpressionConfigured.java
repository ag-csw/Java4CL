package krconfigured;

import api4kbj.Decomposable;
import api4kbj.KnowledgeExpression;

public interface StructuredKnowledgeExpressionConfigured extends KnowledgeExpression,
		StructuredKnowledgeResourceConfigured, Decomposable<KnowledgeExpression>, StructuredKnowledgeExpression {

}
