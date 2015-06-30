package cl2;

import java.util.function.Function;

import cl2a.CLCommentSequence;
import cl2a.CLPrefixSequence;
import cl2a.CLTerm;
import cl2a.CLText;

public class CLDomainRestriction extends CLText {

	private final CLTerm domain;
	private final CLText body;

	public CLDomainRestriction(
			final CLPrefixSequence prefixes,
			final CLCommentSequence comments, 
			final CLTerm domain,
			final CLText body) {
		super(prefixes, comments);
		this.domain = domain;
		this.body = body;
	}

	public CLTerm term(){
		return domain;
	}

	public CLText text(){
		return body;
	}

	@Override
	public CLDomainRestriction insertComments(CLCommentSequence incomments) {
		return new CLDomainRestriction( prefixes(), comments().concat(incomments), 
				domain, body);
	}

	@Override
	public CLDomainRestriction insertPrefixes(CLPrefixSequence inprefixes) {
		return new CLDomainRestriction( prefixes().concat(inprefixes),
				comments(), domain, body);
	}
	
	public CLDomainRestriction mapText(Function<CLText, CLText> f){
		return new CLDomainRestriction(prefixes(), comments(), domain,
				f.apply(this));
	}



}
