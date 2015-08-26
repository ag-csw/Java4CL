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
public class CLUniversal extends CLQuantifiedSentence {

	/**
	 * 
	 */
	public CLUniversal(
			final CLCommentSequence comments,
			final CLBindingSequence bindings,
			final CLSentence body) {
		super(comments, bindings, body);

	}



	@Override
	public CLUniversal insertComments(final CLCommentSequence incomments) {
		return new CLUniversal( comments().concat(incomments), 
				bindings(), body());
	}



	@Override
	public CLUniversal copy() {
		return new CLUniversal(comments(), bindings(), body());
	}

	
}
