package cl2;

import api4kba.AbstractKRRFormatType;
import api4kbj.KRRFormat;

public class CLFormatType<T> extends AbstractKRRFormatType<T> {

	public CLFormatType(String name, KRRFormat format, Class<? extends T> clazz) {
		super(name, format, clazz);
	}

	@Override
	public void setClass(Class<? extends T> clazz) {
		this.clazz = clazz;
		
	}

}
