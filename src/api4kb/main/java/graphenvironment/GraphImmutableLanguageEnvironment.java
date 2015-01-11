package graphenvironment;

import java.util.HashSet;

import functional.None;
import functional.Option;
import functional.Some;
import api4kbj.ImmutableEnvironment;
import api4kbj.KRRLanguage;
import api4kbj.ImmutableLanguageEnvironment;
import api4kbj.KnowledgeExpression;
import api4kbj.LanguageMapping;
import api4kbj.Mapping;

public class GraphImmutableLanguageEnvironment implements
		ImmutableLanguageEnvironment {

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

	@Override
	public boolean containsMembers(Iterable<? extends KRRLanguage> t) {
		for (KRRLanguage lang : t) {
			if (!this.containsMember(lang)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean containsMapping(
			Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression, KnowledgeExpression> t) {
		for (LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> map : translations) {
			if (map.equals(t)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsMappings(
			Iterable<? extends Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression, KnowledgeExpression>> t) {
		for (Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression, KnowledgeExpression> map : t) {
			if (this.containsMapping(map)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public <T1 extends KRRLanguage, S1 extends KnowledgeExpression, R extends ImmutableEnvironment<T1, S1>> boolean contains(
			R other) {
		if (other == null) {
			return false;
		}
		if (!this.containsMembers(other.members())) {
			return false;
		}
		Iterable<? extends Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression, KnowledgeExpression>> otherMappings = (Iterable<? extends Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression, KnowledgeExpression>>) other.mappings();
		if (!this.containsMappings(otherMappings)) {
			return false;
		}
		return true;
	}
	
}
