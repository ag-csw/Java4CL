package api4kbj;

import elevation.Liftable;
import functional.IO;

public interface KnowledgeItem extends KnowledgeResource, Liftable {
	public KnowledgeSourceLevel level = KnowledgeSourceLevel.ITEM;

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.ITEM;
	}

	// getter for wrapped IO objects
	Iterable<IO<?>> values();

	// getter for destinations
	Iterable<?> destinations();

}
