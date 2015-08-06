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
public class CLNegation extends CLBooleanSentence {

	private final CLSentence sent;

	/**
	 * 
	 */
	public CLNegation(
			final CLCommentSequence comments,
			final CLSentence sent) {
		super(comments);
		this.sent = sent;

	}

	/**
	 * @return the sentence
	 */
	public CLSentence sent() {
		return sent;
	}


	@Override
	public CLNegation insertComments(final CLCommentSequence incomments) {
		return new CLNegation(comments().concat(incomments), 
				sent);
	}
	
	
}
