/**
 * 
 */
package de.fuberlin.csw.api4kp.owl2;

/**
 * @author ralph
 */
public interface OWL2Annotatable {
	public OWL2AnnotationSet annotations();
	
	public OWL2Annotatable insertAnnotations(OWL2AnnotationSet annotations);
}
