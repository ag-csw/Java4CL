/**
 * 
 */
package cl2a;

import cl2.CLCommentExpression;
import cl2array.CLTermSequence;

/**
 * @author ralph
 *
 */
public abstract class CLOutOfDiscourseStatement extends CLDiscourseStatement {

	/**
	 * @param terms
	 */
	public CLOutOfDiscourseStatement(CLTermSequence args) {
		super(args);
	}

	@Override
	public CLOutOfDiscourseStatement insertComments(CLCommentExpression... comments) {
		// TODO Auto-generated method stub
		return null;
	}

}
