/**
 * 
 */
package cl2;

import java.util.Iterator;

import api4kbj.KRRLanguage;
import api4kbj.KnowledgeSourceLevel;
import cl2a.CLCommentSequence;
import cl2a.CLExpression;
import cl2a.CLPrefixSequence;
import cl2a.CLText;
import cl2a.CLExpressionSequence;

/**
 * @author tara
 *
 */
public class CLTextConstruction
		extends CLText {

    public CLTextConstruction(CLPrefixSequence prefixes,
			CLCommentSequence comments,
			CLExpressionSequence args) {
		super(prefixes, comments);
		this.args = args;
	}


	private final CLExpressionSequence args;


	public CLExpressionSequence texts() {
		return args;
	}


	@Override
	public CLTextConstruction insertComments(CLCommentSequence incomments) {
		return new CLTextConstruction( prefixes(), comments().concat(incomments), 
				args);
	}


	@Override
	public CLTextConstruction insertPrefixes(CLPrefixSequence inprefixes) {
		return new CLTextConstruction( prefixes().concat(inprefixes),
				comments(), args);
	}
	
}

