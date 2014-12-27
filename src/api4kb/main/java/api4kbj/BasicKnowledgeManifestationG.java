package api4kbj;

import elevation.Liftable;
import elevation.Lowerable;

public interface BasicKnowledgeManifestationG<T> extends KnowledgeResource,
		Liftable, Lowerable {
	KnowledgeSourceLevel level = KnowledgeSourceLevel.MANIFESTATION;

	// getter for wrapped object
	T value();

	// getter for dialect type
	KRRDialectType<T> dialectType();

	Class<T> type();

}
