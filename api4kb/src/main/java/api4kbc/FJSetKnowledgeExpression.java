package api4kbc;

import fj.F;
import fj.data.Set;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeExpression;

public abstract class FJSetKnowledgeExpression implements KnowledgeExpression {

	public FJSetKnowledgeExpression(final Set<KRRLanguage> languages) {
		this.languages = languages;
	}

	private final Set<KRRLanguage> languages;

	public Set<KRRLanguage> languages() {
		return languages;
	}	
	
	public static Set<KRRLanguage> languages(final FJSetKnowledgeExpression expression){
		return expression.languages();
	}

	//first-class version of languages()
	public static F<FJSetKnowledgeExpression, Set<KRRLanguage>> languages_(){
		return s -> languages(s);
	}
	
	@Override
	public boolean usesLanguage(final KRRLanguage language){
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
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FJSetKnowledgeExpression))
			return false;
		FJSetKnowledgeExpression other = (FJSetKnowledgeExpression) obj;
		if (languages == null) {
			if (other.languages != null)
				return false;
		} else if (!languages.equals(other.languages))
			return false;
		return true;
	}

}
