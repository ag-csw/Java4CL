/**
 * 
 */
package cl2a;

import cl2array.CLTermSequenceArray;

/**
 * @author ralph
 *
 */
public abstract class CLOutOfDiscourseStatement extends CLDiscourseStatement {

	public CLOutOfDiscourseStatement(CLPrefixSequence prefixes,
			CLCommentSequence comments, CLTermSequenceArray args) {
		super(prefixes, comments, args);
	}


}
