package api4kbj;

import java.util.Set;

import elevation.Liftable;
import functional.IO;

public interface KnowledgeItem extends KnowledgeResource, Liftable {
	public KnowledgeSourceLevel level = KnowledgeSourceLevel.ITEM;

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.ITEM;
	}

	// getter for wrapped IO objects
	Set<IO<?>> values();

	// getter for destinations
	Set<?> destinations();

}
