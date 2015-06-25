package cl2i;

import cl2a.CLExpressionLike;
import cl2a.CLPrefixSequence;


public interface CLPrefixable {
	
	public CLPrefixSequence prefixes();

	public CLExpressionLike insertPrefixes(CLPrefixSequence inprefixes);
}
