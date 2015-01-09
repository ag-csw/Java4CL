package api4kbj;

import elevation.Liftable;
import elevation.Lowerable;

public interface KnowledgeExpression extends KnowledgeResource, Liftable,
		Lowerable {

	@Override
	default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.EXPRESSION;
	}

	boolean usesLanguage(KRRLanguage language);


}
