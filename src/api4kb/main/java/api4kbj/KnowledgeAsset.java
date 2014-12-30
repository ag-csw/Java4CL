package api4kbj;

import elevation.Lowerable;

/**
 * Interface for knowledge assets, which are lowerable knowledge resources in an
 * environment at the {@link KnowledgeSourceLevel.ASSET} abstraction level.
 * 
 * @author taraathan
 *
 */
public interface KnowledgeAsset extends KnowledgeResource, Lowerable {
	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.ASSET;
	}

	/**
	 * Return the environment of the asset.
	 * 
	 * @return the environment of the asset
	 */
	ImmutableEnvironment environment();

}
