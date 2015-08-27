package cl2jc;

import java.util.Collections;
import java.util.Set;

import cl2.CLStringComment;
import cl2a.CLCommentSet;
import cl2array.CLBindingSequenceArray;



public class CLEmptyCommentSetJC extends CLCommentSet {

	public CLEmptyCommentSetJC() {
		super();
	}

	@Override
	public Set<CLStringComment> args(){
		return Collections.emptySet();
	}
	
	@Override
	public int length(){
		return 0;
	}

	@Override
	public CLCommentSet concat(CLCommentSet incomments){
		return incomments;
	}


	@Override
	public CLEmptyCommentSetJC copy() {
		return new CLEmptyCommentSetJC();
	}
}