package de.fuberlin.csw.api4kp.owl2;

import api4kba.AbstractKRRLanguage;
import api4kba.AbstractKRRLogic;

/**
 * 
 * @author ralph
 */
public class OWL2 {
	
	public static AbstractKRRLogic OWL2_LOGIC = AbstractKRRLogic.logic("OWL 2 logic");
	
	public static AbstractKRRLanguage LANG = AbstractKRRLanguage.language(
			"OWL 2", OWL2ExpressionLike.class, OWL2_LOGIC);
	
	// private constructor to enforce non-instantiability
	private OWL2() {
	}
	
}
