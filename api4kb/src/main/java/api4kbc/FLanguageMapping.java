package api4kbc;

import api4kbj.KRRLanguage;
import api4kbj.KnowledgeExpression;
import api4kbj.LanguageMapping;
import fj.F;

public class FLanguageMapping<S extends KnowledgeExpression, T extends KnowledgeExpression>
		implements LanguageMapping<S, T> {

	public FLanguageMapping(F<S, T> function, KRRLanguage startLang,
			KRRLanguage endLang) {
		Function = function;
		this.startLang = startLang;
		this.endLang = endLang;
	}

	private F<S, T> Function;
	private KRRLanguage startLang;
	private KRRLanguage endLang;

	@Override
	public Class<? extends S> startClass() {
		return (Class<? extends S>) startLanguage().asClass();
	}

	@Override
	public Class<? extends T> endClass() {
		return (Class<? extends T>) endLanguage().asClass();
	}

	@Override
	public F<S, T> function() {
		return Function;
	}

	@Override
	public KRRLanguage startLanguage() {
		return startLang;
	}

	@Override
	public KRRLanguage endLanguage() {
		return endLang;
	}

}
