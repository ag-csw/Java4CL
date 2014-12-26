package api4kbj;

import java.util.Set;

public interface ImmutableEnvironment extends Immutable {

	// TODO change return type to Immutable Collection from FJ
	Set<KRRLanguage> languages();

	Set<KRRLanguage> focusLanguages();

	//
	Boolean containsLanguage(KRRLanguage lang);

	//
	Boolean isFocused();

	//
	KnowledgeExpression translate(KnowledgeExpression expression,
			KRRLanguage language);

	//
	KRRLanguage defaultLanguage();

	KnowledgeExpression apply(KnowledgeExpression expression,
			KRRLanguage endLanguage);

}
