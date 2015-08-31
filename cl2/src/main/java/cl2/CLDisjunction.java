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
public class CLDisjunction extends CLBooleanSentence {

	private CLCommentSet comments;
	private final CLSentenceSequence disjuncts;

	/**
	 * 
	 */
	public CLDisjunction(
			final CLCommentSet comments,
			final CLSentenceSequence disjuncts
			) {
		super(comments);
		if(disjuncts==null)
			throw new NullPointerException("Conjuncts of a CLDisjunction should not be null.");
		this.disjuncts = disjuncts;

	}

	/**
	 * @return the disjuncts
	 */
	public CLSentenceSequence disjuncts() {
		return disjuncts;
	}


	@Override
	public CLDisjunction insertComments(final CLCommentSet incomments) {
		return new CLDisjunction( comments().concat(incomments), 
				disjuncts());
	}

	@Override
	public CLDisjunction copy() {
		return new CLDisjunction(comments().copy(), disjuncts().copy());
	}
	
    /**
     * Returns a modified copy derived by applying functions to each of the
     * fields: comments, disjuncts.
     * Law: when the passed operators are the identity operators, then the
     * copy is equal to the original.
     * Law: copy is composable.
     * 
     * @param commentsOperator function that modifies a CL comment sequence
     * @param disjuncts function that modifies a CL sentence sequence
     * @return modified copy of this CL disjunction
     */
	public CLDisjunction copy(
			final Function<CLCommentSet, ? extends CLCommentSet> commentsOperator,
			final Function<CLSentenceSequence, ? extends CLSentenceSequence> disjunctsOperator
			) {
				return 
						new CLDisjunction(
								commentsOperator.apply(comments()),
								disjunctsOperator.apply(disjuncts)
								);
		
	}

	/**
     * Returns the XCL2 sour syntax for the disjunction sentence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Or>" + 
	            comments().toString() +
	            disjuncts().toString() + "</cl:Or>";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments() == null) ? 0 : comments().hashCode());
		result = prime * result + ((disjuncts() == null) ? 0 : disjuncts().hashCode());
		return result;
	}

	public boolean canEqual(Object other) {
        return (other instanceof CLDisjunction);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLDisjunction))
			return false;
		CLDisjunction other = (CLDisjunction) obj;
		if (!other.canEqual(this))
			return false;
		if (comments() == null) {
			if (other.comments() != null)
				return false;
		} else if (!comments().equals(other.comments()))
			return false;
		if (disjuncts() == null) {
			if (other.disjuncts() != null)
				return false;
		} else if (!disjuncts().equals(other.disjuncts()))
			return false;
		return true;
	}	
	
}
