/**
 * 
 */
package cl2;

import cl2a.CLCommentSet;
import cl2a.CLSentence;
import cl2a.CLSequenceMarkerSequence;
import cl2a.CLStatement;

/**
 * @author ralph
 *
 */
public class CLSchema extends CLStatement {

	private CLSequenceMarkerSequence bindings;
	private CLSentence body;

	public CLSchema(
			final CLCommentSet comments,
			final CLSequenceMarkerSequence bindings,
			final CLSentence body) {
		super(comments);
		this.bindings = bindings;
		this.body = body;
	}

	/**
	 * @return the declarations
	 */
	public CLSequenceMarkerSequence declarations() {
		return bindings;
	}

	/**
	 * @return the body
	 */
	public CLSentence body() {
		return body;
	}
	
	@Override
	public CLSchema insertComments(final CLCommentSet incomments) {
		return new CLSchema(comments().concat(incomments), 
				bindings, body);
	}

	@Override
	public CLSchema copy() {
		return new CLSchema(comments(), bindings, body);
	}

}
