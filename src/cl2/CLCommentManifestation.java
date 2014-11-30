package cl2;

import java.util.HashMap;

import api4kb.AbstractKnowledgeManifestation;
import api4kb.Configuration;
import api4kb.EncodingSystem;
import api4kb.EncodingSystemIncompatible;
import api4kb.KnowledgeEncoding;
import api4kb.None;
import api4kb.Option;
import api4kb.Some;

public class CLCommentManifestation<T> extends AbstractKnowledgeManifestation<T> implements CLComment, CLManifestation<T> {

	// Package-Private Constructors
	// Wrapper-based constructor
	// TODO elevate this constructor to AbstractKnowledgeManifestation
	 CLCommentManifestation(T value, CLDialect<T> dialect) {
		this.value = value;
		this.dialect = dialect;
        mapEncoding = new HashMap< EncodingSystem<T, ?>, KnowledgeEncoding<T, ?>>();
	}
	
	 // Component-based constructor
	// TODO elevate this constructor to AbstractCLComment
	CLCommentManifestation(String symbol, Option<CLCommentManifestation<T>> comment, CLDialect<T> dialect) {
		this.symbol = symbol;
		this.comment = comment;
		this.dialect = dialect;
        mapEncoding = new HashMap< EncodingSystem<T, ?>, KnowledgeEncoding<T, ?>>();
        // TODO Option 1 - lazy evaluation of "value"
        // details of building value are contained in the getValue method
        // TODO Option 2 - eager evaluation of "value"
        // TODO For the specified dialect, construct the T value
		//if (dialect == CL.clif) {} ...			
	}
	
	// Lazy lowering constructor - argument is expression and dialect
	// TODO elevate this method implementation to AbstractKnowledgeManifestation
	 CLCommentManifestation(CLCommentExpression expression, CLDialect<T> dialect) {
		this.expression = expression;
		this.dialect = dialect;
       mapEncoding = new HashMap< EncodingSystem<T, ?>, KnowledgeEncoding<T, ?>>();
	}

	// TODO Lazy lifting constructor - argument is an Encoding
    // TODO elevate this method implementation to AbstractKnowledgeManifestation
	
	// private fields
	private T value;
	private String symbol;
	private Option<CLCommentManifestation<T>> comment;
	private CLDialect<T> dialect;
	private Configuration<?> configuration;
	private final HashMap< EncodingSystem<T, ?>, KnowledgeEncoding<T, ?>> mapEncoding;
	private CLCommentExpression expression;

	// TODO Static Factory Methods
	// TODO elevate this constructor to AbstractCLComment
	public static <T> CLCommentManifestation<?> eagerNewInstance(
			String symbol, 
			Option<CLCommentManifestation<T>> comment,
			CLDialect<T> dialect
			){
		return new CLCommentManifestation<T>(symbol, comment, dialect);
	}
	
	// TODO elevate this constructor to AbstractCLComment
	public static <T> CLCommentManifestation<T> eagerNewInstance(
			String symbol, 
			CLDialect<T> dialect
			){
		return new CLCommentManifestation<T>(symbol, new None<CLCommentManifestation<T>>(), dialect);
	}
	
    // TODO elevate this method implementation to AbstractKnowledgeManifestation
	@Override
	public T getValue() {
		return value;
	}


	// TODO elevate this constructor to AbstractCLComment
	@Override
	public String getSymbol() {
		// check the cache
		if (symbol == null){
			// TODO parse using methods appropriate to the format
			// and cache			
		}
		return symbol;
	}

	// TODO elevate this constructor to AbstractCLKnowledgeResource
	@Override
	public Option<CLCommentManifestation<T>> getComment() {
		// check the cache
		if (comment == null){
			// TODO parse using methods appropriate to the format
			// and cache			
		}
		return comment;
	}


    // TODO elevate this method implementation to AbstractKnowledgeManifestation
	@Override
	public CLDialect<T> getDialect() {
		return dialect;
	}

    // TODO elevate this method implementation to AbstractKnowledgeManifestation
	@Override
	public Configuration<?> getConfiguration() {
		// check the cache
		if (configuration == null){
		// TODO create and cache
		}
		return configuration;
	}

    // TODO elevate this method implementation to AbstractKnowledgeManifestation
	@Override
	public String toString() {
  	  return this.getValue().toString();
	}

    // TODO elevate this method implementation to AbstractKnowledgeManifestation
	@Override
	public <S> KnowledgeEncoding<T, S> encode(EncodingSystem<T, S> system)
			throws EncodingSystemIncompatible {
		CLEncoding<T, S> encoding = 
				new CLEncoding<T, S>(system.code(value), system, dialect);
		return encoding;
	}
	
    // TODO elevate this method implementation to AbstractKnowledgeManifestation
	@Override
	public void clearEncode(EncodingSystem<T, ?> system) {
		mapEncoding.remove(system);		
	}

    // TODO elevate this method implementation to AbstractKnowledgeManifestation
	@Override
	public CLCommentExpression parse() {
		if (expression == null) {
			if (symbol == null) {
				// lazy parse
				this.expression = CLCommentExpression.lazyNewInstance(this);
			}
			else {
				if (comment.isEmpty()) {
				  this.expression = 
						  CLCommentExpression.eagerNewInstance(symbol);
				}
				else {
				  this.expression = 
						  CLCommentExpression.eagerNewInstance(
								  symbol, 
								  new Some<CLCommentExpression>( ((Some<CLCommentManifestation<T>>) 
										  comment).getValue().parse()) );
				}
			}
		}
		return expression;
	}


    // TODO elevate this method implementation to AbstractKnowledgeManifestation
	@Override
	public void clearParse() {
       expression = null;				
	}

    // TODO elevate this method implementation to AbstractKnowledgeManifestation
	@Override
	public void clear() {
		clearParse();
		mapEncoding.clear();
	}

}
