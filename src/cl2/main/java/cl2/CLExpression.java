package cl2;

import api4kb.AbstractKnowledgeExpression;
import api4kb.AbstractKRRLanguage;
import api4kb.KnowledgeAssetLI;
import api4kb.LanguageIncompatibleException;
import api4kb.UnsupportedTranslationException;

public abstract class CLExpression extends AbstractKnowledgeExpression implements CLKnowledgeResource {

	public <T> CLExpression(AbstractKRRLanguage lang) {
		super(CL.lang);
	}

	public CLExpression(KnowledgeAssetLI asset, AbstractKRRLanguage lang)
			throws UnsupportedTranslationException, LanguageIncompatibleException {
		super(asset, CL.lang);
	}

	public <T> CLExpression(CLManifestation<T> manifestation) {
		super(manifestation);
	}



}
