package cl2;

import cl2a.CLExpressionLike;

public class CLComment extends CLExpressionLike  {


	public CLComment(String symbol) {
		super();
		this.symbol = symbol;
	}

	// private fields
	private String symbol;

	public String symbol() {
		return symbol;
	}

}
