package api4kbj;

public interface StructuredKnowledgeExpression extends KnowledgeExpression,
		StructuredKnowledgeResource<KnowledgeExpression> {
	
	//private Iterable<? extends KnowledgeExpression> components;
	//private Iterable<? extends KRRLanguage> languages;
	
	Iterable<? extends KRRLanguage> languages();

	@Override
	public default boolean usesLanguage(final KRRLanguage language) {
		for (KRRLanguage lang : languages()) {
			if (lang.equals(language))
				return true;
		}
		return false;
	}

}