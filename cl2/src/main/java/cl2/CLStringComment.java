package cl2;

import cl2a.CLComment;

public class CLStringComment extends CLComment  {
	
	//private String data;


	public CLStringComment(String data) {
		super(data);
		if (!CL.isValidStringSymbol(data))
			throw new IllegalArgumentException("Symbol must not contain CL-forbidden characters");
	}

	/**
	 * Returns the data of the CL comment.
	 * <p> 
	 *  The data of a CLStringComment should be equal to the parameter passed to the constructor.
	 */
	@Override
	public String data() {
		return (String) super.data();
	}

    /**
     * Returns the XCL2 sour syntax for the comment, as a string,
     * using the prefix cl: to indicate the XCL2 namespace.
     */
	@Override
	public String toString() {
		//TODO escape data to give valid XML
		return "<cl:Comment>" + CL.xmlContentEncode(data()) + "<\\cl:Comment>";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data() == null) ? 0 : data().hashCode());
		return result;
	}

    public boolean canEqual(Object other) {
        return (other instanceof CLStringComment);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLStringComment))
			return false;
		CLStringComment other = (CLStringComment) obj;
		if (!other.canEqual(this))
			return false;
		if (data() == null) {
			if (other.data() != null)
				return false;
		} else if (!data().equals(other.data()))
			return false;
		return true;
	}

}
