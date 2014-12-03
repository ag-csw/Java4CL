package api4kb;

public class GraphImmutableEnvironment implements
		ImmutableEnvironment {
	
	public static class Builder {
		//optional parameters
		private KRRLanguage[] languages  = new KRRLanguage[0];
		private LanguageMapping[] translations = new LanguageMapping[0];
		//
		public Builder() {}
		public void addLanguages(KRRLanguage... languages){
			// TODO append languages to this.languages
		}
		public void addTranslations(LanguageMapping... translations){
			// TODO append translations to this.translations
			// Also add start and end languages to languages array, if
			// not already present.
		}
		public GraphImmutableEnvironment build() {
			return new GraphImmutableEnvironment(this) {};
		}
	}
   
    private GraphImmutableEnvironment(Builder builder) {
		this.languages = builder.languages;
		this.translations = builder.translations;
	}
	private final KRRLanguage[] languages;
    private final LanguageMapping[] translations;
    
	// TODO modify to change return type from array to immutable collection
	@Override
    public KRRLanguage[] getLanguages() {
		return languages;
	}

	// TODO modify to change return type from array to immutable collection
	public LanguageMapping[] getTranslations() {
		return translations;
	}

	@Override
	public Boolean containsLanguage(KRRLanguage lang) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isFocused() {
		// TODO Auto-generated method stub
		return null;
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


}
