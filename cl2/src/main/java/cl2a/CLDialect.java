package cl2a;

import cl2.CL;
import api4kba.AbstractKRRDialect;

public abstract class CLDialect extends AbstractKRRDialect {

	public CLDialect(String name) {
		super(name, CL.LANG);
	}

}
