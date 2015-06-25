package cl2;

import api4kba.AbstractCodecSystem;
import api4kba.AbstractKRRDialectType;
import cl2a.CLDialect;

public class CLDialectType<T> extends AbstractKRRDialectType<T> {

	public CLDialectType(String name, CLDialect dialect, Class<T> type,
			AbstractCodecSystem<T, ?> system) {
		super(name, dialect, type);
	}

}
