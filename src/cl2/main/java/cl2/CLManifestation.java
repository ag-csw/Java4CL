package cl2;

import api4kb.AbstractKRRDialectType;
import api4kb.AbstractKnowledgeManifestation;
import api4kb.CodecSystem;
import api4kb.DialectTypeIncompatibleException;
import api4kb.EncodingSystemIncompatibleException;

public abstract class CLManifestation<T> extends
		AbstractKnowledgeManifestation<T> implements CLKnowledgeResource {

	public CLManifestation(T value, AbstractKRRDialectType<T> dialect) {
		super(value, dialect);
		// TODO Auto-generated constructor stub
	}

	public CLManifestation(CLDialectType<T> dialect) {
		super(dialect);
		// TODO Auto-generated constructor stub
	}

	public CLManifestation(CLExpression expression, CLDialectType<T> dialect) throws DialectTypeIncompatibleException {
		super(expression, dialect);
		// TODO Auto-generated constructor stub
	}

	public <S> CLManifestation(CLEncoding<T, S> encoding) {
		super(encoding);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <S> CLEncoding<T, S> encode(CodecSystem<T, S> system)
			throws EncodingSystemIncompatibleException {
		return (CLEncoding<T, S>) super.encode(system);
	}
	
	@Override
	protected <S> CLEncoding<T, S> evalEncoding(
			CodecSystem<T, S> system)
			throws EncodingSystemIncompatibleException {
		CLEncoding<T, S> encoding = CLEncoding.lazyNewInstance(this, system);
		return encoding;
	}


}
