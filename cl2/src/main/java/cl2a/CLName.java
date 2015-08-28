package cl2a;

/**
 * This abstract class provides the syntactic category for all CL names.
 * Concrete classes must be either interpretable names or interpreted names.
 * All CL names have a symbol, that in general could be any object.
 * Concrete classes should specialize the type of the symbol.
 * 
 * @author taraathan
 *
 */
public abstract class CLName extends CLTerm {

	
    /**
     * Creates a CL name that can be a component in CLExpressions.
     * The Object symbol argument provides the symbol of the CL name.
     * 
     * @param symbol an Object giving the symbol of the CL name
     */
	public CLName(Object symbol) {
		super();
		if(symbol==null)
			throw new NullPointerException("Symbol of a CLName should not be null.");
        this.symbol = symbol;
	}

	protected final Object symbol;

	/**
	 * Returns the symbol of the CL name
	 * 
	 * @return the symbol Object
	 */
	public Object symbol() {
		return symbol;
	}

}
