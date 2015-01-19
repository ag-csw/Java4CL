package functional;

public interface EquivalenceRelation {
		
	default boolean apply(Object x, Object y){
		if (x.equals(y)){
			return true;
		}
		return false;
	}

}
