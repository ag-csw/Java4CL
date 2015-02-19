package api4kbj;

public interface Mutable<A extends Immutable> {
	// getter for the Immutable snapshot
	A getSnapshot();

	// setter for the Immutable snapshot
	void setSnapshot(A snapshot);

}
