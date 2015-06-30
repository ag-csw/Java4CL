package api4kba;

import api4kbj.BasicKnowledgeExpressionLike;
import api4kbj.KRRLanguage;

public abstract class AbstractBasicKnowledgeExpressionLike implements
		BasicKnowledgeExpressionLike {

	public AbstractBasicKnowledgeExpressionLike(final KRRLanguage language) {
		this.language = language;
	}

	private KRRLanguage language;
	
	@Override
	public KRRLanguage language() {
		return language;
	}
	
	public static KRRLanguage language_(AbstractBasicKnowledgeExpressionLike ke){
		return ke.language();
	}

}
