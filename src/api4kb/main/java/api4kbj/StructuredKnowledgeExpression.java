package api4kbj;

import api4kbj7.IKRRLanguage;

public interface StructuredKnowledgeExpression extends KnowledgeExpression,
		StructuredKnowledgeResource<KnowledgeExpression> {

	Iterable<KRRLanguage> languages();

	@Override
	default boolean usesLanguage(IKRRLanguage language) {
		for (IKRRLanguage lang : languages()) {
			if (lang.equals(language))
				return true;
		}
		return false;
	}

}