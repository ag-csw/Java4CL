package cl2;

import cl2a.CLComment;

public class CLStringComment extends CLComment  {


	public CLStringComment(String symbol) {
		super();
		this.symbol = symbol;
	}

	// private fields
	private String symbol;

	@Override
	public String symbol() {
		return symbol;
	}

}
