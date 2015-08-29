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
public class CLExistential extends CLQuantifiedSentence {

	/**
	 * 
	 */
	public CLExistential(
			final CLCommentSet comments,
			final CLBindingSequence declarations,
			final CLSentence body) {
		super(comments, declarations, body);

	}



	@Override
	public CLExistential insertComments(final CLCommentSet incomments) {
		return new CLExistential( comments().concat(incomments), 
				bindings(), body());
	}

	@Override
	public CLExistential copy() {
		return new CLExistential(comments().copy(), bindings().copy(), body().copy());
	}

	/**
     * Returns the XCL2 sour syntax for the existential quantification sentence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Exists>" + 
	            comments().toString() +
	            bindings().toString() +
	            body().toString() + "</cl:Exists>";
	}


}
