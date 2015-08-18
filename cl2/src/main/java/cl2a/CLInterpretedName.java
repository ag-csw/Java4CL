package cl2a;


/**
 * This abstract class provides the syntactic category for all CL interpreted names.
 * All names have a symbol, that in general could be any object.
 * Further, all interpreted names have a datatype, that in general could be any object.
 * Concrete classes should specialize the type of the symbol and datatype.
 * 
 * @author taraathan
 *
 */
public abstract class CLInterpretedName extends CLName {

    /**
     * Creates a CL interpretable name with an object symbol that can be a component in CLExpressions.
     * The Object argument provides the symbol of the name.
     * 
     * @param symbol an Object giving the symbol of the name
     */
	public CLInterpretedName(Object symbol, Object datatype) {
		super(symbol);
		if(datatype!=null)
			  this.datatype = datatype;
			else
				throw new NullPointerException("Symbol of a CLStringInterpretableName should not be null.");
		
	}

	protected final Object datatype;

	public Object datatype() {
		return datatype;
	}

}
