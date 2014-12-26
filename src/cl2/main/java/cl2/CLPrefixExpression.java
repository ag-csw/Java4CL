package cl2;

import api4kbj.AbstractKRRDialectType;
import api4kbj.AbstractKRRLanguage;
import api4kbj.GraphImmutableEnvironment;
import api4kbj.ImmutableEnvironment;
import api4kbj.KnowledgeAssetLI;

public abstract class CLPrefixExpression extends CLExpression implements
		CLPrefix {

	public CLPrefixExpression(KnowledgeAssetLI asset, AbstractKRRLanguage lang) {
		super(asset, lang);
		// TODO Auto-generated constructor stub
	}

	public <T> CLPrefixExpression(CLManifestationG<T> manifestation) {
		super(manifestation);
		// TODO Auto-generated constructor stub
	}

	public <T> CLPrefixExpression(AbstractKRRLanguage lang) {
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
