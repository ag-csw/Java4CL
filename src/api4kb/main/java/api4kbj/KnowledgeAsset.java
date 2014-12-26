package api4kbj;

import elevation.Lowerable;

public interface KnowledgeAsset extends KnowledgeResource, Lowerable {
	public KnowledgeSourceLevel level = KnowledgeSourceLevel.ASSET;

	// getter for environment
	ImmutableEnvironment getEnvironment();

}
