package cl2;

import cl2a.CLCommentSequence;
import cl2a.CLPrefixSequence;
import cl2a.CLText;

public class CLImportation extends CLText {

	private final CLName name;

	public CLImportation(
			final CLPrefixSequence prefixes,
			final CLCommentSequence comments, 
			final CLName name) {
		super(prefixes, comments);
		this.name = name;
	}

	// getter for name
	CLName name(){
		return name;
	}

	@Override
	public CLImportation insertComments(CLCommentSequence incomments) {
		return new CLImportation( prefixes(), comments().concat(incomments), 
				name);
	}

	@Override
	public CLImportation insertPrefixes(CLPrefixSequence inprefixes) {
		return new CLImportation( prefixes().concat(inprefixes),
				comments(), name);
	}



}
