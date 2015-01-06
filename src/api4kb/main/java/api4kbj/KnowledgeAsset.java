package api4kbj;

import krconfigured.KnowledgeResourceConfigured;
import elevation.Lowerable;

/**
 * Interface for knowledge assets, which are lowerable knowledge resources in a
 * focused environment at the {@link KnowledgeSourceLevel.ASSET} abstraction
 * level.
 * 
 * @author taraathan
 *
 */
public interface KnowledgeAsset extends KnowledgeResourceConfigured, Lowerable {

	@Override
	public default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.ASSET;
	}

	/**
	 * Return the environment of the asset.
	 * 
	 * @return the environment of the asset
	 */
	FocusedImmutableEnvironment environment();

	/**
	 * Returns the canonical expression (i.e. expression in the focus language
	 * of the environment of the asset) of the asset.
	 * 
	 * @return the canonical expression of the asset
	 */
	KnowledgeExpression canonicalExpression();

}
