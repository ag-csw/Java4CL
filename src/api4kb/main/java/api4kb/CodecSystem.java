package api4kb;


public interface CodecSystem<T, S> {

	public S code( T t) throws EncoderException, Exception;
	
	public T decode( S s) throws DecoderException, Exception;

}
