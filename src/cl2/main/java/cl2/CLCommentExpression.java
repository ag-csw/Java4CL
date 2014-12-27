package cl2;

import krhashmap.AbstractBasicKnowledgeManifestationG;
import krhashmap.KnowledgeAssetLI;
import api4kbj.AbstractKRRDialectType;
import org.dom4j.dom.DOMElement;
import org.dom4j.dom.DOMText;
import org.w3c.dom.Element;

import functional.None;
import functional.Option;
import functional.Some;
import graphenvironment.GraphImmutableEnvironment;

public class CLCommentExpression extends CLExpression implements CLComment {

	// Private Constructors
	// Component-based constructor
	private CLCommentExpression(String symbol,
			Option<CLCommentExpression> comment) {
		super(CL.lang);
		this.symbol = symbol;
		this.comment = comment;
	}

	// Lazy lowering constructor
	private <T> CLCommentExpression(KnowledgeAssetLI asset) {
		super(asset, CL.lang);
	}

	// Lazy lifting constructor
	private <T> CLCommentExpression(CLCommentManifestationG<T> manifestation) {
		super(manifestation);
	}

	// private fields
	private String symbol;
	private Option<CLCommentExpression> comment;

	// Static Factory Methods
	public static CLCommentExpression eagerNewInstance(String symbol,
			Option<CLCommentExpression> comment) {
		// TODO do we want a static logger for this concrete class
		SLOG.debug("Starting eagerNewInstance");
		return new CLCommentExpression(symbol, comment);
	}

	public static CLCommentExpression eagerNewInstance(String symbol) {
		return eagerNewInstance(symbol, new None<CLCommentExpression>());
	}

	public static CLCommentExpression eagerNewInstance() {
		return eagerNewInstance("");
	}

	// lazy lowering
	public static <T> CLCommentExpression lazyNewInstance(KnowledgeAssetLI asset) {
		return new CLCommentExpression(asset);
	}

	// lazy lifting
	public static <T> CLCommentExpression lazyNewInstance(
			CLCommentManifestationG<T> manifestation) {
		return new CLCommentExpression(manifestation) {
		};
	}

	// TODO Shift implementation to AbstractCLComment and incorporate by
	// composition
	@Override
	public String getSymbol() {
		// check the cache and evaluate if necessary
		LOG.debug("Symbol cache: {}", symbol);
		if (symbol == null) {
			LOG.debug("Found no cached manifestation for symbol {}");
			symbol = evalSymbol();
		}
		return symbol;
	}

	private String evalSymbol() {
		// A. check the manifestation cache
		LOG.debug("Manifest cache: {}", mapManifest);
		if (!mapManifest.isEmpty()) {
			return ((CLCommentManifestationG<?>) mapManifest.values()
					.iterator().next()).getSymbol();
		}
		// B. if A fails, check the asset cache and apply a language
		// translation from the environment
		else {
			KnowledgeAssetLI asset = mapAsset.values().iterator().next();
			return ((CLCommentExpression) asset.express(CL.lang)).getSymbol();
		}
	}

	// TODO Shift implementation to AbstractCLComment and incorporate by
	// composition
	@Override
	public Option<CLCommentExpression> getComment() {
		// check the cache
		LOG.debug("Comment cache: {}", comment);
		if (comment == null) {
			comment = evalComment();
		}
		return comment;
	}

	private Option<CLCommentExpression> evalComment() {
		// A. check the manifestation cache
		LOG.debug("Manifest cache: {}", mapManifest);
		if (!(mapManifest.isEmpty())) {
			CLCommentManifestationG<?> manifest = (CLCommentManifestationG<?>) mapManifest
					.values().iterator().next();
			LOG.debug("manifest: {}", manifest);
			Option<?> maybeComment = manifest.getComment();
			LOG.debug("maybeComment: {}", maybeComment);
			if (maybeComment.isEmpty()) {
				return new None<CLCommentExpression>();
			} else {
				// TODO cleanup - use FJ Option and map over parse()
				@SuppressWarnings("unchecked")
				CLCommentManifestationG<?> commentM = ((Some<CLCommentManifestationG<?>>) maybeComment)
						.value();
				CLCommentExpression commentE = (CLCommentExpression) commentM
						.parse();
				return new Some<CLCommentExpression>(commentE);
			}
		}
		// B. if A fails, check the asset cache and apply a language
		// translation from the environment
		else {
			KnowledgeAssetLI asset = mapAsset.values().iterator().next();
			return ((CLCommentExpression) asset.express(CL.lang)).getComment();
		}
	}

	@Override
	public CLCommentManifestationG<Element> manifest() {
		LOG.debug("Starting default manifest of expression");
		return (CLCommentManifestationG<Element>) manifest(CL.lang
				.defaultDialectType());
	}

	@Override
	public <T> CLCommentManifestationG<T> manifest(
			AbstractKRRDialectType<T> dialectType) {
		AbstractBasicKnowledgeManifestationG<T> manifestation = super
				.manifest(dialectType);
		LOG.debug("Starting manifest of expression");
		if (manifestation instanceof CLCommentManifestationG<?>) {
			return (CLCommentManifestationG<T>) manifestation;
		}
		// last resort create a new manifestation with lazy lowering
		// this means discarding the manifestation created in the superclass
		return CLCommentManifestationG.lazyNewInstance(this,
				(CLDialectType<T>) dialectType);
	}

	// TODO this needs to be implemented through other methods
	protected <T> CLCommentManifestationG<T> evalManifest(
			AbstractKRRDialectType<T> dialect) {
		if (dialect.language() != CL.lang) {
			throw new IllegalArgumentException(
					"Requested dialect is not a CL dialect");
		}
		if (dialect != CL.xcl2dom) {
			// TODO implement other CL dialects
			throw new IllegalArgumentException(
					"This CL dialect is not yet supported.");
		}
		// TODO this really belongs in the XCL2 package
		LOG.debug("Symbol cache: {}", symbol);
		LOG.debug("comment cache: {}", comment);
		if ((symbol != null) && (comment != null)) {
			DOMElement element = new DOMElement("Comment", CL.NS_XCL2);
			if (!(comment.isEmpty())) {
				element.appendChild(((Some<CLCommentExpression>) comment)
						.value().manifest(CL.xcl2dom).value());
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
			CLCommentManifestationG<T> manifestation = (CLCommentManifestationG<T>) CLCommentManifestationG
					.getNewWrapperInstance(element, CL.xcl2dom);
			return manifestation;
		} else {
			assert false : "Call to evalManifest when uninitialized.";
		}
		assert false : "Should throw or return before reaching here";
		return null;
	}

	@Override
	public KnowledgeAssetLI conceptualize(GraphImmutableEnvironment e) {
		return super.conceptualize(e);
	}

	@Override
	protected <T> AbstractBasicKnowledgeManifestationG<T> newManifestation(
			AbstractKRRDialectType<T> dialectType) {
		// TODO Auto-generated method stub
		return null;
	}

}
