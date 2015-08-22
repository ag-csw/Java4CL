package api4kbj;

/**
 * equivalence class of basic expressions determined by the equivalence relation of an asset environment
 * 
 * @author taraathan
 * @api4kp.OntologyClass <a href="http://www.omg.org/spec/API4KB/API4KBTerminology/BasicKnowledgeAsset">API4KBTerminology/BasicKnowledgeAsset</a>
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