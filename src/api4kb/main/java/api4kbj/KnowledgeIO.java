package api4kbj;

import elevation.Liftable;

public interface KnowledgeIO extends KnowledgeResource, Liftable {
	public KnowledgeSourceLevel level = KnowledgeSourceLevel.IO;

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.IO;
	}

}
