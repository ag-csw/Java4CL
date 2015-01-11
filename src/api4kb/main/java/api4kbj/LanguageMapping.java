package api4kbj;

public interface LanguageMapping<T extends KnowledgeExpression, R extends KnowledgeExpression> extends Mapping<T, R, KnowledgeExpression> {

	KRRLanguage startLanguage();
	
	KRRLanguage endLanguage();

	@Override
	R f(T arg);

}
