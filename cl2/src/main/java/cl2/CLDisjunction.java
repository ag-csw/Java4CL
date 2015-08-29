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

	private final CLSentenceSequence disjuncts;

	/**
	 * 
	 */
	public CLDisjunction(
			final CLCommentSet comments,
			final CLSentenceSequence disjuncts
			) {
		super(comments);
		this.disjuncts = disjuncts;

	}

	/**
	 * @return the left condition
	 */
	public CLSentenceSequence disjuncts() {
		return disjuncts;
	}


	@Override
	public CLDisjunction insertComments(final CLCommentSet incomments) {
		return new CLDisjunction( comments().concat(incomments), 
				disjuncts);
	}

	@Override
	public CLDisjunction copy() {
		return new CLDisjunction(comments().copy(), disjuncts.copy());
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
			final Function<CLSentenceSequence, ? extends CLSentenceSequence> conjunctsOperator
			) {
				return 
						new CLDisjunction(
								commentsOperator.apply(comments()),
								conjunctsOperator.apply(disjuncts)
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
	            disjuncts.toString() + "</cl:Or>";
	}	
	
}
