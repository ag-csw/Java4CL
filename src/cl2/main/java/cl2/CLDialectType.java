package cl2;

import api4kbj.AbstractCodecSystem;
import api4kbj.AbstractKRRDialectType;

public abstract class CLDialectType<T> extends AbstractKRRDialectType<T> {

	public CLDialectType(String name, CLDialect dialect, Class<T> type,
			AbstractCodecSystem<T, ?> system) {
		super(name, dialect, type, system);
	}

}
