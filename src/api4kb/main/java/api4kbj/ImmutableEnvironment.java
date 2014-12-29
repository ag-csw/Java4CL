package api4kbj;

import java.util.Set;

import functional.Option;

public interface ImmutableEnvironment extends Immutable {

	// TODO change return type to Immutable Collection from FJ
	Set<KRRLanguage> languages();

	//
	Boolean containsLanguage(KRRLanguage lang);

	//
	Boolean isFocused();

	//
	KnowledgeExpression translate(KnowledgeExpression expression,
			KRRLanguage language);

	//
	KRRLanguage defaultLanguage();

	Option<KRRLanguage> focusLanguage();

}
