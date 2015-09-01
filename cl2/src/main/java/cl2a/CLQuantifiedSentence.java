/**
 * 
 */
package cl2a;

import java.util.function.Function;

import cl2.CLExistential;

/**
 * @author ralph
 *
 */
public abstract class CLQuantifiedSentence extends CLBooleanSentence {

	private CLBindingSet bindings;
	private CLSentence body;

	public CLQuantifiedSentence(
			final CLCommentSet comments,
			final CLBindingSet bindings,
			final CLSentence body) {
		super(comments);
		if (bindings==null)
			throw new NullPointerException("Bindings of a CLQuantifiedSentence should not be null.");
		this.bindings = bindings;
		if (body==null)
			throw new NullPointerException("Body of a CLQuantifiedSentence should not be null.");
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

	public abstract CLQuantifiedSentence copy(
			final Function<CLCommentSet, ? extends CLCommentSet> commentsOperator,
			final Function<CLBindingSet, ? extends CLBindingSet> bindingsOperator,
			final Function<CLSentence, ? extends CLSentence> bodyOperator
			);

}
