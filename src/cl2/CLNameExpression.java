package cl2;

import api4kb.Dialect;
import api4kb.DialectIncompatibleException;
import api4kb.Manifestation;

public class CLNameExpression implements CLExpression, CLName {

	public CLNameExpression() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> Manifestation<T> manifest(Dialect<T> dialect)
			throws DialectIncompatibleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CLCommentExpression comment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CLPrefixExpression[] prefixes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String symbol() {
		// TODO Auto-generated method stub
		return null;
	}

}
