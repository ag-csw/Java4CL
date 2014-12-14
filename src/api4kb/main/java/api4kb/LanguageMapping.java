package api4kb;

public interface LanguageMapping extends Mapping<KRRLanguage> {

	KRRLanguage startLanguage();

	KRRLanguage endLanguage();

	KRRLanguagePair getPair();

}
