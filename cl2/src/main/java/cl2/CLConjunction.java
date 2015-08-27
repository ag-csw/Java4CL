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
public class CLConjunction extends CLBooleanSentence {

	private final CLSentenceSequence conjuncts;

	/**
	 * 
	 */
	public CLConjunction(
			final CLCommentSet comments,
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
	public CLConjunction insertComments(final CLCommentSet incomments) {
		return new CLConjunction( comments().concat(incomments), 
				conjuncts);
	}
	
	@Override
	public CLConjunction copy() {
		return new CLConjunction(comments(), conjuncts);
	}
	
}
