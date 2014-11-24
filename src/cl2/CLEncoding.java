package cl2;

import api4kb.EncodingSystem;
import api4kb.KRRDialect;
import api4kb.KnowledgeEncoding;
import api4kb.KnowledgeManifestation;

public class CLEncoding<T, S> implements KnowledgeEncoding<T, S>,
CLKnowledgeResource {

   public CLEncoding(S value, EncodingSystem<T, S> system, CLDialect<T> dialect) {
		this.value = value;
		this.system = system;
		this.dialect = dialect;
	}

private S value;
   private KnowledgeManifestation<T> manifestation;
   private EncodingSystem<T, S> system;
   private CLDialect<T> dialect;
   
	@Override
	public void clear() {
		manifestation = null;		
	}

	@Override
	public S getValue() {
		return value;
	}

	@Override
	public void clearDecode() {
		manifestation = null;		
	}

	@Override
	public EncodingSystem<T, S> getEncodingSystem() {
		return system;
	}

	@Override
	public KRRDialect<T> getDialect() {
		return dialect;
	}

	@Override
	public KnowledgeManifestation<T> decode() {
		if (manifestation == null) {
			T x = system.decode(value);
			if (CL.isComment(x, dialect)) {
			  manifestation = new CLCommentManifestation<T>(x, dialect);
			}  
		}
		return manifestation;
	} 

}
