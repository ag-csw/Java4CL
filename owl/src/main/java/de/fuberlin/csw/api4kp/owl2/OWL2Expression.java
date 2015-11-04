package de.fuberlin.csw.api4kp.owl2;

import org.semanticweb.owlapi.model.OWLObject;

import api4kba.AbstractBasicKnowledgeExpression;
import api4kbj.BasicKnowledgeExpression;

/**
 * 
 * @author ralph
 * 
 * TODO There is a CL2 commentable -> api4kb?
 */
public abstract class OWL2Expression<T extends OWLObject> extends AbstractBasicKnowledgeExpression implements OWL2KnowledgeResource, BasicKnowledgeExpression , OWL2Annotatable {
	
	private T target;
	
	private OWL2AnnotationSet annotations;
	
	public OWL2Expression(
			final OWL2AnnotationSet annotations) { // TODO this will probably be read from the owl object
		super(OWL2.LANG);
		if(annotations==null)
			throw new NullPointerException("Comments of an OWL2Expression should not be null.");
		this.annotations = annotations;
		
	}
	
	public OWLObject getTarget() {
		return target;
	}
	
	/* (non-Javadoc)
	 * @see de.fuberlin.csw.api4kp.owl2.OWL2Annotatable#annotations()
	 * TODO 
	 */
	@Override
	public OWL2AnnotationSet annotations() {
		return annotations;
	}
}
