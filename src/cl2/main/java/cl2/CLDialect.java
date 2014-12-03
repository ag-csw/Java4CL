package cl2;

import api4kb.AbstractKRRDialect;

public class CLDialect<T> extends AbstractKRRDialect<T> {

	private CLDialect(String name) {
		super(name, CL.lang);
	}

	// static factory method
	public static <T> CLDialect<T> newInstance(String name) {
		return (CLDialect<T>) new CLDialect<T>(name);
	}


}
