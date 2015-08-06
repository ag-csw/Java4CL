package cl2;

import cl2a.CLName;

public class CLStringName extends CLName {

	public CLStringName(String symbol) {
		super();
		this.symbol = symbol;
	}

	private String symbol;

	public String symbol() {
		return symbol;
	}

}
