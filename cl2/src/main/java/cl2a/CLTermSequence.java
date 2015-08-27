package cl2a;

import java.util.List;



public abstract class CLTermSequence extends CLExpressionLike {
	
	//private List<? extends CLTermOrSequenceMarker> args;
	
	public CLTermSequence() {
		super();
	}

	public abstract List<? extends CLTermOrSequenceMarker> args();

	public abstract int length();

	public abstract CLTermSequence concat(CLTermSequence inargs);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((args() == null) ? 0 : args().hashCode());
		return result;
	}

    public boolean canEqual(Object other) {
        return (other instanceof CLTermSequence);
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CLTermSequence))
			return false;
		CLTermSequence other = (CLTermSequence) obj;
		if (!other.canEqual(this))
			return false;
		if (args() == null) {
			if (other.args() != null)
				return false;
		} else if (!args().equals(other.args()))
			return false;
		return true;
	}

}