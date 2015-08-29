/**
 * 
 */
package cl2;

import java.util.function.Function;

import cl2a.CLBooleanSentence;
import cl2a.CLCommentSet;
import cl2a.CLSentenceSequence;

/**
 * @author ralph
 *
 */
public class CLConjunction extends CLBooleanSentence {

	// private CLCommentSet comments;
	private final CLSentenceSequence conjuncts;

	/**
	 * 
	 */
	public CLConjunction(
			final CLCommentSet comments,
			final CLSentenceSequence conjuncts
			) {
		super(comments);
		if(conjuncts==null)
			throw new NullPointerException("Conjuncts of a CLConjunction should not be null.");
		this.conjuncts = conjuncts;

	}

	/**
	 * @return the conjuncts
	 */
	public CLSentenceSequence conjuncts() {
		return conjuncts;
	}


	@Override
	public CLConjunction insertComments(final CLCommentSet incomments) {
		return new CLConjunction( comments().concat(incomments), 
				conjuncts);
	}

	@Override
	public CLConjunction copy() {
		return new CLConjunction(comments().copy(), conjuncts.copy());
	}
	
    /**
     * Returns a modified copy derived by applying functions to each of the
     * fields: comments, conjuncts.
     * Law: when the passed operators are the identity operators, then the
     * copy is equal to the original.
     * Law: copy is composable.
     * 
     * @param commentsOperator function that modifies a CL comment sequence
     * @param conjunctsOperator function that modifies a CL sentence sequence
     * @return modified copy of this CL conjunction
     */
	public CLConjunction copy(
			final Function<CLCommentSet, ? extends CLCommentSet> commentsOperator,
			final Function<CLSentenceSequence, ? extends CLSentenceSequence> conjunctsOperator
			) {
				return 
						new CLConjunction(
								commentsOperator.apply(comments()),
								conjunctsOperator.apply(conjuncts)
								);
		
	}

	/**
     * Returns the XCL2 sour syntax for the conjunction sentence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:And>" + 
	            comments().toString() +
	            conjuncts.toString() + "</cl:And>";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments() == null) ? 0 : comments().hashCode());
		result = prime * result + ((conjuncts == null) ? 0 : conjuncts.hashCode());
		return result;
	}

	public boolean canEqual(Object other) {
        return (other instanceof CLConjunction);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLConjunction))
			return false;
		CLConjunction other = (CLConjunction) obj;
		if (!other.canEqual(this))
			return false;
		if (comments() == null) {
			if (other.comments() != null)
				return false;
		} else if (!comments().equals(other.comments()))
			return false;
		if (conjuncts == null) {
			if (other.conjuncts != null)
				return false;
		} else if (!conjuncts.equals(other.conjuncts))
			return false;
		return true;
	}

}
