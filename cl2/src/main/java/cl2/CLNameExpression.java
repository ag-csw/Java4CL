package cl2;

public class CLNameExpression extends CLExpression {

	public CLNameExpression(CLPrefixExpression[] prefixes, String symbol) {
		super();
		this.prefixes = prefixes;
		this.symbol = symbol;
	}

	private CLPrefixExpression[] prefixes;
	private String symbol;

	public CLPrefixExpression[] prefixes() {
		return prefixes;
	}

	public String symbol() {
		return symbol;
	}

}
