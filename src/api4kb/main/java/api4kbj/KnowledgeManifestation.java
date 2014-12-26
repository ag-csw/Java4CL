package api4kbj;

import elevation.Liftable;
import elevation.Lowerable;

public interface KnowledgeManifestation extends KnowledgeResource, Liftable,
		Lowerable {
	KnowledgeSourceLevel level = KnowledgeSourceLevel.MANIFESTATION;

	// getter for dialect
	KRRDialect dialect();

	// getter for wrapped objects
	<T> T value();

}
