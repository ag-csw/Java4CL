/**
 * 
 */
package cl2array;

import java.util.Arrays;

import cl2.CL;
import cl2.CLCommentExpression;
import cl2.CLName;
import cl2a.CLStatement;
import cl2a.CLText;

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

	@Override
	public Iterable<CLCommentExpression> comments() {
		return Arrays.asList(comments);
	}

	@Override
	public CLTitling insertComments(CLCommentExpression... incomments) {
		return new CLTitling(name, text, CL.concatComments(comments, incomments));
	}



}
