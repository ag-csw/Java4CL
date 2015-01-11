package graphenvironment;

import functional.Some;
import api4kbj.AbstractKRRLanguage;
import api4kbj.FocusedImmutableLanguageEnvironment;
import api4kbj.KRRLanguage;

public class FocusedGraphImmutableLanguageEnvironment extends GraphImmutableLanguageEnvironment
		implements FocusedImmutableLanguageEnvironment {

	public FocusedGraphImmutableLanguageEnvironment(AbstractKRRLanguage lang) {
	  super(lang);
    
	}

	@Override
	public Some<KRRLanguage> focusMember() {
		return (Some<KRRLanguage>) super.focusMember();
	}

}
