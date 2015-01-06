package api4kbj;

import krconfigured.KnowledgeResourceConfigured;
import elevation.Liftable;
import elevation.Lowerable;

/**
 * Interface of knowledge encodings, which are liftable and lowerable knowledge
 * resources at the {@link KnowledgeSourceLevel.ENCODING} abstract level.
 * 
 * @author taraathan
 *
 */
public interface KnowledgeEncoding extends KnowledgeResourceConfigured, Liftable,
		Lowerable {

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.ENCODING;
	}

}
