package cl2;

import api4kb.AbstractKnowledgeExpression;
import api4kb.KRRLanguage;
import api4kb.UnsupportedTranslationException;

public abstract class CLExpression extends AbstractKnowledgeExpression implements CLKnowledgeResource {

	public CLExpression(CLAsset asset, KRRLanguage lang)
			throws UnsupportedTranslationException {
		super(asset, CL.lang);
		// TODO Auto-generated constructor stub
	}

	public <T> CLExpression(CLManifestation<T> manifestation) {
		super(manifestation);
		// TODO Auto-generated constructor stub
	}

	public <T> CLExpression(KRRLanguage lang) {
		super(CL.lang);
		// TODO Auto-generated constructor stub
	}

}
