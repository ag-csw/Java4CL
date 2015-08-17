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
		if(symbol!=null)
		  this.symbol = symbol;
		else
			throw new NullPointerException("Symbol of a CLName should not be null.");
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

	/**
	 * Returns the hashcode of the CLName.
	 * <p>
     * The hashcode of CLNames should depend only on its symbol.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	/**
	 * Returns a boolean that is true iff the object represents the same CL name according to the CL abstract syntax.
	 * <p>
	 * Equality of CLNames should depend only on equality of their symbols.
	 * 
	 * @param obj an Object to compare
	 * @return true if obj is essentially equal to this CLName
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CLName other = (CLName) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
	

}
