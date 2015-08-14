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
public class CLBiconditional extends CLBooleanSentence {

	private final CLSentence left;
	private final CLSentence right;

	/**
	 * 
	 */
	public CLBiconditional(
			final CLCommentSequence comments,
			final CLSentence left,
			final CLSentence right
			) {
		super(comments);
		this.left = left;
		this.right = right;

	}

	/**
	 * @return the left condition
	 */
	public CLSentence left() {
		return left;
	}

	/**
	 * @return the right condition
	 */
	public CLSentence right() {
		return right;
	}


	@Override
	public CLBiconditional insertComments(final CLCommentSequence incomments) {
		return new CLBiconditional( comments().concat(incomments), 
				left, right);
	}


	
	
}