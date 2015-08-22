package api4kbop;

import elevation.Liftable;
import elevation.Lowerable;

public interface LiftingAction<T extends Liftable, R extends Lowerable> extends
		UnaryAction<T, R> {

}
