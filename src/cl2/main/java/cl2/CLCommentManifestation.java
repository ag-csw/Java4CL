package cl2;

import org.dom4j.dom.DOMElement;

import api4kb.DialectIncompatibleException;
import api4kb.CodecSystem;
import api4kb.EncodingSystemIncompatibleException;
import api4kb.KnowledgeResource;
import api4kb.None;
import api4kb.Option;
import api4kb.Some;

public final class CLCommentManifestation<T> extends
		CLManifestation<T> implements CLComment {

	// Package-Private Constructors
	CLCommentManifestation(T value, CLDialect<T> dialect) {
		super(value, dialect);
	}

	// Component-based constructor
	CLCommentManifestation(String symbol,
			Option<CLCommentManifestation<T>> comment, CLDialect<T> dialect) {
		// TODO verify that comment is compatible with dialect
		super(dialect);
		this.symbol = symbol;
		this.comment = comment;
	}

	// Lazy lowering constructor
	private CLCommentManifestation(CLCommentExpression expression,
			CLDialect<T> dialect) throws DialectIncompatibleException {
		super(expression, dialect);
	}

	// Lazy lifting constructor
	private <S> CLCommentManifestation(CLEncoding<T, S> encoding) {
		// TODO verify that encoding is a Comment
		super(encoding);
	}

	// private fields
	private String symbol;
	private Option<CLCommentManifestation<T>> comment;

	// Static Factory Methods
	public static <T> CLCommentManifestation<T> getNewWrapperInstance(T value,
			CLDialect<T> dialect) {
		return new CLCommentManifestation<T>(value, dialect);
	}

	public static <T> CLCommentManifestation<T> getNewComponentInstance(
			String symbol, Option<CLCommentManifestation<T>> comment,
			CLDialect<T> dialect) {
		return new CLCommentManifestation<T>(symbol, comment, dialect);
	}

	public static <T> CLCommentManifestation<T> getNewComponentInstance(
			String symbol, CLDialect<T> dialect) {
		return getNewComponentInstance(symbol,
				new None<CLCommentManifestation<T>>(), dialect);
	}

	public static <T> CLCommentManifestation<T> getNewComponentInstance(
			CLDialect<T> dialect) {
		return getNewComponentInstance("", dialect);
	}

	public static <T> CLCommentManifestation<T> lazyNewInstance(
			CLCommentExpression expression, CLDialect<T> dialect)
			throws DialectIncompatibleException {
		return new CLCommentManifestation<T>(expression, dialect);
	}

	public static <T, S> CLCommentManifestation<T> lazyNewInstance(
			CLEncoding<T, S> encoding) {
		return new CLCommentManifestation<T>(encoding);
	}

	// TODO Shift implementation to AbstractCLComment and incorporate by
	// composition
	@Override
	public String getSymbol() {
		// check the cache and evaluate if necessary
		if (symbol == null) {
			symbol = evalSymbol();
		}
		return symbol;
	}

	private String evalSymbol() {
		// TODO parse value using methods appropriate to the format
		return null;
	}

	// TODO Shift implementation to AbstractCLComment and incorporate by
	// composition
	@Override
	public Option<CLCommentManifestation<T>> getComment() {
		// check the cache and evaluate if necessary
		if (comment == null) {
			comment = evalComment();
		}
		return comment;
	}

	private Option<CLCommentManifestation<T>> evalComment() {
		// default value
		if (symbol == null)
			symbol = "";
		comment = new None<CLCommentManifestation<T>>();
		if (dialect != CL.xcl2dom) {
			// TODO implement other CL dialects
		} else {
			DOMElement element = (DOMElement) value;
			symbol = element.getText();
			for (Object i : element.elements()) {
				DOMElement child = (DOMElement) i;
				// TODO write isSymbolEdge method
				if (child.getName().equals("symbol")) {
					symbol = child.getText();
				}
				// TODO write isComment method
				if (child.getName().equals("Comment")) {
					comment = new Some<CLCommentManifestation<T>>(getNewWrapperInstance((T) child, (CLDialect<T>) dialect));
				}

			}
		}
		return comment;
	}

	@Override
	public CLDialect<T> getDialect() {
		return (CLDialect<T>) dialect;
	}

	@Override
	public String toString() {
		// TODO incorporate the dialect in a message header
		return getValue().toString();
	}

	// TODO Shift implementation to AbstractCLComment and incorporate by
	// composition
	// determines if a KnowledgeResource is equal to this one
	public Boolean equals(KnowledgeResource that) {
		return this.toString().equals(that);
	}

	// TODO Shift implementation to AbstractCLComment and incorporate by
	// composition
	// overriding hashCode to agree with equal
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	protected <S> CLEncoding<T, S> evalEncoding(
			CodecSystem<T, S> system)
			throws EncodingSystemIncompatibleException {
		// TODO implement eager lowering to encoding
		return null;
	}

	@Override
	protected CLCommentExpression evalExpression() {
		// TODO implement eager lifting to expression
		return null;
	}

}
