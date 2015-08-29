/**
 * 
 */
package cl2;

import cl2a.CLCommentSet;
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
			final CLCommentSet comments,
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
	public CLTitling insertComments(final CLCommentSet incomments) {
		return new CLTitling(comments().concat(incomments), 
				name, text);
	}

	@Override
	public CLTitling copy() {
		return new CLTitling(comments().copy(), name.copy(), text.copy());
	}

	/**
     * Returns the XCL2 sour syntax for the titling statement, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Titling>" + 
	            comments().toString() +
	            name.toString() +
	            text.toString() + "</cl:Titling>";
	}


}
