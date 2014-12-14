package cl2;

import api4kb.AbstractCodecSystem;
import api4kb.AbstractKRRDialectType;

public abstract class CLDialectType<T> extends AbstractKRRDialectType<T> {

	public CLDialectType(String name, CLDialect dialect, Class<T> type, AbstractCodecSystem<T, ?> system) {
		super(name, dialect, type, system);
	}


}
