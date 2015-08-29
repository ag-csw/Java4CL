/**
 * 
 */
package cl2a;



/**
 * @author ralph
 *
 */
public abstract class CLStatement extends CLExpression {

	public CLStatement(CLCommentSet comments) {
		super(comments);
	}
	
	@Override
	public abstract CLStatement copy();
	
}
