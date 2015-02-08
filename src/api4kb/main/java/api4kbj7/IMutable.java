package api4kbj7;


public interface IMutable<A extends IImmutable> {

	// getter for the Immutable snapshot representing the current state
	A getSnapshot();

	// setter for the Immutable snapshot
	void setSnapshot(A snapshot);

}