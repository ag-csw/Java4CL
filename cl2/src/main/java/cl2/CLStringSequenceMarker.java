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
		//TODO escape symbol to give valid XML
		return "<cl:Marker>" + CL.xmlContentEncode(symbol()) + "<\\cl:Marker>";
	}

}
