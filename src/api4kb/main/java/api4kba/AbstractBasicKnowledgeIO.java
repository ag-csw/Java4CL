/**
 * 
 */
package api4kba;

import java.io.InputStream;

import api4kbj.BasicKnowledgeIO;
import api4kbj.KRRFormat;
import functional.IO;
import functional.IOWrapper;

/**
 * @author taraathan
 *
 */
public abstract class AbstractBasicKnowledgeIO implements BasicKnowledgeIO {

	public AbstractBasicKnowledgeIO(InputStream input, KRRFormat format) {
		this.wrappedInput = input;
		this.format = format;
	}

	private InputStream wrappedInput;
	private KRRFormat format;

	@Override
	public KRRFormat format() {
		return format;
	}

	@Override
	public <R extends InputStream> IO<R> build(Class<R> clazz) {
		// TODO use fj io to better handle input
		if (clazz.isAssignableFrom(wrappedInput.getClass())) {
			return new IOWrapper<R>(wrappedInput);
		}
		throw new IllegalArgumentException("Requested type" + clazz
				+ " is not supported");
	}

}
