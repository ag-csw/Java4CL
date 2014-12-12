package api4kb;

import elevation.Liftable;
import elevation.Lowerable;

public interface KnowledgeExpression extends KnowledgeResource, Liftable, Lowerable {
	
	public KnowledgeSourceLevel level = KnowledgeSourceLevel.EXPRESSION;
			
	//getter for language
	KRRLanguage getLanguage();

}
