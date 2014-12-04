package cl2;

import api4kb.AbstractKnowledgeExpression;
import api4kb.ImmutableEnvironment;
import api4kb.DialectIncompatibleException;
import api4kb.EnvironmentIncompatibleException;
import api4kb.KRRDialect;
import api4kb.KRRLanguage;
import api4kb.KnowledgeAsset;
import api4kb.KnowledgeResource;
import api4kb.LanguageIncompatibleException;
import api4kb.Option;
import api4kb.None;
import api4kb.Some;
import api4kb.UnsupportedTranslationException;

import org.dom4j.dom.DOMElement;
import org.dom4j.dom.DOMText;

public class CLCommentExpression extends AbstractKnowledgeExpression implements
		CLComment, CLExpression {

	// Private Constructors
	// Component-based constructor
	private CLCommentExpression(String symbol,
			Option<CLCommentExpression> comment) {
		super(CL.lang);
		this.symbol = symbol;
		this.comment = comment;
	}

	// Lazy lowering constructor
	private <T> CLCommentExpression(CLCommentAsset asset)
			throws UnsupportedTranslationException {
		super(asset, CL.lang);
	}

	// Lazy lifting constructor
	private <T> CLCommentExpression(CLCommentManifestation<T> manifestation) {
		super(manifestation);
	}

	// private fields
	private String symbol;
	private Option<CLCommentExpression> comment;

	// Static Factory Methods
	public static CLCommentExpression eagerNewInstance(String symbol,
			Option<CLCommentExpression> comment) {
		LOG.debug("Symbol passed to constructor: {}", symbol);
		return new CLCommentExpression(symbol, comment);
	}

	public static CLCommentExpression eagerNewInstance(String symbol) {
		return eagerNewInstance(symbol, new None<CLCommentExpression>());
	}

	public static CLCommentExpression eagerNewInstance() {
		return eagerNewInstance("");
	}

	public static <T> CLCommentExpression lazyNewInstance(CLCommentAsset asset)
			throws UnsupportedTranslationException {
		return new CLCommentExpression(asset);
	}

	public static <T> CLCommentExpression lazyNewInstance(
			CLCommentManifestation<T> manifestation) {
		return new CLCommentExpression(manifestation) {
		};
	}

	// TODO Shift implementation to AbstractCLComment and incorporate by
	// composition
	@Override
	public String getSymbol() {
		// check the cache and evaluate if necessary
		if (symbol == null) {
			LOG.debug("Found no cached manifestation for symbol {}");
			symbol = evalSymbol();
		}
		LOG.debug("Symbol to be returned by getSymbol(): {}", symbol);
		return symbol;
	}

	private String evalSymbol() {
		// A. check the manifestation cache
		if (!mapManifest.isEmpty()) {
			return ((CLCommentManifestation<?>) mapManifest.values().iterator()
					.next()).getSymbol();
		}
		// B. if A fails, check the asset cache and apply a language
		// translation from the environment
		else {
			KnowledgeAsset asset = mapAsset.values().iterator().next();
			try {
				return ((CLCommentExpression) asset.express(CL.lang))
						.getSymbol();
			} catch (LanguageIncompatibleException e) {
				assert false : "Faulty lazy expression constructor";
				return null;
			}
		}
	}

	// TODO Shift implementation to AbstractCLComment and incorporate by
	// composition
	@Override
	public Option<CLCommentExpression> getComment() {
		// check the cache
		if (comment == null) {
			comment = evalComment();
		}
		return comment;
	}

	private Option<CLCommentExpression> evalComment() {
		// A. check the manifestation cache
		if (!mapManifest.isEmpty()) {
			CLCommentManifestation<?> manifest = (CLCommentManifestation<?>) mapManifest
					.values().iterator().next();
			Option<?> maybeComment = manifest.getComment();
			if (maybeComment.isEmpty()) {
				return new None<CLCommentExpression>();
			} else {
				// TODO cleanup - use FJ Option and map over parse()
				@SuppressWarnings("unchecked")
				CLCommentManifestation<?> commentM = ((Some<CLCommentManifestation<?>>) maybeComment)
						.getValue();
				CLCommentExpression commentE = (CLCommentExpression) commentM
						.parse();
				return new Some<CLCommentExpression>(commentE);
			}
		}
		// B. if A fails, check the asset cache and apply a language
		// translation from the environment
		else {
			KnowledgeAsset asset = mapAsset.values().iterator().next();
			try {
				return ((CLCommentExpression) asset.express(CL.lang))
						.getComment();
			} catch (LanguageIncompatibleException e) {
				assert false : "Faulty lazy expression constructor";
				return null;
			}
		}
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
			assert false : "Faulty Dialect Compatibility Check";
			return "Faulty Dialect Compatibility Check";
		}
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
	public <T> CLCommentManifestation<T> manifest(KRRDialect<T> dialect)
			throws DialectIncompatibleException {
		try {
			return (CLCommentManifestation<T>) super.manifest(dialect);
		} catch (DialectIncompatibleException e) {
			throw e;
		}
	}

	@Override
	protected <T> CLCommentManifestation<T> evalManifest(KRRDialect<T> dialect)
			throws DialectIncompatibleException {
		if (dialect.getLanguage() != CL.lang) {
			throw new DialectIncompatibleException();
		}
		if (dialect != CL.xcl2dom) {
			// TODO implement other CL dialects
			throw new DialectIncompatibleException();
		}
		// TODO this really belongs in the XCL2 package
		if ((symbol != null) && (comment != null)) {
			DOMElement element = new DOMElement("Comment", CL.NS_XCL2);
			if (!(comment.isEmpty())) {
				element.appendChild(((Some<CLCommentExpression>) comment)
						.getValue().manifest(CL.xcl2dom).getValue());
				DOMElement symbolElement = new DOMElement("symbol", CL.NS_XCL2);
				symbolElement.add(new DOMText(symbol));
				element.appendChild(symbolElement);
			} else {
				element.add(new DOMText(symbol));
			}
			// If this point is reached, then T is Element, and the factory
			// method will produce a result of the correct type already
			// so the case is only necessary for the compiler.
			@SuppressWarnings("unchecked")
			CLCommentManifestation<T> manifestation = (CLCommentManifestation<T>) CLCommentManifestation
					.getNewWrapperInstance(element, CL.xcl2dom);
			return manifestation;
		} else {
			assert false : "Call to evalManifest when uninitialized.";
		}
		assert false : "Should throw or return before reaching here";
		return null;
	}

	@Override
	protected KnowledgeAsset evalAsset(ImmutableEnvironment e)
			throws EnvironmentIncompatibleException {
		// TODO Auto-generated method stub
		return null;
	}

}
