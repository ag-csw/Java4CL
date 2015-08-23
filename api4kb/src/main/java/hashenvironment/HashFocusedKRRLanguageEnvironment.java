package hashenvironment;

import functional.Some;
import api4kba.AbstractKRRLanguage;
import api4kbj.FocusedLanguageEnvironment;
import api4kbj.KRRLanguage;

public class HashFocusedKRRLanguageEnvironment extends
		HashKRRLanguageEnvironment implements
		FocusedLanguageEnvironment {

	public HashFocusedKRRLanguageEnvironment(final AbstractKRRLanguage lang) {
		super(lang);

	}

	@Override
	public Some<KRRLanguage> optionalFocusMember() {
		return (Some<KRRLanguage>) super.optionalFocusMember();
	}

	@Override
	public KRRLanguage focusMember() {
		return optionalFocusMember().value();
	}

}
