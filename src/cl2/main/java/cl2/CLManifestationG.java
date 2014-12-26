package cl2;

import krhashmap.AbstractKnowledgeManifestationG;
import api4kbj.AbstractKRRDialectType;
import api4kbj.CodecSystem;
import api4kbj.ImmutableEnvironment;
import api4kbj.KRRDialect;
import api4kbj.KRRDialectType;
import api4kbj.KRRLanguage;

public abstract class CLManifestationG<T> extends
		AbstractKnowledgeManifestationG<T> implements CLKnowledgeResource {

	public CLManifestationG(T value, AbstractKRRDialectType<T> dialect) {
		super(value, dialect);
		// TODO Auto-generated constructor stub
	}

	public CLManifestationG(CLDialectType<T> dialect) {
		super(dialect);
		// TODO Auto-generated constructor stub
	}

	public CLManifestationG(CLExpression expression, CLDialectType<T> dialect) {
		super(expression, dialect);
	}

	public <S> CLManifestationG(CLEncoding<T, S> encoding) {
		super(encoding);
		// TODO Auto-generated constructor stub
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
