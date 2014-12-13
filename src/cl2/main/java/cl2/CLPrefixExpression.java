package cl2;

import api4kb.AbstractKRRLanguage;
import api4kb.GraphImmutableEnvironment;
import api4kb.ImmutableEnvironment;
import api4kb.KRRDialectType;
import api4kb.DialectTypeIncompatibleException;
import api4kb.KRRLanguage;
import api4kb.KnowledgeAssetLI;
import api4kb.UnsupportedTranslationException;

public abstract class CLPrefixExpression extends CLExpression implements CLPrefix {


	public CLPrefixExpression(KnowledgeAssetLI asset, KRRLanguage lang)
			throws UnsupportedTranslationException {
		super(asset, lang);
		// TODO Auto-generated constructor stub
	}

	public <T> CLPrefixExpression(
			CLManifestation<T> manifestation) {
		super(manifestation);
		// TODO Auto-generated constructor stub
	}

	public <T> CLPrefixExpression(KRRLanguage lang) {
		super(lang);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String pre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String iri() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> CLManifestation<T> manifest(KRRDialectType<T> dialect)
			throws DialectTypeIncompatibleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearManifest(KRRDialectType<?> dialect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public KnowledgeAssetLI conceptualize(GraphImmutableEnvironment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearConceptualize(ImmutableEnvironment environment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractKRRLanguage getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearManifest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearAsset() {
		// TODO Auto-generated method stub
		
	}

}
