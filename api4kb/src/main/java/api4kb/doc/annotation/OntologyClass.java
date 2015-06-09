package api4kb.doc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates which OWL class in the API4KB ontology this class corresponds to.
 * @author ralph
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface OntologyClass {
	
	/**
	 * An IRI identifying the ontology class the annotation subject corresponds to.
	 * @return
	 */
	String value();

}
