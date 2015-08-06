package cl2list;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cl2.CLComment;
import cl2a.CLCommentSequence;



public class CLCommentSequenceList extends CLCommentSequence {

	private final List<CLComment> comments;

	public CLCommentSequenceList(final CLComment... comments) {
		this.comments = Arrays.asList(comments);
	}

	public CLCommentSequenceList(final List<CLComment> comments) {
		this.comments = comments;
	}

	public Collection<? extends CLComment> args(){
		return comments;
	}
	
	public int length(){
		return comments.size();
	}

	public CLCommentSequence concat(final CLCommentSequence incomments){
		List<CLComment> newcomments = comments;
		newcomments.addAll(incomments.args());
		return new CLCommentSequenceList(newcomments);
	}


}