package cl2a;

import java.util.Collection;

import cl2array.CLSentenceSequenceArray;



public abstract class CLSentenceSequence extends CLExpressionLike {

	public CLSentenceSequence() {
		super();
	}

	public abstract Collection<? extends CLSentence> args();

	public abstract int length();

	public abstract CLSentenceSequence concat(CLSentenceSequence inargs);

	@Override
	public abstract CLSentenceSequence copy();

	/**
     * Returns the XCL2 sour syntax for the sentence sequence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		String result = "";
		for (CLSentence s : args() ) result = result + s.toString();
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
        return (other instanceof CLSentenceSequence);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLSentenceSequence))
			return false;
		CLSentenceSequence other = (CLSentenceSequence) obj;
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