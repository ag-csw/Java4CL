package cl2;

import api4kb.AbstractKnowledgeExpression;
import api4kb.AbstractKRRLanguage;
import api4kb.CodecSystem;
import api4kb.ImmutableEnvironment;
import api4kb.KRRDialect;
import api4kb.KRRDialectType;
import api4kb.KRRLanguage;
import api4kb.KnowledgeAssetLI;

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
