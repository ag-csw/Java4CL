package cl2a;

import cl2.CLDialectType;
import api4kbc.WrapperBasicKnowledgeManifestation;
import api4kbj.DialectTypeEnvironment;

public abstract class CLManifestation extends
		WrapperBasicKnowledgeManifestation implements CLKnowledgeResource {

	public CLManifestation(CLDialect dialect,
			CLDialectType<?> wrappedDialectType, Object wrappedValue,
			DialectTypeEnvironment environment) {
		super(dialect, wrappedDialectType, wrappedValue, environment);
	}



}
