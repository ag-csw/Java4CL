package cl2a;


import java.util.Set;

import cl2array.CLBindingSetArray;

public abstract class CLBindingSet extends CLExpressionLike {

		public CLBindingSet(CLInterpretableName... names){
			for (CLInterpretableName name:names){
			if (name == null)
				throw new NullPointerException("Arguments to CLBindingSequence constructor should not be null");
			}
		}

	public abstract Set<? extends CLInterpretableName> args();

	public abstract int length();

	public abstract CLBindingSet concat(CLBindingSet inargs);

	@Override
	public abstract CLBindingSet copy();

	/**
     * Returns the XCL2 sour syntax for the binding sequence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		String result = "";
		for (CLInterpretableName s : args() ) result += s.toString();
		return result;
	}
	
}