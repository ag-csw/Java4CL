package api4kbop;

public interface VoidOperation extends Operation<Void> {

	default boolean hasSideEffects(){
		return true;
	}

}
