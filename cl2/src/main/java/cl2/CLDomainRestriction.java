package cl2;

import java.util.function.Function;

import cl2a.CLCommentSet;
import cl2a.CLTerm;
import cl2a.CLText;

public class CLDomainRestriction extends CLText {

	private final CLTerm domain;
	private final CLText body;

	public CLDomainRestriction(
			final CLCommentSet comments, 
			final CLTerm domain,
			final CLText body) {
		super(comments);
		this.domain = domain;
		this.body = body;
	}

	public CLTerm domain(){
		return domain;
	}

	public CLText body(){
		return body;
	}

	@Override
	public CLDomainRestriction insertComments(CLCommentSet incomments) {
		return new CLDomainRestriction( comments().concat(incomments), 
				domain(), body());
	}
	
	public CLDomainRestriction mapText(Function<CLText, CLText> f){
		return new CLDomainRestriction(comments(), domain(),
				f.apply(this));
	}

	@Override
	public CLDomainRestriction copy() {
		return new CLDomainRestriction(comments().copy(), domain().copy(), body().copy());
	}

	/**
     * Returns the XCL2 sour syntax for the domain restriction text, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Restrict>" + 
	            comments().toString() +
	            domain().toString() +
	            body().toString() + "<\\cl:Restrict>";
	}

}
