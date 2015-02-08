package api4kbj;

import api4kbj7.ILanguageMapping;

public interface LanguageMapping<T extends KnowledgeExpression, R extends KnowledgeExpression>
		extends Mapping<T, R>, ILanguageMapping<T, R> {
	
	@Override
	KRRLanguage startLanguage();

	@Override
	KRRLanguage endLanguage();

}
