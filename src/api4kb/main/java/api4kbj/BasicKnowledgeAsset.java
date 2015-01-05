package api4kbj;

/**
 * Interface for basic knowledge assets: knowledge resources at the asset
 * abstraction level that are not structured. As a consequence, a basic
 * knowledge asset has a canonical expression (i.e. expression of the asset in
 * the focus language of its environment) that is also basic.
 * 
 * @author taraathan
 *
 */
public interface BasicKnowledgeAsset extends KnowledgeAsset,
		BasicKnowledgeResource {

	@Override
	/**
	 * Returns the canonical expression of the asset.
	 * 
	 * @return the canonical expression of the asset
	 */
	BasicKnowledgeExpression canonicalExpression();

}
