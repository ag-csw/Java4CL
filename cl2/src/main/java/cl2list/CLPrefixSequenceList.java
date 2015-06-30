package cl2list;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import cl2.CLPrefix;
import cl2a.CLPrefixSequence;



public class CLPrefixSequenceList extends CLPrefixSequence {

	private final List<CLPrefix> prefixes;

	public CLPrefixSequenceList(final CLPrefix... prefixes) {
		this.prefixes = Arrays.asList(prefixes);
	}

	public CLPrefixSequenceList(final List<CLPrefix> prefixes) {
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
		return new CLPrefixSequenceList(newprefixes);
	}


}