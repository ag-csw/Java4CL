package cl2a;


import java.util.Set;


public abstract class CLBindingSet extends CLExpressionLike {
	
	private Set<? extends CLInterpretableName> bindings;

		public CLBindingSet(CLInterpretableName... bindings){
			for (CLInterpretableName name:bindings){
			if (name == null)
				throw new NullPointerException("Arguments to CLBindingSequence constructor should not be null");
			}
		}

	public abstract Set<? extends CLInterpretableName> bindings();

	public abstract int length();

	public abstract CLBindingSet concat(CLBindingSet inbindings);

	@Override
	public abstract CLBindingSet copy();

	/**
     * Returns the XCL2 sour syntax for the binding sequence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		String result = "";
		for (CLInterpretableName s : bindings() ) result += s.toString();
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bindings() == null) ? 0 : bindings().hashCode());
		return result;
	}

    public boolean canEqual(Object other) {
        return (other instanceof CLBindingSet);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLBindingSet))
			return false;
		CLBindingSet other = (CLBindingSet) obj;
		if (!other.canEqual(this))
			return false;
		if (bindings() == null) {
			if (other.bindings() != null)
				return false;
		} else if (!bindings().equals(other.bindings()))
			return false;
		return true;
	}
	
}