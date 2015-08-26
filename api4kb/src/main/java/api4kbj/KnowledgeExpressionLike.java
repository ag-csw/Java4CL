package api4kbj;

public interface KnowledgeExpressionLike extends KnowledgeSourceLike {

	//EquivalenceRelation ID = new EquivalenceRelation();

	@Override
	default KnowledgeSourceLevel level() {
		return KnowledgeSourceLevel.EXPRESSION;
	}

	boolean usesLanguage(final KRRLanguage language);

}
