package api4kbj;

public interface BasicSource extends Source {

	@Override
	default boolean isBasic() {
		return true;
	}

}
