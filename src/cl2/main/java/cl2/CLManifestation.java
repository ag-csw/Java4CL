package cl2;

import api4kb.AbstractKnowledgeManifestation;
import api4kb.KRRDialect;

public abstract class CLManifestation<T> extends
		AbstractKnowledgeManifestation<T> implements CLKnowledgeResource {

	public CLManifestation(T value, KRRDialect<T> dialect) {
		super(value, dialect);
		// TODO Auto-generated constructor stub
	}

}
