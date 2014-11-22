package cl2;

import api4kb.Dialect;
import api4kb.DialectIncompatibleException;
import api4kb.Manifestation;

public class CLCommentExpression implements CLComment, CLExpression {

	public CLCommentExpression() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> CLCommentManifestation<T> manifest(Dialect<T> dialect)
			throws DialectIncompatibleException {
		// TODO Auto-generated method stub
		return null;
	}

	public String symbol() {
		// TODO Auto-generated method stub
		return null;
	}

}
