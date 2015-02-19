package api4kbj;

public interface StructuredKnowledgeExpression extends KnowledgeExpression,
		StructuredKnowledgeResource<KnowledgeExpression> {

	Iterable<KRRLanguage> languages();

	@Override
	default boolean usesLanguage(KRRLanguage language) {
		for (KRRLanguage lang : languages()) {
			if (lang.equals(language))
				return true;
		}
		return false;
	}

}