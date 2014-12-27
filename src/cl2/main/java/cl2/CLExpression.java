package cl2;

import krhashmap.AbstractBasicKnowledgeExpression;
import krhashmap.KnowledgeAssetLI;
import api4kbj.AbstractKRRLanguage;
import api4kbj.CodecSystem;
import api4kbj.ImmutableEnvironment;
import api4kbj.KRRDialect;
import api4kbj.KRRDialectType;
import api4kbj.KRRLanguage;

public abstract class CLExpression extends AbstractBasicKnowledgeExpression
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
	public ImmutableEnvironment defaultEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KRRLanguage defaultLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KRRDialect defaultDialect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KRRDialectType<?> defaultDialectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodecSystem<?, ?> defaultCodecSystem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object defaultReceiver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object defaultSender() {
		// TODO Auto-generated method stub
		return null;
	}

}
