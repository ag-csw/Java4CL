package graphenvironment;

import java.util.HashSet;

import functional.None;
import functional.Option;
import functional.Some;
import api4kbj.KRRLanguage;
import api4kbj.ImmutableLanguageEnvironment;
import api4kbj.KnowledgeExpression;
import api4kbj.LanguageMapping;

public class GraphImmutableLanguageEnvironment implements
		ImmutableLanguageEnvironment {

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof GraphImmutableLanguageEnvironment)) {
			return false;
		}
		GraphImmutableLanguageEnvironment other = (GraphImmutableLanguageEnvironment) obj;
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

	public GraphImmutableLanguageEnvironment(KRRLanguage lang) {
		this(init(lang));
	}
	
	public static Builder init(KRRLanguage lang) {
		Builder builder = new Builder();
		builder.addLanguages(lang);
		builder.setDefaultLanguage(lang);
		builder.addFocusLanguage(lang);
		return builder;
	}

	public static class Builder {
		// mutable properties to configure builder
		private KRRLanguage defaultLanguage;
		private HashSet<KRRLanguage> languages = new HashSet<KRRLanguage>();
		private HashSet<LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> translations = new HashSet<LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>>();
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

		public <T extends KnowledgeExpression, S extends KnowledgeExpression> void addMappings(
				Iterable<LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> translations) {
			for (LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> map : translations) {
				this.translations.add(map);
				this.languages.add(map.startLanguage());
				this.languages.add(map.endLanguage());
			}
		}

		public GraphImmutableLanguageEnvironment build() {
			return new GraphImmutableLanguageEnvironment(this) {
			};
		}

		public void addMapping(
				LanguageMapping<KnowledgeExpression, KnowledgeExpression> map) {
			this.translations.add(map);
			this.languages.add(map.startLanguage());
			this.languages.add(map.endLanguage());
			
		}
	}

	private GraphImmutableLanguageEnvironment(Builder builder) {
		this.languages = builder.languages;
		this.focusLanguage = builder.focusLanguage;
		this.defaultLanguage = builder.defaultLanguage;
		this.translations = builder.translations;
	}

	private final KRRLanguage defaultLanguage;
	private final Option<KRRLanguage> focusLanguage;
	// TODO replace HashSets with a graph data structure from some
	// library
	private final HashSet<KRRLanguage> languages;
	private final Iterable<LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> translations;

	// TODO modify to change return type to immutable collection
	@Override
	public Iterable<KRRLanguage> members() {
		return languages;
	}

	@Override
	public boolean containsMember(KRRLanguage lang) {
		return languages.contains(lang);
	}

	@Override
	public boolean isFocused() {
		return !focusLanguage.isEmpty();
	}

	/*
	 * @Override public KnowledgeExpression applyMapping(KnowledgeExpression
	 * expression, KRRLanguage endLanguage) { if
	 * (!languages.contains(endLanguage)) { throw new IllegalArgumentException(
	 * "End language requested for translation is not supported in the environment."
	 * ); } if (expression.isBasic()) { KRRLanguage startLanguage =
	 * ((BasicKnowledgeExpression) expression) .language(); if
	 * (!languages.contains(startLanguage)) { throw new
	 * IllegalArgumentException(
	 * "Language of input expression to translate is not supported in the environment."
	 * ); } // TODO implement using methods of LanguageMapping // 1. Determine
	 * the shortest path from startLanguage to endLanguage // in // the graph,
	 * if it exists // 2. If path exists, successively apply the translations of
	 * the // path // 3. Otherwise, return IllegalArgumentException } // TODO
	 * implement in case expression is not basic by translating all //
	 * components return null; }
	 */

	@Override
	public KRRLanguage defaultMember() {
		return defaultLanguage;
	}

	@Override
	public Option<KRRLanguage> focusMember() {
		return focusLanguage;
	}


	@Override
	public Iterable<LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> mappings() {
		return translations;
	}

   /*
	@Override
	public boolean containsMapping(
			Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> t) {
		for (LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> map : mappings()) {
			if (map.equals(t)) {
				return true;
			}
		}
		return false;
	}*/


	
}
