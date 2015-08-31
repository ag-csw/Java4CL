package cl2a;

import java.util.Set;


public abstract class CLExpressionSet extends CLExpressionLike {

	public CLExpressionSet() {
		super();
	}

	public abstract Set<? extends CLExpression> args();
	
	public abstract int length();

	public abstract CLExpressionSet concat(CLExpressionSet inargs);

	@Override
	public abstract CLExpressionSet copy();

	/**
     * Returns the XCL2 sour syntax for the expression sequence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		String result = "";
		for (CLExpression s : args() ) result = result + s.toString();
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + args().hashCode();
		return result;
	}

    public boolean canEqual(Object other) {
        return (other instanceof CLExpressionSet);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLExpressionSet))
			return false;
		CLExpressionSet other = (CLExpressionSet) obj;
		if (!other.canEqual(this))
			return false;
		if (args() == null) {
			if (other.args() != null)
				return false;
		} else if (!args().equals(other.args()))
			return false;
		return true;
	}

}