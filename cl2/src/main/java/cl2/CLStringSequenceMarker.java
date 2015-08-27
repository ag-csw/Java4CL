package cl2;

import cl2a.CLSequenceMarker;

/**
 * A CL sequence marker with string symbol, which may be concretized directly 
 * (with minimal character escaping) in XCL2 or CLIF2.
 * <p>
 * Requirements from superclasses:
 *   A CLSequenceMarker is basic.
 *   A CLSequenceMarker's language should be CL.
 *   A CLSequenceMarker's knowledge source level should be EXPRESSION.
 *
 * @author taraathan
 * 
 */
public class CLStringSequenceMarker extends CLSequenceMarker {
	
	//private String symbol;

    /**
     * Creates a CL sequence marker with a string symbol that can be used in CLExpressions.
     * The string argument provides the symbol of the CL sequence marker.
     * <p>
     * A CLStringSequenceMarker constructor call with null argument should throw a NullPointerException.
     * 
     * @param symbol a string giving the symbol of the CL sequence marker
     */
	public CLStringSequenceMarker(String symbol) {
		super(symbol);
		if (!CL.isValidStringSymbol(symbol))
			throw new IllegalArgumentException("Symbol must not contain CL-forbidden characters");
	}


	/**
	 * Returns the symbol of the CL sequence marker.
	 * <p> 
	 *  The symbol of a CLStringSequenceMarker should be equal to the parameter passed to the constructor.
	 */
	@Override
	public String symbol() {
		return (String) super.symbol();
	}

    /**
     * Returns the XCL2 sour syntax for the CL sequence marker, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		return "<cl:Marker>" + CL.xmlContentEncode(symbol()) + "<\\cl:Marker>";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol() == null) ? 0 : symbol().hashCode());
		return result;
	}

    public boolean canEqual(Object other) {
        return (other instanceof CLStringSequenceMarker);
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLStringSequenceMarker))
			return false;
		CLStringSequenceMarker other = (CLStringSequenceMarker) obj;
		if (!other.canEqual(this))
			return false;
		if (symbol() == null) {
			if (other.symbol() != null)
				return false;
		} else if (!symbol().equals(other.symbol()))
			return false;
		return true;
	}

	@Override
	public CLStringSequenceMarker copy() {
		return new CLStringSequenceMarker(symbol());
	}
	
}
