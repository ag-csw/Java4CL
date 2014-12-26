package cl2;

import krhashmap.AbstractKnowledgeExpression;
import krhashmap.KnowledgeAssetLI;
import api4kbj.AbstractKRRLanguage;
import api4kbj.CodecSystem;
import api4kbj.ImmutableEnvironment;
import api4kbj.KRRDialect;
import api4kbj.KRRDialectType;
import api4kbj.KRRLanguage;

public abstract class CLExpression extends AbstractKnowledgeExpression
		implements CLKnowledgeResource {

	public <T> CLExpression(AbstractKRRLanguage lang) {
		super(CL.lang);
	}

	public CLExpression(KnowledgeAssetLI asset, AbstractKRRLanguage lang) {
		super(asset, CL.lang);
	}

	public <T> CLExpression(CLManifestationG<T> manifestation) {
		super(manifestation);
	}

	@Override
	public ImmutableEnvironment getDefaultEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KRRLanguage getDefaultLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KRRDialect getDefaultDialect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KRRDialectType<?> getDefaultDialectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodecSystem<?, ?> getDefaultCodecSystem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDefaultReceiver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDefaultSender() {
		// TODO Auto-generated method stub
		return null;
	}

}
