package cl2jc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import cl2a.CLComment;
import cl2a.CLCommentSet;



public class CLCommentSetJC extends CLCommentSet {

	private final Set<CLComment> comments;

	public CLCommentSetJC(final CLComment... comments) {
		this.comments = new HashSet<CLComment>(Arrays.asList(comments));
	}

	public CLCommentSetJC(final Set<CLComment> comments) {
		this.comments = comments;
	}

	@Override
	public Set<CLComment> args(){
		return comments;
	}
	
	@Override
	public int length(){
		return comments.size();
	}

	@Override
	public CLCommentSet concat(final CLCommentSet incomments){
		Set<CLComment> newcomments = comments;
		newcomments.addAll(incomments.args());
		return new CLCommentSetJC(newcomments);
	}


	@Override
	public CLCommentSetJC copy() {
		return new CLCommentSetJC(args());
	}
}