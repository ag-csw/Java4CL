package functional;

public class Functional {
	public static EquivalenceRelation ID = new EquivalenceRelation() {

		@Override
		public boolean apply(Object x, Object y) {
			if((x != null) & x.equals(y)) return true;
			return false;
		}
	};

}
