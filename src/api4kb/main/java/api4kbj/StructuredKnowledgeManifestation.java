package api4kbj;

public interface StructuredKnowledgeManifestation extends
		KnowledgeManifestation, StructuredKnowledgeResource,
		Decomposable<KnowledgeManifestation> {

	// getter for dialects
	Iterable<KRRDialect> dialects();

}
