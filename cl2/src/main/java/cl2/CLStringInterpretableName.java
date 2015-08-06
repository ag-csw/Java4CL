package cl2;

import cl2a.CLName;

public class CLStringInterpretableName extends CLName {

	public CLStringInterpretableName(String symbol) {
		super();
		this.symbol = symbol;
	}

	private String symbol;

	@Override
	public String symbol() {
		return symbol;
	}

}
