package krconfigured;

import api4kbj.Decomposable;
import api4kbj.KRRDialect;
import api4kbj.KnowledgeExpression;

public interface StructuredKnowledgeExpression extends KnowledgeExpression,
		StructuredKnowledgeResource, Decomposable<KnowledgeExpression> {

	// getter for languages
	Iterable<KRRDialect> languages();

}