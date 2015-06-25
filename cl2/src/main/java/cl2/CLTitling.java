/**
 * 
 */
package cl2;

import java.util.Arrays;

/**
 * @author ralph
 *
 */
public class CLTitling extends CLStatement {
	
	private CLName name;
	private CLText text;
	private CLCommentExpression[] comments;
	
	/**
	 * @param name
	 * @param text
	 */
	public CLTitling(CLName name, CLText text, CLCommentExpression... comments) {
		this.name = name;
		this.text = text;
		this.comments = comments;
	}

	public CLName name() {
		return name;
	}

	public CLText text() {
		return text;
	}

	public Iterable<CLCommentExpression> comments() {
		return Arrays.asList(comments);
	}

	@Override
	public CLTitling insertComments(CLCommentExpression... incomments) {
		return new CLTitling(name, text, CL.concatComments(comments, incomments));
	}



}
