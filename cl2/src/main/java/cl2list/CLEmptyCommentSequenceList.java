package cl2list;

import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;

import cl2.CLComment;
import cl2a.CLCommentSequence;



public class CLEmptyCommentSequenceList extends CLCommentSequence {

	public CLEmptyCommentSequenceList() {
		super();
	}

	@Override
	public Collection<? extends CLComment> args(){
		return CollectionUtils.emptyCollection();
	}
	
	@Override
	public int length(){
		return 0;
	}

	@Override
	public CLCommentSequence concat(CLCommentSequence incomments){
		return incomments;
	}


}