package cl2a;

import java.util.Collection;

import cl2.CLPrefix;



public abstract class CLPrefixSequence extends CLExpressionLike {

	public CLPrefixSequence() {
		super();
	}

	public abstract Collection<? extends CLPrefix> args();
	
	public abstract int length();

	public abstract CLPrefixSequence concat(CLPrefixSequence inprefixes);

}