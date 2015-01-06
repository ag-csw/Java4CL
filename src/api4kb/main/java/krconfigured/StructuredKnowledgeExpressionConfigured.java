package krconfigured;

import api4kbj.Decomposable;
import api4kbj.KRRDialect;
import api4kbj.KnowledgeExpression;

public interface StructuredKnowledgeExpressionConfigured extends KnowledgeExpression,
		StructuredKnowledgeResourceConfigured, Decomposable<KnowledgeExpression> {

	// getter for languages
	Iterable<KRRDialect> languages();

}
