package hashenvironment;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import functional.EquivalenceRelation;
import functional.Functional;
import functional.None;
import functional.Option;
import functional.Some;
import api4kbj.Immutable;
import api4kbj.KRRLanguage;
import api4kbj.KRRLanguageEnvironment;
import api4kbj.KnowledgeExpression;
import api4kbj.LanguageMapping;

public class HashKRRLanguageEnvironment implements
		KRRLanguageEnvironment {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public HashKRRLanguageEnvironment(final KRRLanguage lang) {
		this(init(lang));
	}

	public static Builder init(final KRRLanguage lang) {
		Builder builder = new Builder();
		builder.addLanguages(lang);
		builder.addFocusLanguage(lang);
		builder.addPreserves(Functional.ID);
		return builder;
	}

	public static class Builder {
		// mutable properties to configure builder
		private HashSet<KRRLanguage> languages = new HashSet<KRRLanguage>();
		private HashSet<LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> translations = new HashSet<LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>>();
		private Option<KRRLanguage> focusLanguage = new None<KRRLanguage>();
		private Option<EquivalenceRelation> preserves = new None<EquivalenceRelation>();

		//
		public Builder() {
		}

		public void addLanguages(final KRRLanguage... languages) {
			for (KRRLanguage lang : languages) {
				this.languages.add(lang);
			}
		}

		public void addFocusLanguage(final KRRLanguage language) {
			if (!this.languages.contains(language)) {
				throw new IllegalArgumentException("The language " + language
						+ " is not contained in the environment.");
			}
			// TODO verify that language is really a focus using OntoIOp
			this.focusLanguage = new Some<KRRLanguage>(language);
		}

		public void addPreserves(final EquivalenceRelation preserves) {
			this.preserves = new Some<EquivalenceRelation>(preserves);
		}

		public void addMappings(
				final Iterable<LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> translations) {
			for (LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> map : translations) {
				this.translations.add(map);
				this.languages.add(map.startLanguage());
				this.languages.add(map.endLanguage());
			}
		}

		public HashKRRLanguageEnvironment build() {
			return new HashKRRLanguageEnvironment(this) {
			};
		}

		public void addMapping(
				final LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> map) {
			this.translations.add(map);
			this.languages.add(map.startLanguage());
			this.languages.add(map.endLanguage());

		}
	}

	private HashKRRLanguageEnvironment(final Builder builder) {
		this.languages = (HashSet<KRRLanguage>) builder.languages.clone();
		this.focusLanguage = builder.focusLanguage.clone();
		this.translations = (Iterable<LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>>) builder.translations
				.clone();
		this.preserves = builder.preserves.clone();
	}

	private final Option<KRRLanguage> focusLanguage;
	// TODO replace HashSets with a graph data structure from some
	// library
	private final HashSet<KRRLanguage> languages;
	private final Iterable<LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> translations;
	private final Option<EquivalenceRelation> preserves;

	// TODO modify to change return type to immutable collection
	@Override
	public Iterable<KRRLanguage> members() {
		return languages;
	}

	@Override
	public boolean containsMember(final KRRLanguage lang) {
		return languages.contains(lang);
	}

	@Override
	public boolean isFocused() {
		return !focusLanguage.isEmpty();
	}

	@Override
	public Option<KRRLanguage> optionalFocusMember() {
		return focusLanguage;
	}

	@Override
	public Option<EquivalenceRelation> optionalPreserves() {
		return preserves;
	}

	@Override
	public Iterable<LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> mappings() {
		return translations;
	}

	@Override
	public boolean isPreserving() {
		return !preserves.isEmpty();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((focusLanguage == null) ? 0 : focusLanguage.hashCode());
		result = prime * result
				+ ((languages == null) ? 0 : languages.hashCode());
		result = prime * result
				+ ((preserves == null) ? 0 : preserves.hashCode());
		result = prime * result
				+ ((translations == null) ? 0 : translations.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashKRRLanguageEnvironment other = (HashKRRLanguageEnvironment) obj;
		if (focusLanguage == null) {
			if (other.focusLanguage != null)
				return false;
		} else if (!focusLanguage.equals(other.focusLanguage))
			return false;
		if (languages == null) {
			if (other.languages != null)
				return false;
		} else if (!languages.equals(other.languages))
			return false;
		if (preserves == null) {
			if (other.preserves != null)
				return false;
		} else if (!preserves.equals(other.preserves))
			return false;
		if (translations == null) {
			if (other.translations != null)
				return false;
		} else if (!translations.equals(other.translations))
			return false;
		return true;
	}

	/*
	 * @Override public boolean isCompatibleWith(Class<?> clazz) { if (clazz ==
	 * null) return false; for (KRRLanguage member: members()){ if
	 * (member.asClass().isAssignableFrom(clazz)) return true; } return false; }
	 */

	/*
	 * @Override boolean containsMapping( TODO implement using graph structure
	 * 1. Determine if there is a path between startLanguage and endLanguage 2.
	 * if so, return true
	 */

	/*
	 * @Override public KnowledgeExpression apply(KnowledgeExpression... TODO
	 * implement using graph structure 1. Determine the shortest path from
	 * startLanguage to endLanguage in the graph, if it exists 2. If path
	 * exists, successively apply the translations of the path 3. Otherwise,
	 * return IllegalArgumentException } TODO implement in case expression is
	 * not basic by translating all components
	 */

}
