package hashenvironment;

import functional.Some;
import api4kba.AbstractKRRLanguage;
import api4kbj.FocusedLanguageEnvironment;
import api4kbj.KRRLanguage;

public class HashFocusedKRRLanguageEnvironment extends
		HashKRRLanguageEnvironment implements
		FocusedLanguageEnvironment {

	public HashFocusedKRRLanguageEnvironment(AbstractKRRLanguage lang) {
		super(lang);

	}

	@Override
	public Some<KRRLanguage> focusMember() {
		return (Some<KRRLanguage>) super.focusMember();
	}

}
