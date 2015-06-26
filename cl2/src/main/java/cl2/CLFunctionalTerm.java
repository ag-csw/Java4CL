package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLPrefixSequence;
import cl2a.CLTerm;
import cl2a.CLTermSequence;
import cl2i.CLCommentable;
import cl2i.CLPrefixable;

public class CLFunctionalTerm extends CLTerm implements CLCommentable, CLPrefixable {
	
	private CLPrefixSequence prefixes;
	private CLCommentSequence comments;
	private final CLTerm operator;
	private final CLTermSequence args;


	public CLFunctionalTerm(
			final CLPrefixSequence prefixes, 
			final CLCommentSequence comments,
			final CLTerm operator, 
			final CLTermSequence args) {
		this.prefixes = prefixes;
		this.comments = comments;
		this.operator = operator;
		this.args = args;

	}

	@Override
	public CLCommentSequence comments() {
		return comments;
	}

	@Override
	public CLPrefixSequence prefixes() {
		return prefixes;
	}
	
	/**
	 * @return the operator
	 */
	public CLTerm operator() {
		return operator;
	}

	/**
	 * @return the arguments
	 */
	public CLTermSequence args() {
		return args;
	}


	@Override
	public CLFunctionalTerm insertComments(final CLCommentSequence incomments) {
		return new CLFunctionalTerm( prefixes(), comments().concat(incomments), 
				operator, args);
	}


	@Override
	public CLFunctionalTerm insertPrefixes(final CLPrefixSequence inprefixes) {
		return new CLFunctionalTerm( prefixes().concat(inprefixes),
				comments(), operator, args);
	}

	
	


}
