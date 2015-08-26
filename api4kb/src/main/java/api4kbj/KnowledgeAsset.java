package api4kbj;

import elevation.Lowerable;

/**
 * Interface for knowledge assets, which are lowerable knowledge resources in a
 * focused preserving environment at the {@link KnowledgeSourceLevel.ASSET} abstraction
 * level.

 * @author taraathan
 * @api4kp.OntologyClass <a href="http://www.omg.org/spec/API4KB/API4KBTerminology/KnowledgeAsset">API4KBTerminology/KnowledgeAsset</a>
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
	FocusedLanguageEnvironment environment();

	/**
	 * Returns the canonical expression (that is, the expression in the focus language of the environment of the asset) of the asset.
	 * 
	 * @return the canonical expression of the asset
	 */
	KnowledgeExpression canonicalExpression();

	public default boolean compatibleWith(
			final FocusedLanguageEnvironment environment) {
		return environment.contains(environment());
	}

	public default boolean conceptualizes(final KnowledgeExpression e) {
		if (canonicalExpression().equals(e)) {
			return true;
		}
		// TODO implement test based on equivalence relation that defines the
		// asset
		return false;
	}

}
