package cl2a;


/**
 * This abstract class provides the syntactic category for all CL comments.
 * All comments have a piece of data, that in general could be any object.
 * Concrete classes should specialize the type of the data.
 * 
 * @author taraathan
 *
 */
public abstract class CLComment extends CLExpressionLike  {
    
	/**
     * Creates a CL comment that can be attached to CLCommentables.
     * The Object data argument provides the data of the CL comment.
     * 
     * @param data an Object giving the data of the CL comment
     */
	public CLComment(Object data) {
	super();
	if(data!=null)
	  this.data = data;
	else
		throw new NullPointerException("Symbol of a CLComment should not be null.");
}

	protected final Object data;

	/**
	 * Returns the data of the CL comment
	 * 
	 * @return the data Object
	 */
	public Object data() {
		return data;
	}

	/**
	 * Returns the hashcode of the CLComment.
	 * <p>
     * The hashcode of CLComments should depend only on its data.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	/**
	 * Returns a boolean that is true iff the object represents the same CL comment according to the CL abstract syntax.
	 * <p>
	 * Equality of CLComments should depend only on equality of their datas.
	 * 
	 * @param obj an Object to compare
	 * @return true if obj is essentially equal to this CLComment
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CLComment other = (CLComment) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	


	

}
