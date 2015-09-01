package cl2a;

import java.util.Set;

public abstract class CLSentenceSet extends CLExpressionSet {

	public CLSentenceSet(CLSentence... sentences){
		for (CLSentence sentence:sentences){
		if (sentence == null)
			throw new NullPointerException("Arguments to CLSentenceSet constructor should not be null");
		}
	}

	@Override
	public abstract Set<? extends CLSentence> args();

	@Override
	public abstract int length();

	public abstract CLSentenceSet concatSentences(CLSentenceSet inargs);

	@Override
	public abstract CLSentenceSet copy();

	/**
     * Returns the XCL2 sour syntax for the sentence sequence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		String result = "";
		for (CLSentence s : args() ) result += s.toString();
		return result;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + args().hashCode();
		return result;
	}

    @Override
	public boolean canEqual(Object other) {
        return (other instanceof CLSentenceSet);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLSentenceSet))
			return false;
		CLSentenceSet other = (CLSentenceSet) obj;
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