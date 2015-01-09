package api4kbop;

import elevation.Liftable;
import elevation.Lowerable;

public interface LoweringAction<T extends Lowerable, R extends Liftable> extends Action<T, R> {

}
