package cl2;

import krconfigured.BasicKnowledgeAssetConfigured;
import krconfigured.BasicKnowledgeManifestationConfigured;
import krconfigured.KnowledgeResourceConfiguredTemplate;
import krhashmap.li.BasicKnowledgeAssetLI;
import krhashmap.li.mse.BasicKnowledgeAssetLIMSE;
import api4kbj.KRRDialect;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import functional.None;
import functional.Option;

public class CLCommentExpression extends CLExpression implements CLComment {

	// Private Constructors
	// Component-based constructor
	private CLCommentExpression(KnowledgeResourceConfiguredTemplate template,
			String symbol, Option<CLCommentExpression> comment) {
		super(template, CL.LANG);
		this.symbol = symbol;
		this.comment = comment;
	}

	// Lazy lowering constructor
	private <T> CLCommentExpression(BasicKnowledgeAssetConfigured asset) {
		super(asset, CL.LANG);
	}

	// Lazy lifting constructor
	private CLCommentExpression(CLManifestation manifestation) {
		super(manifestation);
	}

	// private fields
	private String symbol;
	private Option<CLCommentExpression> comment;

	// Static Factory Methods
	public static CLCommentExpression eagerNewInstance(
			KnowledgeResourceConfiguredTemplate template, String symbol,
			Option<CLCommentExpression> comment) {
		// TODO do we want a static logger for this concrete class
		SLOG.debug("Starting eagerNewInstance");
		return new CLCommentExpression(template, symbol, comment);
	}

	public static CLCommentExpression eagerNewInstance(
			KnowledgeResourceConfiguredTemplate template, String symbol) {
		return eagerNewInstance(template, symbol,
				new None<CLCommentExpression>());
	}

	public static CLCommentExpression eagerNewInstance(
			KnowledgeResourceConfiguredTemplate template) {
		return eagerNewInstance(template, "");
	}

	// lazy lowering
	public static <T> CLCommentExpression lazyNewInstance(
			BasicKnowledgeAssetLI asset) {
		return new CLCommentExpression(asset);
	}

	// lazy lifting
	public static <T> CLCommentExpression lazyNewInstance(
			CLManifestation manifestation) {
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
			BasicKnowledgeManifestationConfigured manifest = mapManifest
					.values().iterator().next();
			// TODO extract symbol from manifest by building the DOM element
			Element value = manifest.build(CL.xcl2dom);
			NodeList symbolList = value.getElementsByTagName("symbol");
			NodeList symbolContent;
			if (symbolList.getLength() > 0) {
				Element symbol = (Element) value.getElementsByTagName("symbol")
						.item(0);
				symbolContent = symbol.getChildNodes();
			} else {
				symbolContent = value.getChildNodes();
			}
			// TODO implement proper output
			String symbolValue = symbolContent.toString();

			return symbolValue;
		}
		// B. if A fails, check the asset cache and apply a language
		// translation from the environment
		else {
			BasicKnowledgeAssetLIMSE asset = (BasicKnowledgeAssetLIMSE) mapAsset
					.values().iterator().next();
			return ((CLCommentExpression) asset.express(CL.LANG)).getSymbol();
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
			BasicKnowledgeManifestationConfigured manifest = mapManifest
					.values().iterator().next();
			LOG.debug("manifest: {}", manifest);
			// TODO move this to a helper function in XCL2.dom package
			Element value = manifest.build(CL.xcl2dom);
			NodeList commentList = value.getElementsByTagName("Comment");
			if (commentList.getLength() == 0) {
				return new None<CLCommentExpression>();
			} else {
				// TODO implement with lazy initialization
				return null;
			}
		}
		// B. if A fails, check the asset cache and apply a language
		// translation from the environment
		else {
			BasicKnowledgeAssetLIMSE asset = (BasicKnowledgeAssetLIMSE) mapAsset
					.values().iterator().next();
			return ((CLCommentExpression) asset.express(CL.LANG)).getComment();
		}
	}

	@Override
	protected BasicKnowledgeManifestationConfigured newManifestation(
			KRRDialect dialect) {
		// TODO Auto-generated method stub
		return null;
	}

}
