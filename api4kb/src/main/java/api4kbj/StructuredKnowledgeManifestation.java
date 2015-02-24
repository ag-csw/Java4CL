package api4kbj;

public interface StructuredKnowledgeManifestation extends
		KnowledgeManifestation,
		StructuredKnowledgeResource<KnowledgeManifestation> {

	// getter for dialects
	Iterable<? extends KRRDialect> dialects();

}