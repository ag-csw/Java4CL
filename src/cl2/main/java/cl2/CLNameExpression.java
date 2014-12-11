package cl2;

import api4kb.ImmutableEnvironment;
import api4kb.KRRDialect;
import api4kb.DialectIncompatibleException;
import api4kb.KRRLanguage;
import api4kb.AbstractKnowledgeAsset;
import api4kb.Option;
import api4kb.UnsupportedTranslationException;

public abstract class CLNameExpression extends CLExpression implements CLName {


	public CLNameExpression(CLAsset asset, KRRLanguage lang)
			throws UnsupportedTranslationException {
		super(asset, lang);
		// TODO Auto-generated constructor stub
	}

	public <T> CLNameExpression(CLManifestation<T> manifestation) {
		super(manifestation);
		// TODO Auto-generated constructor stub
	}

	public <T> CLNameExpression(KRRLanguage lang) {
		super(lang);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Option<CLComment> comment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CLPrefixExpression[] prefixes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String symbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> CLManifestation<T> manifest(KRRDialect<T> dialect)
			throws DialectIncompatibleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearManifest(KRRDialect<?> dialect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractKnowledgeAsset conceptualize(ImmutableEnvironment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearConceptualize(ImmutableEnvironment environment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public KRRLanguage getLanguage() {
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
