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
			final CLPrefixSequence prefixes, 
			final CLCommentSequence comments) {
		super(prefixes, comments);
	}

}
