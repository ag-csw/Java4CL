package cl2;

import functional.Option;
import graphenvironment.GraphImmutableEnvironment;
import krhashmap.KnowledgeAssetLI;
import api4kbj.AbstractKRRDialectType;
import api4kbj.AbstractKRRLanguage;
import api4kbj.ImmutableEnvironment;

public abstract class CLNameExpression extends CLExpression implements CLName {

	public CLNameExpression(KnowledgeAssetLI asset, AbstractKRRLanguage lang) {
		super(asset, lang);
		// TODO Auto-generated constructor stub
	}

	public <T> CLNameExpression(CLManifestationG<T> manifestation) {
		super(manifestation);
		// TODO Auto-generated constructor stub
	}

	public <T> CLNameExpression(AbstractKRRLanguage lang) {
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
	public <T> CLManifestationG<T> manifest(AbstractKRRDialectType<T> dialect) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearManifest(AbstractKRRDialectType<?> dialect) {
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
	public AbstractKRRLanguage language() {
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
