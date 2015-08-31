/**
 * 
 */
package cl2a;

/**
 * @author ralph
 *
 */
public abstract class CLQuantifiedSentence extends CLSentence {

	private CLBindingSet bindings;
	private CLSentence body;

	public CLQuantifiedSentence(
			final CLCommentSet comments,
			final CLBindingSet bindings,
			final CLSentence body) {
		super(comments);
		this.bindings = bindings;
		this.body = body;
	}

	/**
	 * @return the declarations
	 */
	public CLBindingSet bindings() {
		return bindings;
	}

	/**
	 * @return the body
	 */
	public CLSentence body() {
		return body;
	}

	@Override
	public abstract CLQuantifiedSentence copy();
	
}
