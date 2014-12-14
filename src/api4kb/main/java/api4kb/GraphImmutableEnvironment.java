package api4kb;

import java.util.HashMap;
import java.util.HashSet;

public class GraphImmutableEnvironment implements
		ImmutableEnvironment {
	
	public static class Builder {
        // mutable properties to configure builder
		private KRRLanguage defaultLanguage;
		private HashSet<KRRLanguage> languages  = new HashSet<KRRLanguage>();
		private HashMap<KRRLanguagePair, LanguageMapping> translations = new HashMap<KRRLanguagePair, LanguageMapping>();
		private HashSet<KRRLanguage> focusLanguages;
		//
		public Builder() {}
		public void addLanguages(KRRLanguage... languages){
			for(KRRLanguage lang:languages) {
				this.languages.add(lang);
			}
		}
		public void setDefaultLanguage(KRRLanguage defaultLanguage){
			this.defaultLanguage = defaultLanguage;
			if (!languages.contains(defaultLanguage)) {
				addLanguages(defaultLanguage);
			}
		}
		
		public void addFocusLanguages(KRRLanguage... languages){
			for(KRRLanguage lang:languages) {
				this.focusLanguages.add(lang);
				this.languages.add(lang);
			}
		}

		public void addTranslations(LanguageMapping... translations){
			for(LanguageMapping map:translations) {
				this.translations.put(map.getPair(), map);
				// TOD Also add start and end languages to languages array, if
				// not already present.
				this.languages.add(map.startLanguage());
				this.languages.add(map.endLanguage());
			}
		}
		public GraphImmutableEnvironment build() {
			return new GraphImmutableEnvironment(this) {};
		}
	}

   
    private GraphImmutableEnvironment(Builder builder) {
		this.languages = builder.languages;
		this.focusLanguages = builder.focusLanguages;
		this.defaultLanguage = builder.defaultLanguage;
		this.translations = builder.translations;
	}
	private final HashSet<KRRLanguage> languages;
	private final HashSet<KRRLanguage> focusLanguages;
    private final HashMap<KRRLanguagePair, LanguageMapping> translations;
	private final KRRLanguage defaultLanguage;
    
	// TODO modify to change return type from array to immutable collection
	@Override
    public HashSet<KRRLanguage> getLanguages() {
		return languages;
	}

	// TODO modify to change return type from array to immutable collection
	public HashMap<KRRLanguagePair, LanguageMapping> getTranslations() {
		return translations;
	}

	@Override
	public Boolean containsLanguage(KRRLanguage lang) {
		//TODO change languages to a set because we never are interested in random access by index
        return languages.contains(lang);
	}

	@Override
	public Boolean isFocused() {
		return !focusLanguages.isEmpty();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public KnowledgeExpression translate(KnowledgeExpression expression,
			KRRLanguage language) throws UnsupportedTranslationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KRRLanguage getDefaultLanguage() {
		return defaultLanguage;
	}

	// TODO modify to change return type from array to immutable collection
	@Override
	public  HashSet<KRRLanguage> getFocusLanguages() {
		return focusLanguages;
	}
	
	@Override
	public KnowledgeExpression apply(KnowledgeExpression expression, KRRLanguage endLanguage) throws LanguageIncompatibleException{
        //check that the language of the expression is in the environment
		// TODO all expressions to be in multiple languages
		if (!languages.contains(expression.getLanguage())){
			throw new LanguageIncompatibleException("Language of input expression to apply is not supported in the environment.");
		}
		//check that the end language is in the environment
		if (!languages.contains(endLanguage)){
			throw new LanguageIncompatibleException("Language requested for apply is not supported in the environment.");
		}
		//TODO implement using methods of LanguageMapping
		return null;
	}


}
