package cl2a;


/**
 * This abstract class provides the syntactic category for all CL interpretable names,
 * whose interpreation is not fixed, but is specified in a CL interpreation.  
 * Like all CL names, CL interpretable names can be components in CLExpressions,
 * including arguments of term sequences,
 * and operators in atomic sentences or functional terms.
 * Only CL interpretable names may serve as 
 * titles in titlings and importations,
 * and bindings in quantified sentence.
 * All CL names, including interpretable names, have a symbol, 
 * which in general could be any object.
 * Concrete classes should specialize the type of the symbol.
 * 
 * @author taraathan
 *
 */
public abstract class CLInterpretableName extends CLName {

    /**
     * Creates a CL interpretable name with an Object symbol.
     * The Object argument provides the symbol of the CL interpretable name.
     * 
     * @param symbol an Object giving the symbol of the CL interpretable name
     */
	public CLInterpretableName(Object symbol) {
		super(symbol);
	}
	
	@Override
	public abstract CLInterpretableName copy();
	
}
