package api4kbc;

import fj.data.Set;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeExpression;

public abstract class FJSetKnowledgeExpression implements KnowledgeExpression {

	public FJSetKnowledgeExpression(Set<KRRLanguage> languages) {
		this.languages = languages;
	}

	private Set<KRRLanguage> languages;

	public Set<KRRLanguage> languages() {
		return languages;
	}
	
	public static Set<KRRLanguage> languages(FJSetKnowledgeExpression expression){
		return expression.languages();
	}
	
	@Override
	public boolean usesLanguage(KRRLanguage language){
		return languages.member(language);
	}

}
