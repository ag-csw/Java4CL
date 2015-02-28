package api4kba;

import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;

public abstract class AbstractBasicKnowledgeExpression implements
		BasicKnowledgeExpression {

	public AbstractBasicKnowledgeExpression(final KRRLanguage language) {
		this.language = language;
	}

	private KRRLanguage language;
	
	@Override
	public KRRLanguage language() {
		return language;
	}

}
