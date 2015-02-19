package krconfigured;

import api4kbj.BasicKnowledgeAsset;

/**
 * Interface for basic knowledge assets: knowledge resources at the asset
 * abstraction level that are not structured. As a consequence, a basic
 * knowledge asset has a canonical expression (i.e. expression of the asset in
 * the focus language of its environment) that is also basic.
 * 
 * @author taraathan
 *
 */
public interface BasicKnowledgeAssetConfigured extends
		BasicKnowledgeResourceConfigured, BasicKnowledgeAsset {

}
