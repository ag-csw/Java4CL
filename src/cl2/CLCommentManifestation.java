package cl2;

import java.util.HashMap;

import api4kb.Configuration;
import api4kb.DialectIncompatibleException;
import api4kb.EncodingSystem;
import api4kb.EncodingSystemIncompatible;
import api4kb.KnowledgeEncoding;
import api4kb.Option;
import api4kb.Some;

public class CLCommentManifestation<T> implements CLComment, CLManifestation<T> {

	public CLCommentManifestation(T value, CLDialect<T> dialect) {
		this.value = value;
		this.dialect = dialect;
	}
	
	public CLCommentManifestation(String symbol, CLDialect<T> dialect) {
		this.symbol = symbol;
		this.dialect = dialect;
		//TODO For each dialect construct the T value
		if (dialect == CL.clif) {
			
		}
	}

	private T value;
	private String symbol;
	private Option<CLCommentManifestation<T>> comment;
	private CLDialect<T> dialect;
	private Configuration<?> configuration;
	private HashMap< EncodingSystem<T, ?>, KnowledgeEncoding<T, ?>> mapEncoding;
	private CLCommentExpression expression;

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
				CLCommentExpression expression = new CLCommentExpression(this);
			}
			else {
				if (comment.isEmpty()) {
				  CLCommentExpression expression = new CLCommentExpression(symbol);
				}
				else {
				  CLCommentExpression expression = 
				    new CLCommentExpression(symbol, new Some<CLCommentExpression>( ((Some<CLCommentManifestation<T>>) comment).getValue().parse()) );
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
