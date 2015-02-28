package functional;

public interface EquivalenceRelation {

	default boolean apply(final Object x, final Object y) {
		if (x.equals(y)) {
			return true;
		}
		return false;
	}

}
