package cl2;

import cl2a.CLSequenceMarker;

public class CLStringSequenceMarker extends CLSequenceMarker {

	public CLStringSequenceMarker(String symbol) {
		super();
		this.symbol = symbol;
	}

	private String symbol;

	@Override
	public String symbol() {
		return symbol;
	}

}
