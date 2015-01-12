package api4kbj;

public interface ClassWrapper<S> {

	String name();

	Class<? extends S> asClass();

}