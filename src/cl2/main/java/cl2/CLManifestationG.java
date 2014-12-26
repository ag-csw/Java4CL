package cl2;

import api4kb.AbstractKRRDialectType;
import api4kb.AbstractKnowledgeManifestationG;
import api4kb.CodecSystem;
import api4kb.ImmutableEnvironment;
import api4kb.KRRDialect;
import api4kb.KRRDialectType;
import api4kb.KRRLanguage;

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
