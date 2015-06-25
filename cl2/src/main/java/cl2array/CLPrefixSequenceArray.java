package cl2array;

import java.util.Arrays;

import cl2.CL;
import cl2.CLPrefixExpression;
import cl2a.CLPrefixSequence;

public class CLPrefixSequenceArray extends CLPrefixSequence {
	
	private final CLPrefixExpression[] prefixes;

	public CLPrefixSequenceArray(CLPrefixExpression... prefixes) {
		this.prefixes = prefixes;
	}

	@Override
	public Iterable<CLPrefixExpression> args() {
		return Arrays.asList(prefixes);
	}

	@Override
	public int length(){
		return prefixes.length;
	}
	
	@Override
	public CLPrefixSequence concat(CLPrefixSequence inprefixes) {
		int bLen = inprefixes.length();
		CLPrefixExpression[] b= new CLPrefixExpression[bLen];
		int i = 0;
        for (final CLPrefixExpression inprefix : inprefixes.args())
        {
            b[i] = inprefix;
        }		
        CLPrefixExpression[] c = CL.concatPrefixes(prefixes, b);
		return new CLPrefixSequenceArray(c);
	}

}
