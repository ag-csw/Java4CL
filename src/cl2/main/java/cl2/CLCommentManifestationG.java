package cl2;

import org.dom4j.dom.DOMElement;
import org.dom4j.dom.DOMText;
import org.w3c.dom.Node;

import api4kbj.AbstractCodecSystem;
import api4kbj.AbstractKnowledgeEncoding;
import api4kbj.AbstractKnowledgeExpression;
import api4kbj.KnowledgeSourceLevel;
import api4kbj.None;
import api4kbj.Option;
import api4kbj.Some;

public final class CLCommentManifestationG<T> extends CLManifestationG<T>
		implements CLComment {

	// Package-Private Constructors
	CLCommentManifestationG(T value, CLDialectType<T> dialectType) {
		super(value, dialectType);
	}

	// Component-based constructor
	CLCommentManifestationG(String symbol,
			Option<CLCommentManifestationG<T>> comment,
			CLDialectType<T> dialectType) {
		// TODO verify that comment is compatible with dialect
		super(dialectType);
		this.symbol = symbol;
		this.comment = comment;
	}

	// Lazy lowering constructor
	private CLCommentManifestationG(CLCommentExpression expression,
			CLDialectType<T> dialect) {
		super(expression, dialect);
	}

	// Lazy lifting constructor
	private <S> CLCommentManifestationG(CLEncoding<T, S> encoding) {
		// TODO verify that encoding is a Comment
		super(encoding);
	}

	// private fields
	private String symbol;
	private Option<CLCommentManifestationG<T>> comment;

	// Static Factory Methods
	public static <T> CLCommentManifestationG<T> getNewWrapperInstance(T value,
			CLDialectType<T> dialect) {
		return new CLCommentManifestationG<T>(value, dialect);
	}

	public static <T> CLCommentManifestationG<T> getNewComponentInstance(
			String symbol, Option<CLCommentManifestationG<T>> comment,
			CLDialectType<T> dialect) {
		return new CLCommentManifestationG<T>(symbol, comment, dialect);
	}

	public static <T> CLCommentManifestationG<T> getNewComponentInstance(
			String symbol, CLDialectType<T> dialect) {
		return getNewComponentInstance(symbol,
				new None<CLCommentManifestationG<T>>(), dialect);
	}

	public static <T> CLCommentManifestationG<T> getNewComponentInstance(
			CLDialectType<T> dialect) {
		return getNewComponentInstance("", dialect);
	}

	public static <T> CLCommentManifestationG<T> lazyNewInstance(
			CLCommentExpression expression, CLDialectType<T> dialect) {
		return new CLCommentManifestationG<T>(expression, dialect);
	}

	public static <T, S> CLCommentManifestationG<T> lazyNewInstance(
			CLEncoding<T, S> encoding) {
		return new CLCommentManifestationG<T>(encoding);
	}

	// TODO Shift implementation to AbstractCLComment and incorporate by
	// composition
	@Override
	public String getSymbol() {
		// check the symbol cache directly
		if (symbol != null) {
			LOG.debug("Symbol found in cache : {}", symbol);
			return symbol;
		}
		// try for the initial value next, if it is an expression
		if ((initialValue != null)
				&& (initialValue.getLevel() == KnowledgeSourceLevel.EXPRESSION)) {
			// FIXME - symbol should be a node list for manifestation
			symbol = ((CLCommentExpression) initialValue).getSymbol();
			LOG.debug("Symbol obtained from initial value : {}", symbol);
			return symbol;
		}
		// try for the expression value next
		if (expression != null) {
			// FIXME - symbol should be a node list for manifestation
			symbol = ((CLCommentExpression) expression).getSymbol();
			LOG.debug("Symbol obtained from cached expression : {}", symbol);
			return symbol;
		}
		return evalSymbol();
	}

	private String evalSymbol() {
		// TODO parse value using methods appropriate to the format
		// also cache the comment at this time
		// FIXME this is a default implementation only
		symbol = "";
		comment = new None<CLCommentManifestationG<T>>();
		return symbol;
	}

	// TODO Shift implementation to AbstractCLComment and incorporate by
	// composition
	@Override
	public Option<CLCommentManifestationG<T>> getComment() {
		// check the comment cache directly
		if (comment != null) {
			LOG.debug("comment found in cache : {}", comment);
			return comment;
		}
		// try for the initial value next, if it is an expression
		if ((initialValue != null)
				&& (initialValue.getLevel() == KnowledgeSourceLevel.EXPRESSION)) {
			Option<CLCommentExpression> exprcomment = ((CLCommentExpression) initialValue)
					.getComment();
			if (exprcomment.isEmpty()) {
				comment = new None<CLCommentManifestationG<T>>();
				LOG.debug("comment obtained from initial value : {}", comment);
				return comment;
			}
			CLCommentExpression exprcommentval = ((Some<CLCommentExpression>) exprcomment)
					.getValue();

			CLCommentManifestationG<T> commentval;
			commentval = exprcommentval.manifest(this.getDialectType());
			comment = new Some<CLCommentManifestationG<T>>(commentval);
			return comment;
		}
		return evalComment();
	}

	private Option<CLCommentManifestationG<T>> evalComment() {
		// FIXME default value
		if (comment == null) {
			symbol = "";
			comment = new None<CLCommentManifestationG<T>>();
		}
		return comment;
		/**
		 * if (dialectType != CL.xcl2dom) { // TODO implement other CL dialects
		 * } else { DOMElement element = (DOMElement) value; symbol =
		 * element.getText(); for (Object i : element.elements()) { DOMElement
		 * child = (DOMElement) i; // TODO write isSymbolEdge method if
		 * (child.getName().equals("symbol")) { symbol = child.getText(); } //
		 * TODO write isComment method if (child.getName().equals("Comment")) {
		 * comment = new
		 * Some<CLCommentManifestationG<T>>(getNewWrapperInstance((T) child,
		 * (CLDialectType<T>) dialectType)); }
		 * 
		 * } } return comment;
		 **/
	}

	@Override
	public CLDialectType<T> getDialectType() {
		return (CLDialectType<T>) dialectType;
	}

	@Override
	public Class<T> getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected T eval() {
		if (dialectType != CL.xcl2dom) {
			// TODO implement other CL dialects
			throw new IllegalArgumentException("The requested CL dialect type "
					+ dialectType.toString() + " is not supported yet.");
		}
		// TODO this really belongs in the XCL2 package
		// TODO need to integrate with evalManifest method of
		// CLCommentExpression
		// The method here should be fundamental.
		LOG.debug("Symbol cache: {}", symbol);
		LOG.debug("comment cache: {}", comment);
		if ((symbol != null) && (comment != null)) {
			DOMElement element = new DOMElement("Comment", CL.NS_XCL2);
			if (!(comment.isEmpty())) {
				element.appendChild((Node) ((Some<CLCommentManifestationG<T>>) comment)
						.getValue().getValue());
				DOMElement symbolElement = new DOMElement("symbol", CL.NS_XCL2);
				symbolElement.add(new DOMText(symbol));
				element.appendChild(symbolElement);
			} else {
				element.add(new DOMText(symbol));
			}
			return (T) element;
		} else {
			assert false : "Call to eval when components are uninitialized.";
			return null;
		}
	}

	@Override
	public CLEncoding<T, byte[]> encode() {
		LOG.debug("Starting default manifest of expression");
		return (CLEncoding<T, byte[]>) encode(dialectType.defaultSystem());
	}

	@Override
	public <S> CLEncoding<T, S> encode(AbstractCodecSystem<T, S> system) {
		AbstractKnowledgeEncoding<T, S> encoding = super.encode(system);
		LOG.debug("Starting manifest of expression");
		if (encoding instanceof CLEncoding<?, ?>) {
			return (CLEncoding<T, S>) encoding;
		}
		// last resort create a new manifestation with lazy lowering
		// this means discarding the manifestation created in the superclass
		return CLEncoding.lazyNewInstance(this, system);
	}

	@Override
	protected <S> AbstractKnowledgeEncoding<T, S> newEncoding(
			AbstractCodecSystem<T, S> system) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractKnowledgeExpression newExpression() {
		// TODO Auto-generated method stub
		return null;
	}

}
