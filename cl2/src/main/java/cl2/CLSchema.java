/**
 * 
 */
package cl2;

import cl2a.CLCommentSet;
import cl2a.CLSentence;
import cl2a.CLSequenceMarkerSet;
import cl2a.CLStatement;

/**
 * @author ralph
 *
 */
public class CLSchema extends CLStatement {

	private CLSequenceMarkerSet bindings;
	private CLSentence body;

	public CLSchema(
			final CLCommentSet comments,
			final CLSequenceMarkerSet bindings,
			final CLSentence body) {
		super(comments);
		this.bindings = bindings;
		this.body = body;
	}

	/**
	 * @return the declarations
	 */
	public CLSequenceMarkerSet declarations() {
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
				declarations(), body());
	}

	@Override
	public CLSchema copy() {
		return new CLSchema(comments().copy(), declarations().copy(), body().copy());
	}

	/**
     * Returns the XCL2 sour syntax for the schema statement, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Schema>" + 
	            comments().toString() +
	            declarations().toString() +
	            body().toString() + "</cl:Schema>";
	}

}
