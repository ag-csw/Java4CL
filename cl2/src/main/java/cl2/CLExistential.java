/**
 * 
 */
package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLBindingSequence;
import cl2a.CLQuantifiedSentence;
import cl2a.CLSentence;

/**
 * @author ralph
 *
 */
public class CLExistential extends CLQuantifiedSentence {

	/**
	 * 
	 */
	public CLExistential(
			final CLCommentSequence comments,
			final CLBindingSequence declarations,
			final CLSentence body) {
		super(comments, declarations, body);

	}



	@Override
	public CLExistential insertComments(final CLCommentSequence incomments) {
		return new CLExistential( comments().concat(incomments), 
				bindings(), body());
	}
	
	
}
