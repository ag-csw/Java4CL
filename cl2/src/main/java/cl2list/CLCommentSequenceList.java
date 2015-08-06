package cl2list;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cl2.CLStringComment;
import cl2a.CLCommentSequence;



public class CLCommentSequenceList extends CLCommentSequence {

	private final List<CLStringComment> comments;

	public CLCommentSequenceList(final CLStringComment... comments) {
		this.comments = Arrays.asList(comments);
	}

	public CLCommentSequenceList(final List<CLStringComment> comments) {
		this.comments = comments;
	}

	@Override
	public Collection<? extends CLStringComment> args(){
		return comments;
	}
	
	@Override
	public int length(){
		return comments.size();
	}

	@Override
	public CLCommentSequence concat(final CLCommentSequence incomments){
		List<CLStringComment> newcomments = comments;
		newcomments.addAll(incomments.args());
		return new CLCommentSequenceList(newcomments);
	}


}