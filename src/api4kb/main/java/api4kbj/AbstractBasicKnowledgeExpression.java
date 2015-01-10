package api4kbj;

import java.util.HashSet;
import java.util.Set;

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
