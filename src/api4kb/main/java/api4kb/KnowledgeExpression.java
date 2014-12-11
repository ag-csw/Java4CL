package api4kb;

public interface KnowledgeExpression extends KnowledgeResource {
	
	public KnowedgeSourceLevel level = KnowedgeSourceLevel.EXPRESSION;
			
	//getter for language
	KRRLanguage getLanguage();

}
