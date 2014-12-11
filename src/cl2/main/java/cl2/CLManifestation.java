package cl2;

import api4kb.AbstractKnowledgeManifestation;
import api4kb.DialectIncompatibleException;
import api4kb.KRRDialectType;

public abstract class CLManifestation<T> extends
		AbstractKnowledgeManifestation<T> implements CLKnowledgeResource {

	public CLManifestation(T value, KRRDialectType<T> dialect) {
		super(value, dialect);
		// TODO Auto-generated constructor stub
	}

	public CLManifestation(CLDialectType<T> dialect) {
		super(dialect);
		// TODO Auto-generated constructor stub
	}

	public CLManifestation(CLExpression expression, CLDialectType<T> dialect) throws DialectIncompatibleException {
		super(expression, dialect);
		// TODO Auto-generated constructor stub
	}

	public <S> CLManifestation(CLEncoding<T, S> encoding) {
		super(encoding);
		// TODO Auto-generated constructor stub
	}

}
