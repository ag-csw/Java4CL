package api4kbj;

/**
 * A BasicKnowledgeExpression is a knowledge resource at the expression
 * abstraction level that is not structured. As a consequence, it has a unique
 * language.
 * 
 * @author taraathan
 *
 */
public interface BasicKnowledgeExpression extends KnowledgeExpression,
		BasicKnowledgeResource {

	/**
	 * Returns the unique language of the basic knowledge expression.
	 * 
	 * @return the language of the basic knowledge expression.
	 */
	KRRLanguage language();

}
