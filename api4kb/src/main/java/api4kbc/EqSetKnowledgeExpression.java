package api4kbc;

import fj.F;
import functional.EqSet;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeExpression;

public abstract class EqSetKnowledgeExpression implements KnowledgeExpression {

	public EqSetKnowledgeExpression(EqSet<KRRLanguage> languages) {
		this.languages = languages;
	}

	private final EqSet<KRRLanguage> languages;

	public EqSet<KRRLanguage> languages() {
		return languages;
	}	
	
	public static EqSet<KRRLanguage> languages(EqSetKnowledgeExpression expression){
		return expression.languages();
	}

	//first-class version of languages()
	public static F<EqSetKnowledgeExpression, EqSet<KRRLanguage>> languages_(){
		return s -> languages(s);
	}
	
	@Override
	public boolean usesLanguage(KRRLanguage language){
		return languages.member(language);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((languages == null) ? 0 : languages.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EqSetKnowledgeExpression))
			return false;
		EqSetKnowledgeExpression other = (EqSetKnowledgeExpression) obj;
		if (languages == null) {
			if (other.languages != null)
				return false;
		} else if (!languages.equals(other.languages))
			return false;
		return true;
	}

}
