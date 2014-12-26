package api4kbj;


public interface CodecSystem<T, S> {

	public S code(T t);

	public T decode(S s);

}
