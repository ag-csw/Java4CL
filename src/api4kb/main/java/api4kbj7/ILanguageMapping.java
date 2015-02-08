package api4kbj7;


public interface ILanguageMapping<T extends IKnowledgeExpression, R extends IKnowledgeExpression> extends IImmutable {

	IKRRLanguage startLanguage();

	IKRRLanguage endLanguage();

}