package cl2;

import cl2a.CLCommentSet;
import cl2a.CLName;
import cl2a.CLText;

public class CLImportation extends CLText {

	private final CLName name;

	public CLImportation(
			final CLCommentSet comments, 
			final CLName name) {
		super(comments);
		this.name = name;
	}

	// getter for name
	CLName name(){
		return name;
	}

	@Override
	public CLImportation insertComments(CLCommentSet incomments) {
		return new CLImportation( comments().concat(incomments), 
				name);
	}

	@Override
	public CLImportation copy() {
		return new CLImportation(comments(), name);
	}

}
