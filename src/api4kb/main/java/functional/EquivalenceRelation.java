package functional;

public interface EquivalenceRelation<T> {
	
	default boolean apply(T x, T y){
		if (x.equals(y)){
			return true;
		}
		return false;
	}

}
