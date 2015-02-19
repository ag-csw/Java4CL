package api4kbj;

public interface KRRLanguage extends ClassWrapper<KnowledgeExpression> {

	KRRLogic logic();

	@Override
	default Class<? extends KnowledgeExpression> asClass() {
		return KnowledgeExpression.class;
	}

}
