package krconfigured;

import api4kbj.KnowledgeExpression;
import api4kbj.StructuredKnowledgeExpression;

public interface StructuredKnowledgeExpressionConfigured<T extends KnowledgeExpression> extends
		KnowledgeExpression, StructuredKnowledgeResourceConfigured<T>,
		StructuredKnowledgeExpression<T> {

}
