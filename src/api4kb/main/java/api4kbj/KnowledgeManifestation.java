package api4kbj;

import java.util.Set;

import elevation.Liftable;
import elevation.Lowerable;

public interface KnowledgeManifestation extends KnowledgeResource, Liftable,
		Lowerable, Decomposable<KnowledgeManifestation> {

	KnowledgeSourceLevel level = KnowledgeSourceLevel.MANIFESTATION;

	// getter for dialects
	// TODO change type to immutable set
	Set<KRRDialect> dialects();

}
