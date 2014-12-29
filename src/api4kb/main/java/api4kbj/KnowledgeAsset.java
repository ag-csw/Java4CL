package api4kbj;

import elevation.Lowerable;

public interface KnowledgeAsset extends KnowledgeResource, Lowerable {

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.ASSET;
	}

	// getter for environment
	ImmutableEnvironment environment();

}
