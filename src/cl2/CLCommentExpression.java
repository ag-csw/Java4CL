package cl2;

import java.util.HashMap;
import java.util.Map.Entry;

import api4kb.ImmutableEnvironment;
import api4kb.DialectIncompatibleException;
import api4kb.KRRDialect;
import api4kb.KRRLanguage;
import api4kb.KnowledgeAsset;
import api4kb.KnowledgeResource;
import api4kb.Option;
import api4kb.None;

public class CLCommentExpression implements CLComment, CLExpression {
	public CLCommentExpression() {
		this("");
	}

	public CLCommentExpression(String symbol) {
		this(symbol, new None<CLCommentExpression>());
	}

    public CLCommentExpression(String symbol, Option<CLCommentExpression> comment) {
		this.symbol = symbol;
		this.comment = comment;
	}
    
    public <T> CLCommentExpression(
    		CLCommentManifestation<T> manifestation) {
        mapManifest = new HashMap< CLDialect<?>, CLCommentManifestation<?>>();
        mapManifest.put(manifestation.getDialect(), manifestation);
    }

	private String symbol;
    private Option<CLCommentExpression> comment;
	private final KRRLanguage lang = CL.lang;	
	private HashMap< CLDialect<?>, CLCommentManifestation<?>> mapManifest;
	// TODO implement cache for conceptualize method
	//private HashMap< ImmutableEnvironment, CLCommentAsset> mapAsset;

	public String getSymbol() {
		if (symbol == null) {
			Entry<CLDialect<?>, CLCommentManifestation<?>> entry = mapManifest.entrySet().iterator().next();
			symbol = entry.getValue().getSymbol();
		}
		return symbol;
	}

	@Override
	public Option<CLCommentExpression> getComment() {
		// check the cache
		if (comment == null){
			// TODO parse using methods appropriate to the format
			// and cache			
		}
		return comment;
	}

	@Override
	public KRRLanguage getLanguage() {
		return lang;
	}
	
	@Override
	public String toString() {
		try {
			return this.manifest(CL.xcl2dom).toString();
		} catch (DialectIncompatibleException e) {
			return e.getMessage();
		}
	}

	// determines if a KnowledgeResource is equal to this one
	public Boolean equals(KnowledgeResource that) {
	  return this.toString().equals(that);
	}
	
	// overriding hashCode to agree with toString
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	@Override
	public <T> CLCommentManifestation<?> manifest(KRRDialect<T> dialect)
			throws DialectIncompatibleException {
		if ( dialect.lang.equals(this.lang) ) {
			// check the cache
			if ( !mapManifest.containsKey(dialect) ) {
				//  TODO if not in the cache then 
				// create and cache				
				// Testing the construct while ignoring the comment
				CLCommentManifestation<T> manifestation = new CLCommentManifestation<T>(symbol, 
						((CLDialect<T>) dialect));
			}
			return mapManifest.get(dialect);
		}
		else {
			throw new DialectIncompatibleException();
		}
	}

	@Override
	public void clearManifest(KRRDialect<?> dialect) {
		this.mapManifest.remove(dialect);
		
	}

	@Override
	public KnowledgeAsset conceptualize(ImmutableEnvironment e) {
		// TODO  check for compatibiity between the language and
		// the environment
		// if not compatible, throw an appropriate exception
		// else check if in the cache
		// if not in the cache then
		// create, cache and return
		return null;
	}

	@Override
	public void clearConceptualize(ImmutableEnvironment environment) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void clear() {
		this.mapManifest.clear();
		//this.mapAsset.clear();		
	}


}
