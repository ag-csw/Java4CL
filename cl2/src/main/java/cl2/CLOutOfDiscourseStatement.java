/**
 * 
 */
package cl2;

import api4kbj.KRRLanguage;
import api4kbj.KnowledgeSourceLevel;

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
