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

	@Override
	public abstract CLComment copy();

}
