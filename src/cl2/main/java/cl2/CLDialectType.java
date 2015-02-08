package cl2;

import api4kbja.AbstractCodecSystem;
import api4kbja.AbstractKRRDialectType;

public abstract class CLDialectType<T> extends AbstractKRRDialectType<T> {

	public CLDialectType(String name, CLDialect dialect, Class<T> type,
			AbstractCodecSystem<T, ?> system) {
		super(name, dialect, type);
	}

}
