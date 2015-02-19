package cl2;

import api4kbj.BasicKnowledgeResource;

public interface CLKnowledgeResource extends BasicKnowledgeResource {

	@Override
	public default boolean isBasic() {
		return true;
	}

}
