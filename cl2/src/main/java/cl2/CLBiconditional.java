/**
 * 
 */
package cl2;

import cl2a.CLBooleanSentence;
import cl2a.CLCommentSequence;
import cl2a.CLPrefixSequence;
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
			final CLPrefixSequence prefixes, 
			final CLCommentSequence comments,
			final CLSentence left,
			final CLSentence right
			) {
		super(prefixes, comments);
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
		return new CLBiconditional( prefixes(), comments().concat(incomments), 
				left, right);
	}


	@Override
	public CLBiconditional insertPrefixes(final CLPrefixSequence inprefixes) {
		return new CLBiconditional( prefixes().concat(inprefixes),
				comments(), left, right);
	}

	
	
}
