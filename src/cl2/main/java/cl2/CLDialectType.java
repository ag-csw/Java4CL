package cl2;

import api4kb.AbstractKRRDialectType;

public class CLDialectType<T> extends AbstractKRRDialectType<T> {

	private CLDialectType(String name) {
		super(name, CL.lang);
	}

	// static factory method
	public static <T> CLDialectType<T> newInstance(String name) {
		return (CLDialectType<T>) new CLDialectType<T>(name);
	}


}
