/**
 * 
 */
package cl2;

import cl2a.CLBooleanSentence;
import cl2a.CLCommentSet;
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
			final CLCommentSet comments,
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
	public CLBiconditional insertComments(final CLCommentSet incomments) {
		return new CLBiconditional( comments().concat(incomments), 
				left, right);
	}

	@Override
	public CLBiconditional copy() {
		return new CLBiconditional(comments(), left, right);
	}
	
	
}
