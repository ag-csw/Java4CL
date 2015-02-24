package cl2;

public class CLName extends CLExpression {

	public CLName(String symbol) {
		super();
		this.symbol = symbol;
	}

	private String symbol;

	public String symbol() {
		return symbol;
	}

}
