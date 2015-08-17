package cl2;

import cl2a.CLInterpretableName;

/**
 * A CL interpretable name with string symbol, which may be concretized directly 
 * (with minimal character escaping) in XCL2 or CLIF2.
 * <p>
 * Requirements from superclasses:
 *   A CLName is basic.
 *   A CLName's language should be CL.
 *   A CLName's knowledge source level should be EXPRESSION.
 
 * @author taraathan
 *
 */
public class CLStringInterpretableName extends CLInterpretableName {

    /**
     * Creates a CL interpretable name with a string symbol that can be used in CLExpressions.
     * The string argument provides the symbol of the CL name.
     * <p>
     * A CLStringInterpretableName constructor call with null argument should throw a NullPointerException.
     * 
     * @param symbol a string giving the symbol of the CL name
     */
	public CLStringInterpretableName(String symbol) {
		super(symbol);
		if (!CL.isValidStringSymbol(symbol))
			throw new IllegalArgumentException("Symbol must not contain CL-forbidden characters");
	}

	/**
	 * Returns the symbol of the CL name.
	 * <p> 
	 *  The symbol of a CLStringInterpretableName should be equal to the parameter passed to the constructor.
	 */
	@Override
	public String symbol() {
		return (String) super.symbol();
	}

    /**
     * Returns the XCL2 sour syntax for the CL name, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		//TODO escape symbol to give valid XML
		return "<cl:Name>" + CL.xmlContentEncode(symbol()) + "<\\cl:Name>";
	}	

}
