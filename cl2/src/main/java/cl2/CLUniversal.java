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

	/**
     * Returns the XCL2 sour syntax for the existential quantification sentence, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Forall>" + 
	            comments().toString() +
	            bindings().toString() +
	            body().toString() + "</cl:Forall>";
	}
	
}
