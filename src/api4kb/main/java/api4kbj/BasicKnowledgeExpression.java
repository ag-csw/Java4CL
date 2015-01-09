package api4kbj;

public interface BasicKnowledgeExpression extends KnowledgeExpression, BasicKnowledgeResource {

	/**
	 * Returns the unique language of the basic knowledge expression.
	 * 
	 * @return the language of the basic knowledge expression.
	 */
	KRRLanguage language();
	
	@Override
	default boolean usesLanguage(KRRLanguage language){
		return language().equals(language);
	}

}