package cl2;

import cl2a.CLInterpretedName;

/**
 * 
 * @author taraathan
 * 
 *         A CLName is basic. A CLName's language should be CL. A CLName's
 *         knowledge source level should be EXPRESSION.
 *
 */
public class CLStringIriInterpretedName extends CLInterpretedName {
	
	//private String symbol;
	//private CLIRI datatype;

	/**
	 * Creates a CL interpreted name with a string symbol and IRI-specified
	 * datatype that can be used in CLExpressions. The String symbol argument
	 * provides the symbol of the name. The String datatype argument provides
	 * the (absolute) IRI of the datatype.
	 * <p>
	 * The symbol does not necessarily belong to the lexical space of the datatype.
	 * 
	 * The hasViolations method should check whether the symbol belongs to the lexical space of the datatype.
	 * 
	 * @param symbol
	 *            a String giving the symbol of the CL interpreted name
	 * @param datatype
	 *            an com.hp.hpl.jena.iri.IRI giving the absolute IRI for the datatype of the CL
	 *            interpreted name
	 */
	public CLStringIriInterpretedName(String symbol, CLIRI datatype) {
		super(symbol, datatype);
	}
	
	/**
	 * Static factory method to create a CL interpreted name with IRI datatype from two strings: a string for the symbol
	 * and another string for the datatype.
	 * 
	 * @param symbol a String symbol for the CL interpreted name
	 * @param datatypestring a String for constructing the IRI datatype for the CL interpreted name
	 * @return the CL interpreted name
	 */
	public static CLStringIriInterpretedName createCLStringIriInterpretedNameFromStringIRI(String symbol,
			String datatypestring) {
		if ((symbol == null) || (datatypestring == null))
			throw new NullPointerException("arguments may not be null ");
        CLIRI datatype = new CLIRI(datatypestring);
		return new CLStringIriInterpretedName(symbol, datatype);
	}

	public static CLStringIriInterpretedName createCLStringIriInterpretedNameFromString(String symbol){
		return createCLStringIriInterpretedNameFromStringIRI(symbol, "http://www.w3.org/2001/XMLSchema#string");
	}

	/**
	 * Returns the symbol of the CL name.
	 * <p>
	 * The symbol of a CLStringInterpretedName should be equal to the parameter
	 * passed to the constructor.
	 */
	@Override
	public String symbol() {
		return (String) super.symbol();
	}

	/**
	 * Returns the datatype of the CL name.
	 * <p>
	 * The datatype of a CLStringIriInterpretedName should be equal to the
	 * parameter passed to the constructor.
	 */
	@Override
	public CLIRI datatype() {
		return (CLIRI) super.datatype();
	}

	@Override
	public CLStringIriInterpretedName copy() {
		return new CLStringIriInterpretedName(symbol(), datatype());
	}
	
	/**
	 * Returns the XCL2 sour syntax for the CL interpreted name, as a string,
	 * using the prefix cl: to indicate the XCL2 namespace.
	 */
	@Override
	public String toString() {
		return "<cl:Data datatype=" + CL.xmlAttributeEncodeIri(datatype()) + ">" + CL.xmlContentEncode(symbol())
				+ "</cl:Data>";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datatype() == null) ? 0 : datatype().hashCode());
		result = prime * result + ((symbol() == null) ? 0 : symbol().hashCode());
		return result;
	}

    public boolean canEqual(Object other) {
        return (other instanceof CLStringIriInterpretedName);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLStringIriInterpretedName))
			return false;
		CLStringIriInterpretedName other = (CLStringIriInterpretedName) obj;
		if (!other.canEqual(this))
			return false;
		if (datatype() == null) {
			if (other.datatype() != null)
				return false;
		} else if (!datatype().equals(other.datatype()))
			return false;
		if (symbol() == null) {
			if (other.symbol() != null)
				return false;
		} else if (!symbol().equals(other.symbol()))
			return false;
		return true;
	}



}
