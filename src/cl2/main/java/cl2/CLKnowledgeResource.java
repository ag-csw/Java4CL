package cl2;

import api4kbj.BasicKnowledgeResource;
import api4kbj.KRRLanguage;

public interface CLKnowledgeResource extends BasicKnowledgeResource {

	@Override
	public default KRRLanguage defaultLanguage() {
		return CL.lang;
	}

	@Override
	public default boolean isBasic() {
		return true;
	}

}
