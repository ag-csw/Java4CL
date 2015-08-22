package api4kbj;

/**
 * An interface for immutable classes.
 * All public fields must be final.
 * Private and protected fields need not be final, but should not have
 * public setter methods.
 * Lazy evaluation of these non-public fields is allowed, but their getters
 * should behave as if the fields were final, always returning the same
 * result.
 * Constructors must return a fully initialized object.
 * @author taraathan
 * @api4kp.OntologyClass <a href="http://www.omg.org/spec/API4KB/API4KBTerminology/Immutable">API4KBTerminology/Immutable</a>
 */
public interface Immutable {

}
