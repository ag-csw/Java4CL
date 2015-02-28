package api4kbj;

public interface StructuredKnowledgeExpression extends KnowledgeExpression,
		StructuredKnowledgeResource<KnowledgeExpression> {

	Iterable<? extends KRRLanguage> languages();

	@Override
	default boolean usesLanguage(final KRRLanguage language) {
		for (KRRLanguage lang : languages()) {
			if (lang.equals(language))
				return true;
		}
		return false;
	}

}