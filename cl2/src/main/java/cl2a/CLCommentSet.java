package cl2a;

import java.util.Set;

public abstract class CLCommentSet extends CLExpressionLike {
	
	public abstract Set<? extends CLComment> args();
	
	public abstract int length();

	public abstract CLCommentSet concat(CLCommentSet incomments);
	
	@Override
	public abstract CLCommentSet copy();

	/**
     * Returns the XCL2 sour syntax for the comment set, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		String result = "";
		for (CLComment s : args() ) result = result + s.toString();
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((args() == null) ? 0 : args().hashCode());
		return result;
	}

    public boolean canEqual(Object other) {
        return (other instanceof CLCommentSet);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLCommentSet))
			return false;
		CLCommentSet other = (CLCommentSet) obj;
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