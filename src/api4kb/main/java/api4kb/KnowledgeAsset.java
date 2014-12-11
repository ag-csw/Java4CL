package api4kb;

public interface KnowledgeAsset extends KnowledgeResource {
	public KnowedgeSourceLevel level = KnowedgeSourceLevel.ASSET;

	//getter for environment
	ImmutableEnvironment getEnvironment();


}
