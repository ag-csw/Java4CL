package cl2a;


/**
 * This abstract class provides the syntactic category for all CL sequence markers, 
 * which can be components in CLExpressions,
 * including arguments of CL term sequences,
 * and bindings in CL schemas.
 * All CL sequence markers have a symbol, which in general could be any object.
 * Concrete classes should specialize the type of the symbol.
 * 
 * @author taraathan
 *
 */
public abstract class CLSequenceMarker extends CLTermOrSequenceMarker {
		
	    /**
	     * Creates a CL sequence marker with an Object symbol.
	     * The Object symbol argument provides the symbol of the CL sequence marker.
	     * 
	     * @param symbol an Object giving the symbol of the CL sequence marker
	     */
		public CLSequenceMarker(Object symbol) {
			super();
			if(symbol==null)
				throw new NullPointerException("Symbol of a CLSequenceMarker should not be null.");
			  this.symbol = symbol;
		}

		protected final Object symbol;

		/**
		 * Returns the symbol of the CL sequence marker
		 * 
		 * @return the symbol Object
		 */
		public Object symbol() {
			return symbol;
		}

}
