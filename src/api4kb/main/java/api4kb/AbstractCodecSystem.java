package api4kb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;



public abstract class AbstractCodecSystem<T, S> implements CodecSystem<T, S> {

	public AbstractCodecSystem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public S code(T t) throws EncoderException, UnsupportedEncodingException, IOException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T decode(S s) throws DecoderException, UnsupportedEncodingException, IOException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
