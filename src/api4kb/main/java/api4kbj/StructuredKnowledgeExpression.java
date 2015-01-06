package api4kbj;


public interface StructuredKnowledgeExpression extends KnowledgeExpression,
		StructuredKnowledgeResource, Decomposable<KnowledgeExpression> {

	// getter for languages
	Iterable<KRRDialect> languages();

}