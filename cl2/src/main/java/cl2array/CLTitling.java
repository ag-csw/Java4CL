/**
 * 
 */
package cl2array;

import cl2.CLName;
import cl2a.CLCommentSequence;
import cl2a.CLPrefixSequence;
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
			CLPrefixSequence prefixes,
			CLCommentSequence comments,
			CLName name, CLText text 
			) {
		super(prefixes, comments);
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
	public CLTitling insertComments(CLCommentSequence incomments) {
		return new CLTitling( prefixes(), comments().concat(incomments), 
				name, text);
	}


	@Override
	public CLTitling insertPrefixes(CLPrefixSequence inprefixes) {
		return new CLTitling( prefixes().concat(inprefixes),
				comments(), name, text);
	}



}
