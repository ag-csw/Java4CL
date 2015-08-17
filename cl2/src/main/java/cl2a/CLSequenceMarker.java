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
			if(symbol!=null)
			  this.symbol = symbol;
			else
				throw new NullPointerException("Symbol of a CLSequenceMarker should not be null.");
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

		/**
		 * Returns the hashcode of the CLSequenceMarker.
		 * <p>
	     * The hashcode of CLSequenceMarkers should depend only on its symbol.
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
			return result;
		}

		/**
		 * Returns a boolean that is true iff the object represents the same CL sequence marker according to the CL abstract syntax.
		 * <p>
		 * Equality of CLSequenceMarkers should depend only on equality of their symbols.
		 * 
		 * @param obj an Object to compare
		 * @return true if obj is essentially equal to this CLSequenceMarker
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CLSequenceMarker other = (CLSequenceMarker) obj;
			if (symbol == null) {
				if (other.symbol != null)
					return false;
			} else if (!symbol.equals(other.symbol))
				return false;
			return true;
		}
		


}
