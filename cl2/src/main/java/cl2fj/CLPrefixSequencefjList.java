package cl2fj;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cl2.CLPrefix;
import cl2a.CLPrefixSequence;



public class CLPrefixSequencefjList extends CLPrefixSequence {

	private final List<CLPrefix> prefixes;

	public CLPrefixSequencefjList(final CLPrefix... prefixes) {
		this.prefixes = Arrays.asList(prefixes);
	}

	public CLPrefixSequencefjList(final List<CLPrefix> prefixes) {
		this.prefixes = prefixes;
	}

	public Collection<? extends CLPrefix> args(){
		return prefixes;
	}
	
	public int length(){
		return prefixes.size();
	}

	public CLPrefixSequence concat(final CLPrefixSequence inprefixes){
		List<CLPrefix> newprefixes = prefixes;
		newprefixes.addAll(inprefixes.args());
		return new CLPrefixSequencefjList(newprefixes);
	}


}