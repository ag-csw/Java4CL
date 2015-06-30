package cl2a;

import api4kba.AbstractKRRDialect;
import cl2.CL;

public abstract class CLDialect extends AbstractKRRDialect {

	public CLDialect(String name) {
		super(name, CL.LANG);
	}

}
