/**
 * 
 */
package cl2a;

/**
 * @author ralph
 *
 */
public abstract class CLQuantifiedSentence extends CLSentence {

	private CLBindingSequence bindings;
	private CLSentence body;

	public CLQuantifiedSentence(
			final CLCommentSet comments,
			final CLBindingSequence bindings,
			final CLSentence body) {
		super(comments);
		this.bindings = bindings;
		this.body = body;
	}

	/**
	 * @return the declarations
	 */
	public CLBindingSequence bindings() {
		return bindings;
	}

	/**
	 * @return the body
	 */
	public CLSentence body() {
		return body;
	}

}
