package api4kbja;

import java.util.HashSet;
import java.util.Set;

import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;

public abstract class AbstractBasicKnowledgeExpression implements
		BasicKnowledgeExpression {

	public AbstractBasicKnowledgeExpression(KRRLanguage language) {
		this.language = language;
		languages.add(language);
	}

	private KRRLanguage language;
	private Set<KRRLanguage> languages = new HashSet<KRRLanguage>();

	@Override
	public KRRLanguage language() {
		return language;
	}

}
