/**
 * 
 */
package cl2a;



/**
 * @author ralph
 *
 */
public abstract class CLSentence extends CLExpression {

	public CLSentence(
			final CLCommentSet comments) {
		super(comments);
	}

	@Override
	public abstract CLSentence copy();

}
