package graphenvironment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import functional.None;
import functional.Option;
import functional.Pair;
import functional.Some;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;
import api4kbj.ImmutableEnvironment;
import api4kbj.KnowledgeExpression;
import api4kbj.LanguageMapping;

public class GraphImmutableEnvironment implements ImmutableEnvironment {

	public GraphImmutableEnvironment(KRRLanguage lang) {
		languages = new HashSet<KRRLanguage>();
		languages.add(lang);
		focusLanguage = new Some<KRRLanguage>(lang);
		defaultLanguage = lang;
		this.translations = new HashMap<Pair<KRRLanguage>, LanguageMapping>();
		;

	}

	static Builder init(KRRLanguage lang) {
		Builder builder = new Builder();
		builder.addLanguages(lang);
		builder.addFocusLanguage(lang);
		return builder;
	}

	public static class Builder {
		// mutable properties to configure builder
		private KRRLanguage defaultLanguage;
		private HashSet<KRRLanguage> languages = new HashSet<KRRLanguage>();
		private HashMap<Pair<KRRLanguage>, LanguageMapping> translations = new HashMap<Pair<KRRLanguage>, LanguageMapping>();
		private Option<KRRLanguage> focusLanguage = new None<KRRLanguage>();

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

		public void addFocusLanguage(KRRLanguage language) {
			if (!this.languages.contains(language)) {
				throw new IllegalArgumentException("The language " + language
						+ " is not contained in the environment.");
			}
			// TODO verify that language is really a focus using OntoIOp
			this.focusLanguage = new Some<KRRLanguage>(language);
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
		this.focusLanguage = builder.focusLanguage;
		this.defaultLanguage = builder.defaultLanguage;
		this.translations = builder.translations;
	}

	private final KRRLanguage defaultLanguage;
	private final Option<KRRLanguage> focusLanguage;
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
	public boolean containsLanguage(KRRLanguage lang) {
		return languages.contains(lang);
	}

	@Override
	public boolean isFocused() {
		return !focusLanguage.isEmpty();
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

	@Override
	public Option<KRRLanguage> focusLanguage() {
		return focusLanguage;
	}

	public boolean containsLanguages(Iterable<KRRLanguage> langs) {
		for (KRRLanguage lang : langs) {
			if (!this.containsLanguage(lang)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean contains(ImmutableEnvironment env) {
		if (env == null) {
			return false;
		}
		if (!this.containsLanguages(env.languages())) {
			return false;
		}
		if (!this.focusLanguage().equals(env.focusLanguage())) {
			return false;
		}
		// TODO implement check of mappings in case of non-focused
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((defaultLanguage == null) ? 0 : defaultLanguage.hashCode());
		result = prime * result
				+ ((focusLanguage == null) ? 0 : focusLanguage.hashCode());
		result = prime * result
				+ ((languages == null) ? 0 : languages.hashCode());
		result = prime * result
				+ ((translations == null) ? 0 : translations.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof GraphImmutableEnvironment)) {
			return false;
		}
		GraphImmutableEnvironment other = (GraphImmutableEnvironment) obj;
		if (defaultLanguage == null) {
			if (other.defaultLanguage != null) {
				return false;
			}
		} else if (!defaultLanguage.equals(other.defaultLanguage)) {
			return false;
		}
		if (focusLanguage == null) {
			if (other.focusLanguage != null) {
				return false;
			}
		} else if (!focusLanguage.equals(other.focusLanguage)) {
			return false;
		}
		if (languages == null) {
			if (other.languages != null) {
				return false;
			}
		} else if (!languages.equals(other.languages)) {
			return false;
		}
		if (translations == null) {
			if (other.translations != null) {
				return false;
			}
		} else if (!translations.equals(other.translations)) {
			return false;
		}
		return true;
	}

}
