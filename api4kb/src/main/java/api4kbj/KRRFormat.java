package api4kbj;

import java.nio.charset.Charset;

public interface KRRFormat {

	String name();
	
	Charset charset();	

	default KRRLanguage language() {
		return dialect().language();
	}

	KRRDialect dialect();

}
