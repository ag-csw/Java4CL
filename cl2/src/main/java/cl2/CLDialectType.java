package cl2;

import api4kba.AbstractCodecSystem;
import api4kba.AbstractKRRDialectType;

public class CLDialectType<T> extends AbstractKRRDialectType<T> {

	public CLDialectType(String name, CLDialect dialect, Class<T> type,
			AbstractCodecSystem<T, ?> system) {
		super(name, dialect, type);
	}

}
