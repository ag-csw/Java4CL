package api4kbj;

import java.io.UnsupportedEncodingException;

public interface CodecSystem<T, S> {

	public S code(T t) throws UnsupportedEncodingException;

	public T decode(S s) throws UnsupportedEncodingException;

}
