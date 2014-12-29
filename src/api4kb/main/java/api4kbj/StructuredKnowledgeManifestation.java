package api4kbj;

import java.util.Set;

public interface StructuredKnowledgeManifestation extends
		KnowledgeManifestation, StructuredKnowledgeResource,
		Decomposable<KnowledgeManifestation> {

	// getter for dialects
	// TODO change type to immutable set
	Set<KRRDialect> dialects();

}
