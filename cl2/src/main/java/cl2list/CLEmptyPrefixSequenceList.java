package cl2list;

import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;

import cl2.CLPrefix;
import cl2a.CLPrefixSequence;



public class CLEmptyPrefixSequenceList extends CLPrefixSequence {

	public CLEmptyPrefixSequenceList() {
		super();
	}

	public Collection<? extends CLPrefix> args(){
		return CollectionUtils.emptyCollection();
	}
	
	public int length(){
		return 0;
	}

	public CLPrefixSequence concat(CLPrefixSequence inprefixes){
		return inprefixes;
	}


}