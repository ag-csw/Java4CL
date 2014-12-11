package cl2;

import api4kb.AbstractKnowledgeManifestation;
import api4kb.DialectIncompatibleException;
import api4kb.KRRDialect;

public abstract class CLManifestation<T> extends
		AbstractKnowledgeManifestation<T> implements CLKnowledgeResource {

	public CLManifestation(T value, KRRDialect<T> dialect) {
		super(value, dialect);
		// TODO Auto-generated constructor stub
	}

	public CLManifestation(CLDialect<T> dialect) {
		super(dialect);
		// TODO Auto-generated constructor stub
	}

	public CLManifestation(CLExpression expression, CLDialect<T> dialect) throws DialectIncompatibleException {
		super(expression, dialect);
		// TODO Auto-generated constructor stub
	}

	public <S> CLManifestation(CLEncoding<T, S> encoding) {
		super(encoding);
		// TODO Auto-generated constructor stub
	}

}
