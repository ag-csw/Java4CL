/**
 * 
 */
package cl2;

import cl2a.CLCommentSet;
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
			final CLCommentSet comments,
			final CLBindingSequence bindings,
			final CLSentence body) {
		super(comments, bindings, body);

	}



	@Override
	public CLUniversal insertComments(final CLCommentSet incomments) {
		return new CLUniversal( comments().concat(incomments), 
				bindings(), body());
	}



	@Override
	public CLUniversal copy() {
		return new CLUniversal(comments(), bindings(), body());
	}

	
}
