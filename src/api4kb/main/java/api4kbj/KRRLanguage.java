package api4kbj;

import api4kbj7.IKRRLanguage;

public interface KRRLanguage extends ClassWrapper<KnowledgeExpression>, IKRRLanguage {

	@Override
	default Class<? extends KnowledgeExpression> asClass() {
		return KnowledgeExpression.class;
	}

}
