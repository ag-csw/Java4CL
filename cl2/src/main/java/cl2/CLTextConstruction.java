/**
 * 
 */
package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLText;
import cl2a.CLExpressionSequence;

/**
 * @author tara
 *
 */
public class CLTextConstruction
		extends CLText {

    public CLTextConstruction(
			CLCommentSequence comments,
			CLExpressionSequence args) {
		super(comments);
		this.args = args;
	}


	private final CLExpressionSequence args;


	public CLExpressionSequence expressions() {
		return args;
	}


	@Override
	public CLTextConstruction insertComments(CLCommentSequence incomments) {
		return new CLTextConstruction( comments().concat(incomments), 
				args);
	}

	@Override
	public CLTextConstruction copy() {
		return new CLTextConstruction(comments(), args);
	}

	
}

