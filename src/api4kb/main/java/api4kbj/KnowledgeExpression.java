package api4kbj;

import elevation.Liftable;
import elevation.Lowerable;
import java.util.Set;

public interface KnowledgeExpression extends KnowledgeResource, Liftable,
		Lowerable, Decomposable<KnowledgeExpression> {

	public KnowledgeSourceLevel level = KnowledgeSourceLevel.EXPRESSION;

	// getter for languages
	// TODO change type to immutable set
	Set<KRRLanguage> languages();

}
