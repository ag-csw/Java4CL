/**
 * 
 */
package cl2a;

import cl2array.CLTermSequenceArray;

/**
 * @author ralph
 *
 */
public abstract class CLInDiscourseStatement extends CLDiscourseStatement {

	public CLInDiscourseStatement(CLPrefixSequence prefixes,
			CLCommentSequence comments, CLTermSequenceArray args) {
		super(prefixes, comments, args);
	}


}
