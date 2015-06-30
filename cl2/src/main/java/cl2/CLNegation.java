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
public class CLNegation extends CLBooleanSentence {

	private final CLSentence sent;

	/**
	 * 
	 */
	public CLNegation(
			final CLPrefixSequence prefixes, 
			final CLCommentSequence comments,
			final CLSentence sent) {
		super(prefixes, comments);
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
		return new CLNegation( prefixes(), comments().concat(incomments), 
				sent);
	}


	@Override
	public CLNegation insertPrefixes(final CLPrefixSequence inprefixes) {
		return new CLNegation( prefixes().concat(inprefixes),
				comments(), sent);
	}

	
	
}
