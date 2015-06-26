package cl2array;

import java.util.Arrays;
import java.util.Collection;

import cl2.CLPrefix;
import cl2a.CLPrefixSequence;

public class CLPrefixSequenceArray extends CLPrefixSequence {
	
	private final CLPrefix[] prefixes;

	public CLPrefixSequenceArray(final CLPrefix... prefixes) {
		this.prefixes = prefixes;
	}

	@Override
	public Collection<? extends CLPrefix> args() {
		return Arrays.asList(prefixes);
	}

	@Override
	public int length(){
		return prefixes.length;
	}
	
	@Override
	public CLPrefixSequence concat(final CLPrefixSequence inprefixes) {
		int bLen = inprefixes.length();
		CLPrefix[] b= new CLPrefix[bLen];
		int i = 0;
        for (final CLPrefix inprefix : inprefixes.args())
        {
            b[i++] = inprefix;
        }		
        CLPrefix[] c = CLArray.concatPrefixes(prefixes, b);
		return new CLPrefixSequenceArray(c);
	}

}
