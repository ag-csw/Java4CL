package cl2;

import cl2a.CLTermOrSequenceMarker;

public class CLSequenceMarker extends CLTermOrSequenceMarker {

	public CLSequenceMarker(String symbol) {
		super();
		this.symbol = symbol;
	}

	private String symbol;

	public String symbol() {
		return symbol;
	}

}
