/**
 * 
 */
package cl2;

import cl2a.CLBooleanSentence;
import cl2a.CLCommentSequence;
import cl2a.CLSentence;

/**
 * @author ralph
 *
 */
public class CLImplication extends CLBooleanSentence {

	private final CLSentence antecedent;
	private final CLSentence consequent;

	/**
	 * 
	 */
	public CLImplication(
			final CLCommentSequence comments,
			final CLSentence antecedent,
			final CLSentence consequent
			) {
		super(comments);
		this.antecedent = antecedent;
		this.consequent = consequent;

	}

	/**
	 * @return the antecedent
	 */
	public CLSentence antecedent() {
		return antecedent;
	}

	/**
	 * @return the consequent
	 */
	public CLSentence consequent() {
		return consequent;
	}


	@Override
	public CLImplication insertComments(final CLCommentSequence incomments) {
		return new CLImplication( comments().concat(incomments), 
				antecedent, consequent);
	}

	@Override
	public CLImplication copy() {
		return new CLImplication(comments(), antecedent, antecedent);
	}
	
}
