/**
 * 
 */
package de.fuberlin.csw.api4kp.owl2;

import java.util.Set;

import org.semanticweb.owlapi.model.OWLAnnotation;

import api4kba.AbstractBasicKnowledgeExpressionLike;

/**
 * @author ralph
 */
public class OWL2AnnotationSet extends AbstractBasicKnowledgeExpressionLike {
	
	private Set<OWLAnnotation> annotations;

	/**
	 * @param language
	 */
	public OWL2AnnotationSet(Set<OWLAnnotation> annotations) {
		super(OWL2.LANG);
		this.annotations = annotations;
	}
	
}
