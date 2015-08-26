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
public class CLConjunction extends CLBooleanSentence {

	private final CLSentenceSequence conjuncts;

	/**
	 * 
	 */
	public CLConjunction(
			final CLCommentSequence comments,
			final CLSentenceSequence conjuncts
			) {
		super(comments);
		this.conjuncts = conjuncts;

	}

	/**
	 * @return the left condition
	 */
	public CLSentenceSequence conjuncts() {
		return conjuncts;
	}


	@Override
	public CLConjunction insertComments(final CLCommentSequence incomments) {
		return new CLConjunction( comments().concat(incomments), 
				conjuncts);
	}
	
	@Override
	public CLConjunction copy() {
		return new CLConjunction(comments(), conjuncts);
	}
	
}
