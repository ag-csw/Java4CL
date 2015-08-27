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
public class CLNegation extends CLBooleanSentence {

	private final CLSentence sent;

	/**
	 * 
	 */
	public CLNegation(
			final CLCommentSet comments,
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
	public CLNegation insertComments(final CLCommentSet incomments) {
		return new CLNegation(comments().concat(incomments), 
				sent);
	}

	@Override
	public CLNegation copy() {
		return new CLNegation(comments(), sent);
	}
	
}
