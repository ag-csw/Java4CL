package api4kbj;


import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = 
  "http://www.omg.org/spec/API4KB/API4KBTerminology/Mutable")
public interface Mutable<A extends Immutable> {
	// getter for the Immutable snapshot
	A getSnapshot();

	// setter for the Immutable snapshot
	void setSnapshot(final A snapshot);
	
	//updater for the Immutable snapshot
	void updateSnapshot(final Mapping<A,A> mapping);

}
