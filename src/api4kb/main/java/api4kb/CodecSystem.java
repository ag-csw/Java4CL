package api4kb;

public interface EncodingSystem<T, S> {

	public S code( T t);
	
	public T decode( S s);

}
