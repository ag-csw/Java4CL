package cl2;

import api4kb.AbstractKRRDialectType;

public abstract class CLDialectType<T> extends AbstractKRRDialectType<T> {

	public CLDialectType(String name, CLDialect dialect, Class<T> type) {
		super(name, dialect, type);
	}


}
