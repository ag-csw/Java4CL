package api4kb;

public class ImmutableEnvironment implements Immutable {

    public ImmutableEnvironment(KRRLanguage[] languages,
			LanguageMapping[] translations) {
		Languages = languages;
		Translations = translations;
	}
	private final KRRLanguage[] Languages;
    private final LanguageMapping[] Translations;
	public KRRLanguage[] getLanguages() {
		return Languages;
	}
	public LanguageMapping[] getTranslations() {
		return Translations;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
