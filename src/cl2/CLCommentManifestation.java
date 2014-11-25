package cl2;

import java.util.HashMap;

import api4kb.Configuration;
import api4kb.EncodingSystem;
import api4kb.EncodingSystemIncompatible;
import api4kb.KnowledgeEncoding;
import api4kb.None;
import api4kb.Option;
import api4kb.Some;

public class CLCommentManifestation<T> implements CLComment, CLManifestation<T> {

	// Package-Private Constructors
	// Wrapper-based constructor
	 CLCommentManifestation(T value, CLDialect<T> dialect) {
		this.value = value;
		this.dialect = dialect;
        mapEncoding = new HashMap< EncodingSystem<T, ?>, KnowledgeEncoding<T, ?>>();
	}
	
	 // Component-based constructor
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
	 CLCommentManifestation(CLCommentExpression expression, CLDialect<T> dialect) {
		this.expression = expression;
		this.dialect = dialect;
       mapEncoding = new HashMap< EncodingSystem<T, ?>, KnowledgeEncoding<T, ?>>();
	}

	// TODO Lazy lifting constructor - argument is an Encoding

	// private fields
	private T value;
	private String symbol;
	private Option<CLCommentManifestation<T>> comment;
	private CLDialect<T> dialect;
	private Configuration<?> configuration;
	private final HashMap< EncodingSystem<T, ?>, KnowledgeEncoding<T, ?>> mapEncoding;
	private CLCommentExpression expression;

	// TODO Static Factory Methods
	public static <T> CLCommentManifestation<?> eagerNewInstance(
			String symbol, 
			Option<CLCommentManifestation<T>> comment,
			CLDialect<T> dialect
			){
		return new CLCommentManifestation<T>(symbol, comment, dialect);
	}
	
	public static <T> CLCommentManifestation<T> eagerNewInstance(
			String symbol, 
			CLDialect<T> dialect
			){
		return new CLCommentManifestation<T>(symbol, new None<CLCommentManifestation<T>>(), dialect);
	}
	
	@Override
	public T getValue() {
		return value;
	}


	@Override
	public String getSymbol() {
		// check the cache
		if (symbol == null){
			// TODO parse using methods appropriate to the format
			// and cache			
		}
		return symbol;
	}

	@Override
	public Option<CLCommentManifestation<T>> getComment() {
		// check the cache
		if (comment == null){
			// TODO parse using methods appropriate to the format
			// and cache			
		}
		return comment;
	}


	@Override
	public CLDialect<T> getDialect() {
		return dialect;
	}

	@Override
	public Configuration<?> getConfiguration() {
		// check the cache
		if (configuration == null){
		// TODO create and cache
		}
		return configuration;
	}

	@Override
	public String toString() {
  	  return this.getValue().toString();
	}

	@Override
	public <S> KnowledgeEncoding<T, S> encode(EncodingSystem<T, S> system)
			throws EncodingSystemIncompatible {
		CLEncoding<T, S> encoding = 
				new CLEncoding<T, S>(system.code(value), system, dialect);
		return encoding;
	}
	
	@Override
	public void clearEncode(EncodingSystem<T, ?> system) {
		mapEncoding.remove(system);		
	}

	@Override
	public CLCommentExpression parse() {
		if (expression == null) {
			if (symbol == null) {
				// lazy parse
				this.expression = new CLCommentExpression(this);
			}
			else {
				if (comment.isEmpty()) {
				  this.expression = CLKnowledgeResources.eagerCLCommentExpression(symbol);
				}
				else {
				  this.expression = 
						  CLKnowledgeResources.eagerCLCommentExpression(symbol, new Some<CLCommentExpression>( ((Some<CLCommentManifestation<T>>) comment).getValue().parse()) );
				}
			}
		}
		return expression;
	}


	@Override
	public void clearParse() {
       expression = null;				
	}

	@Override
	public void clear() {
		clearParse();
		mapEncoding.clear();
	}

}
