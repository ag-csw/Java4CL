package api4kbop;

public interface VoidOperation extends Operation<Void> {

	@Override
	default boolean hasSideEffects(){
		return true;
	}

}
