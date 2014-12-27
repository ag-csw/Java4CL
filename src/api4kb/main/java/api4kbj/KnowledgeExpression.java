package api4kbj;

import elevation.Liftable;
import elevation.Lowerable;
import java.util.Set;

public interface KnowledgeExpression extends KnowledgeResource, Liftable,
		Lowerable {

	public KnowledgeSourceLevel level = KnowledgeSourceLevel.EXPRESSION;

	// getter for languages
	// TODO change type to immutable set
	Set<KRRLanguage> languages();

	// getter for component expressions
	// if empty, is a BasicKnowledgeExpression
	// TODO change type to immutable set
	Set<KnowledgeExpression> components();
	
	// check if basic
	Boolean isBasic();

}
