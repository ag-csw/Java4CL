package cl2a;

import api4kbc.WrapperBasicKnowledgeManifestation;
import api4kbj.DialectTypeEnvironment;
import cl2.CLDialectType;

public abstract class CLManifestation extends
		WrapperBasicKnowledgeManifestation implements CLKnowledgeResource {

	public CLManifestation(CLDialect dialect,
			CLDialectType<?> wrappedDialectType, Object wrappedValue,
			DialectTypeEnvironment environment) {
		super(dialect, wrappedDialectType, wrappedValue, environment);
	}



}
