/**
 * 
 */
package cl2;

import cl2a.CLBooleanSentence;
import cl2a.CLCommentSequence;
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
			final CLCommentSequence comments,
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
	public CLDisjunction insertComments(final CLCommentSequence incomments) {
		return new CLDisjunction( comments().concat(incomments), 
				disjuncts);
	}

	@Override
	public CLDisjunction copy() {
		return new CLDisjunction(comments(), disjuncts);
	}
	
	
}
