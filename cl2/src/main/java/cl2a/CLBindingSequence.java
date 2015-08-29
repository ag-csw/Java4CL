package cl2a;


import java.util.Collection;

public abstract class CLBindingSequence extends CLExpressionLike {

	public CLBindingSequence() {
		super();
	}

	public abstract Collection<? extends CLInterpretableName> args();

	public abstract int length();

	public abstract CLBindingSequence concat(CLBindingSequence inargs);

	@Override
	public abstract CLBindingSequence copy();

	/**
     * Returns the XCL2 sour syntax for the binding sequence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		String result = "";
		for (CLInterpretableName s : args() ) result = result + s.toString();
		return result;
	}
	
}