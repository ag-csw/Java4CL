package graphenvironment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import functional.Pair;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;
import api4kbj.ImmutableEnvironment;
import api4kbj.KnowledgeExpression;
import api4kbj.LanguageMapping;

public class GraphImmutableEnvironment implements ImmutableEnvironment {

	public static class Builder {
		// mutable properties to configure builder
		private KRRLanguage defaultLanguage;
		private HashSet<KRRLanguage> languages = new HashSet<KRRLanguage>();
		private HashMap<Pair<KRRLanguage>, LanguageMapping> translations = new HashMap<Pair<KRRLanguage>, LanguageMapping>();
		private HashSet<KRRLanguage> focusLanguages;

		//
		public Builder() {
		}

		public void addLanguages(KRRLanguage... languages) {
			for (KRRLanguage lang : languages) {
				this.languages.add(lang);
			}
		}

		public void setDefaultLanguage(KRRLanguage defaultLanguage) {
			this.defaultLanguage = defaultLanguage;
			if (!languages.contains(defaultLanguage)) {
				addLanguages(defaultLanguage);
			}
		}

		public void addFocusLanguages(KRRLanguage... languages) {
			for (KRRLanguage lang : languages) {
				this.focusLanguages.add(lang);
				this.languages.add(lang);
			}
		}

		public void addTranslations(LanguageMapping... translations) {
			for (LanguageMapping map : translations) {
				this.translations.put(new Pair<KRRLanguage>(
						map.startLanguage(), map.endLanguage()), map);
				this.languages.add(map.startLanguage());
				this.languages.add(map.endLanguage());
			}
		}

		public GraphImmutableEnvironment build() {
			return new GraphImmutableEnvironment(this) {
			};
		}
	}

	private GraphImmutableEnvironment(Builder builder) {
		this.languages = builder.languages;
		this.focusLanguages = builder.focusLanguages;
		this.defaultLanguage = builder.defaultLanguage;
		this.translations = builder.translations;
	}

	private final KRRLanguage defaultLanguage;
	private final HashSet<KRRLanguage> focusLanguages;
	// TODO replace HashSet+HashMap with a graph data structure from some
	// library
	private final HashSet<KRRLanguage> languages;
	private final HashMap<Pair<KRRLanguage>, LanguageMapping> translations;

	// TODO modify to change return type to immutable collection
	@Override
	public Set<KRRLanguage> languages() {
		return languages;
	}

	// TODO modify to change return type to immutable collection
	public Map<Pair<KRRLanguage>, LanguageMapping> translations() {
		return translations;
	}

	@Override
	public Boolean containsLanguage(KRRLanguage lang) {
		return languages.contains(lang);
	}

	@Override
	public Boolean isFocused() {
		return !focusLanguages.isEmpty();
	}

	@Override
	public KnowledgeExpression translate(KnowledgeExpression expression,
			KRRLanguage endLanguage) {
		if (!languages.contains(endLanguage)) {
			throw new IllegalArgumentException(
					"End language requested for translation is not supported in the environment.");
		}
		if (expression.isBasic()) {
			KRRLanguage startLanguage = ((BasicKnowledgeExpression) expression)
					.language();
			if (!languages.contains(startLanguage)) {
				throw new IllegalArgumentException(
						"Language of input expression to translate is not supported in the environment.");
			}
			// TODO implement using methods of LanguageMapping
			// 1. Determine the shortest path from startLanguage to endLanguage
			// in
			// the graph, if it exists
			// 2. If path exists, successively apply the translations of the
			// path
			// 3. Otherwise, return IllegalArgumentException
		}
		// TODO implement in case expression is not basic by translating all
		// components
		return null;
	}

	@Override
	public KRRLanguage defaultLanguage() {
		return defaultLanguage;
	}

	// TODO modify to change return type from array to immutable collection
	@Override
	public Set<KRRLanguage> focusLanguages() {
		return focusLanguages;
	}

	@Override
	public KnowledgeExpression apply(KnowledgeExpression expression,
			KRRLanguage endLanguage) {
		// check that the language of the expression is in the environment
		// TODO all expressions to be in multiple languages
		// check that the end language is in the environment
		return null;
	}

}
