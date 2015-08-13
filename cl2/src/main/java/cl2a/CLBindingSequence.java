package cl2a;


import java.util.Collection;

public abstract class CLBindingSequence extends CLExpressionLike {

	public CLBindingSequence() {
		super();
	}

	public abstract Collection<? extends CLInterpretableName> args();

	public abstract int length();

	public abstract CLBindingSequence concat(CLBindingSequence inargs);

}