package api4kbj;

import api4kba.AbstractKRRLogic;

public interface KRRLanguage extends ClassWrapper<KnowledgeExpression> {

	AbstractKRRLogic logic();

	@Override
	default Class<? extends KnowledgeExpression> asClass() {
		return KnowledgeExpression.class;
	}

}
