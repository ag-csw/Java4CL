package cl2;

import api4kb.AbstractKnowledgeEncoding;
import api4kb.EncodingSystem;
import api4kb.KnowledgeItem;
import api4kb.KnowledgeManifestation;

public class CLEncoding<T, S> extends AbstractKnowledgeEncoding<T, S> {

   public CLEncoding(S stream, CLDialect<T> dialect, EncodingSystem<T, S> system) {
	   super(stream, dialect, system);
	}

  
	@Override
	protected KnowledgeManifestation<T> evalManifestation() {
		//TODO implement eager lifting to manifestation manifestation
		// Case 1. from encoding
		// Case 2. from item
		return null;
	}

	@Override
	public <R> KnowledgeItem<T, S, R> reproduce(R destination) {
		// TODO implement eager lowering to item
		// Case 1. from encoding
		// Case 2. from manifestation
		// Case 3. from expression
		return null;
	}


}