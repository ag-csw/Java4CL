package api4kbj;

import elevation.Liftable;
import functional.IO;

public interface KnowledgeIO extends KnowledgeResource, Liftable {
	public KnowledgeSourceLevel level = KnowledgeSourceLevel.IO;

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.IO;
	}

	// getter for wrapped IO objects
	Iterable<IO<?>> values();

	// getter for destinations
	Iterable<?> destinations();

}
