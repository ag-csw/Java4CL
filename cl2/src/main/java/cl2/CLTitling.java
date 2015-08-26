/**
 * 
 */
package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLName;
import cl2a.CLStatement;
import cl2a.CLText;

/**
 * @author ralph
 *
 */
public class CLTitling extends CLStatement {
	
	private final CLName name;
	private final CLText text;
	
	/**
	 * @param name
	 * @param text
	 */
	public CLTitling(
			final CLCommentSequence comments,
			final CLName name, 
			final CLText text 
			) {
		super(comments);
		this.name = name;
		this.text = text;
	}

	public CLName name() {
		return name;
	}

	public CLText text() {
		return text;
	}


	@Override
	public CLTitling insertComments(final CLCommentSequence incomments) {
		return new CLTitling(comments().concat(incomments), 
				name, text);
	}

	@Override
	public CLTitling copy() {
		return new CLTitling(comments(), name, text);
	}


}
