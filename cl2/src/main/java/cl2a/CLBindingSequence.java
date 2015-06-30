package cl2a;

import cl2.CLInterpretableName;



public abstract class CLBindingSequence extends CLExpressionLike {

	public CLBindingSequence() {
		super();
	}

	public abstract Iterable<CLInterpretableName> args();

	public abstract int length();

	public abstract CLBindingSequence concat(CLBindingSequence inargs);

}