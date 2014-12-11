package cl2;

import api4kb.AbstractKnowledgeEncoding;
import api4kb.CodecSystem;
import api4kb.AbstractKnowledgeItem;

public class CLEncoding<T, S> extends AbstractKnowledgeEncoding<T, S> implements CLKnowledgeResource {

   public CLEncoding(S stream, CLDialectType<T> dialect, CodecSystem<T, S> system) {
	   super(stream, dialect, system);
	}

  
	@Override
	protected CLManifestation<T> evalManifestation() {
		//TODO implement eager lifting to manifestation manifestation
		// Case 1. from encoding
		// Case 2. from item
		return null;
	}

	@Override
	public <R> AbstractKnowledgeItem<T, S, R> reproduce(R destination) {
		// TODO implement eager lowering to item
		// Case 1. from encoding
		// Case 2. from manifestation
		// Case 3. from expression
		return null;
	}


}
