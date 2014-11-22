package cl2;

import api4kb.Dialect;
import api4kb.DialectIncompatibleException;
import api4kb.Manifestation;

public class CLPrefixExpression implements CLPrefix, CLExpression {

	public CLPrefixExpression() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> Manifestation<T> manifest(Dialect<T> dialect)
			throws DialectIncompatibleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String iri() {
		// TODO Auto-generated method stub
		return null;
	}

}
