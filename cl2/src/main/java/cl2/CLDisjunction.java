/**
 * 
 */
package cl2;

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

	@Override
	public CLDisjunction copy() {
		return new CLDisjunction(comments(), disjuncts);
	}
	
	
}
