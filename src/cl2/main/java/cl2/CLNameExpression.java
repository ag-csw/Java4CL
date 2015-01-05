package cl2;

import functional.Option;

public abstract class CLNameExpression extends CLExpression implements CLName {

	// TODO make private and create static factory method
	public <T> CLNameExpression(CLManifestation manifestation) {
		super(manifestation);
	}

	@Override
	public Option<CLComment> comment() {
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
