package api4kbj;

import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = "http://www.omg.org/spec/API4KB/API4KBTerminology/Immtable")
public interface Immutable {
	// An interface for immutable classes.
	// All public fields must be final.
	// Private and protected fields need not be final, but should not have
	// public setter methods.
	// Lazy evaluation of these non-public fields is allowed, but their getters
	// should behave as if the fields were final, always returning the same
	// result.
	// Constructors must return a fully initialized object.

}
