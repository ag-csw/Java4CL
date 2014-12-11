package api4kb;

public interface Mutable<A extends Immutable> {
	// getter for the Immutable snapshot
	A getSnapshot();

	// setter for the Immutable snapshot
	void setSnapshot(A snapshot);

}
