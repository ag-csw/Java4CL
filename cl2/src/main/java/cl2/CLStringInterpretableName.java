package cl2;

import cl2a.CLInterpretableName;

public class CLStringInterpretableName extends CLInterpretableName {

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
