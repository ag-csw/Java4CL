package cl2;

public class CLCommentExpression extends CLExpressionLike  {


	public CLCommentExpression(String symbol) {
		super();
		this.symbol = symbol;
	}

	// private fields
	private String symbol;

	public String symbol() {
		return symbol;
	}

}
