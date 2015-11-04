/**
 * 
 */
package de.fuberlin.csw.api4kp.owl2;

import api4kba.AbstractBasicKnowledgeExpressionLike;

/**
 * @author ralph
 * TODO there is a circular dependency between ExpressionLike and Language
 */
public class OWL2ExpressionLike extends AbstractBasicKnowledgeExpressionLike {

	/**
	 * @param language
	 */
	public OWL2ExpressionLike() {
		super(OWL2.LANG);
	}

}
