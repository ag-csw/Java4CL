/**
 * 
 */
package cl2a;


/**
 * @author ralph
 *
 */
public abstract class CLSimpleSentence extends CLSentence {

	public CLSimpleSentence(
			CLCommentSet comments) {
		super(comments);
	}
	
	@Override
	public abstract CLSimpleSentence copy();
	
}
