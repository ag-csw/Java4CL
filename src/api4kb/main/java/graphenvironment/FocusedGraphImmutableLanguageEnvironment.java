package graphenvironment;

import functional.Some;
import api4kbj.FocusedImmutableEnvironment;
import api4kbj.KRRLanguage;

public class FocusedGraphImmutableEnvironment extends GraphImmutableEnvironment
		implements FocusedImmutableEnvironment {

	public FocusedGraphImmutableEnvironment(KRRLanguage lang) {
		super(lang);
	}

	@Override
	public Some<KRRLanguage> focusLanguage() {
		// TODO Auto-generated method stub
		return (Some<KRRLanguage>) super.focusLanguage();
	}

}
