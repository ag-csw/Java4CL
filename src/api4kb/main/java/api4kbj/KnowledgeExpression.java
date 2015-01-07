package api4kbj;

import java.util.Set;

import elevation.Liftable;
import elevation.Lowerable;

public interface KnowledgeExpression extends KnowledgeResource, Liftable,
		Lowerable {

	@Override
	default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.EXPRESSION;
	}

	Set<KRRLanguage> languages();

}
