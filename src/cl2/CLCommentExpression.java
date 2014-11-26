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
	
	// Package-Private Constructors
	 // Component-based constructor
    CLCommentExpression(String symbol, Option<CLCommentExpression> comment) {
		this.symbol = symbol;
		this.comment = comment;
        mapManifest = new HashMap< CLDialect<?>, CLCommentManifestation<?>>();
	}
    
	// Lazy lifting constructor - argument is an Encoding
    <T> CLCommentExpression(
    		CLCommentManifestation<T> manifestation) {
        mapManifest = new HashMap< CLDialect<?>, CLCommentManifestation<?>>();
        mapManifest.put(manifestation.getDialect(), manifestation);
    }

	// private fields
	private String symbol;
    private Option<CLCommentExpression> comment;
	private final KRRLanguage lang = CL.lang;	
	private final HashMap< CLDialect<?>, CLCommentManifestation<?>> mapManifest;
	// TODO implement cache for conceptualize method
	//private final HashMap< ImmutableEnvironment, CLCommentAsset> mapAsset;

	// Static Factory Methods
	public static CLCommentExpression eagerNewInstance(
			String symbol, 
			Option<CLCommentExpression> comment){
		return new CLCommentExpression(symbol, comment);
	}

	public static CLCommentExpression  eagerNewInstance(
			String symbol) {
		return eagerNewInstance(symbol, new None<CLCommentExpression>());
	}

	
	public static CLCommentExpression  eagerNewInstance() {
		return eagerNewInstance("");
	}

	public static <T> CLCommentExpression  lazyNewInstance(
    		CLCommentManifestation<T> manifestation) {
		return new CLCommentExpression(manifestation);
	}

	
	
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
	public <T> CLCommentManifestation<T> manifest(KRRDialect<T> dialect)
			throws DialectIncompatibleException {
		CLCommentManifestation<T> manifestation;
		if ( dialect.lang.equals(this.lang) ) {
			// check the cache
			CLDialect<T> cldialect = (CLDialect<T>) dialect ;
			if ( !mapManifest.containsKey(dialect) ) {
				//  TODO if not in the cache then 
				// create and cache				
				manifestation = CLCommentManifestation.eagerNewInstance(symbol, 
						cldialect);
				mapManifest.put(cldialect, manifestation);
			}
			else {
				// This cast is safe because the cache is created with key-value pairs
				// that have matching generic parameters.
				@SuppressWarnings("unchecked")
				CLCommentManifestation<T> cachedManifestation = (CLCommentManifestation<T>) mapManifest.get(cldialect);
				manifestation = cachedManifestation;
			}
			return manifestation; 
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
