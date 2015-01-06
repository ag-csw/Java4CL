package cl2;

import krconfigured.BasicKnowledgeResourceConfigured;
import api4kbj.KRRLanguage;

public interface CLKnowledgeResource extends BasicKnowledgeResourceConfigured {

	@Override
	public default KRRLanguage defaultLanguage() {
		return CL.lang;
	}

	@Override
	public default boolean isBasic() {
		return true;
	}

}
