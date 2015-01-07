package api4kbj;

public abstract class AbstractBasicKnowledgeExpression implements
		BasicKnowledgeExpression {

	public AbstractBasicKnowledgeExpression(KRRLanguage language) {
		this.language = language;
	}

	private KRRLanguage language;

	@Override
	public KRRLanguage language() {
		return language;
	}

}
