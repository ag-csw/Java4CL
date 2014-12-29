package cl2;

import krhashmap.AbstractBasicKnowledgeExpression;
import krhashmap.BasicKnowledgeAssetLI;
import api4kbj.AbstractKRRLanguage;

public abstract class CLExpression extends AbstractBasicKnowledgeExpression
		implements CLKnowledgeResource {

	public CLExpression(AbstractKRRLanguage lang) {
		super(CL.lang);
	}

	public CLExpression(BasicKnowledgeAssetLI asset, AbstractKRRLanguage lang) {
		super(asset, CL.lang);
	}

	public <T> CLExpression(CLManifestationG<T> manifestation) {
		super(manifestation);
	}

	@Override
	public AbstractKRRLanguage language() {
		return (AbstractKRRLanguage) super.language();
	}

}
