package api4kbj;

import api4kbj7.IKRRLanguage;



public interface BasicKnowledgeExpression extends KnowledgeExpression,
		BasicKnowledgeResource {

	/**
	 * Returns the unique language of the basic knowledge expression.
	 * 
	 * @return the language of the basic knowledge expression.
	 */
	KRRLanguage language();

	@Override
	default boolean usesLanguage(IKRRLanguage language) {
		return language().equals(language);
	}

}