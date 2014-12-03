package api4kb;

public interface ImmutableEnvironment extends Immutable {
	
	// TODO change return type to Immutable Collection from FJ
	public KRRLanguage[] getLanguages();
	//
    public Boolean containsLanguage(KRRLanguage lang);
    //
    public Boolean isFocused();
    //
    public KnowledgeExpression translate( KnowledgeExpression expression, KRRLanguage language)  throws UnsupportedTranslationException;

}
