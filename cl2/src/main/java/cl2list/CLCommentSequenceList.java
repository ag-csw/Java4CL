package cl2list;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cl2a.CLComment;
import cl2a.CLCommentSequence;



public class CLCommentSequenceList extends CLCommentSequence {

	private final List<CLComment> comments;

	public CLCommentSequenceList(final CLComment... comments) {
		this.comments = Arrays.asList(comments);
	}

	public CLCommentSequenceList(final List<CLComment> comments) {
		this.comments = comments;
	}

	@Override
	public Collection<? extends CLComment> args(){
		return comments;
	}
	
	@Override
	public int length(){
		return comments.size();
	}

	@Override
	public CLCommentSequence concat(final CLCommentSequence incomments){
		List<CLComment> newcomments = comments;
		newcomments.addAll(incomments.args());
		return new CLCommentSequenceList(newcomments);
	}


}