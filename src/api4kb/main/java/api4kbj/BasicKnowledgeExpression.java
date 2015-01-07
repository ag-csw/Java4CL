package api4kbj;

public interface BasicKnowledgeExpression extends KnowledgeExpression, BasicKnowledgeResource {

	/**
	 * Returns the unique language of the basic knowledge expression.
	 * 
	 * @return the language of the basic knowledge expression.
	 */
	KRRLanguage language();
	
}