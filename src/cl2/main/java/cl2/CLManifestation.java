package cl2;

import api4kb.AbstractKRRDialectType;
import api4kb.AbstractKnowledgeManifestation;
import api4kb.DialectTypeIncompatibleException;
import api4kb.KRRDialectType;

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

}
