package api4kbj;

import api4kba.AbstractKRRLogic;

public interface KRRLanguage extends ClassWrapper<KnowledgeExpressionLike> {

	AbstractKRRLogic logic();

	@Override
	default Class<? extends KnowledgeExpressionLike> asClass() {
		return KnowledgeExpressionLike.class;
	}

}
