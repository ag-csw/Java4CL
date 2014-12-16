package api4kb;

import java.util.HashSet;

public interface ImmutableEnvironment extends Immutable {
	
	// TODO change return type to Immutable Collection from FJ
	HashSet<KRRLanguage> getLanguages();
	HashSet<KRRLanguage> getFocusLanguages();
	//
    Boolean containsLanguage(KRRLanguage lang);
    //
    Boolean isFocused();
    //
    KnowledgeExpression translate( KnowledgeExpression expression, KRRLanguage language)  throws IllegalArgumentException;
    //
    KRRLanguage getDefaultLanguage();
	KnowledgeExpression apply(KnowledgeExpression expression,
			KRRLanguage endLanguage) throws LanguageIncompatibleException;

}
