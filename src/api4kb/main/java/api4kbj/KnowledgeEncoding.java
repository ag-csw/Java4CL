package api4kbj;

import elevation.Liftable;
import elevation.Lowerable;

public interface KnowledgeEncoding extends KnowledgeResource, Liftable,
		Lowerable {

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.ENCODING;
	}

}
