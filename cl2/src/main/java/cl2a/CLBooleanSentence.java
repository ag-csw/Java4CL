/**
 * 
 */
package cl2a;



/**
 * @author ralph
 *
 */
public abstract class CLBooleanSentence extends CLSentence {

	public CLBooleanSentence(
			final CLCommentSet comments) {
		super( comments);
	}

	@Override
	public abstract CLBooleanSentence copy();

}
