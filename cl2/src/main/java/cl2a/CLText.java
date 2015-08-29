/**
 * 
 */
package cl2a;


/**
 * @author ralph
 *
 */
public abstract class CLText extends CLExpression  {

	public CLText(CLCommentSet comments) {
		super(comments);
	}

	@Override
	public abstract CLText copy();


}
