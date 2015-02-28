package api4kbj;


public interface Mutable<A extends Immutable> {
	// getter for the Immutable snapshot
	A getSnapshot();

	// setter for the Immutable snapshot
	void setSnapshot(final A snapshot);
	
	//updater for the Immutable snapshot
	void updateSnapshot(final Mapping<A,A> mapping);

}
