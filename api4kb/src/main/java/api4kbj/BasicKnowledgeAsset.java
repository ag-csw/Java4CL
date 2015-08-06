package api4kbj;

import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = "http://www.omg.org/spec/API4KB/API4KBTerminology/BasicKnowledgeAsset")
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