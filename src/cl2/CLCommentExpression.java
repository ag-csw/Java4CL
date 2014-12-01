package cl2;

import api4kb.AbstractKnowledgeExpression;
import api4kb.ImmutableEnvironment;
import api4kb.DialectIncompatibleException;
import api4kb.IncompatibleEnvironmentException;
import api4kb.KRRDialect;
import api4kb.KRRLanguage;
import api4kb.KnowledgeAsset;
import api4kb.KnowledgeManifestation;
import api4kb.KnowledgeResource;
import api4kb.LanguageIncompatibleException;
import api4kb.Option;
import api4kb.None;
import api4kb.Some;
import api4kb.UnsupportedTranslationException;

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

	//TODO Shift implementation to AbstractCLComment and incorporate by composition
	@Override
	public String getSymbol() {
		// check the cache and evaluate if necessary
		if (symbol == null) {
			symbol = evalSymbol();
		}
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

	//TODO Shift implementation to AbstractCLComment and incorporate by composition
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
			return e.getMessage();
		}
	}

	//TODO Shift implementation to AbstractCLComment and incorporate by composition
	// determines if a KnowledgeResource is equal to this one
	public Boolean equals(KnowledgeResource that) {
		return this.toString().equals(that);
	}

	//TODO Shift implementation to AbstractCLComment and incorporate by composition
	// overriding hashCode to agree with equal
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}


	@Override
	protected <T> KnowledgeManifestation<T> evalManifest(KRRDialect<T> dialect)
			throws DialectIncompatibleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected KnowledgeAsset evalAsset(ImmutableEnvironment e)
			throws IncompatibleEnvironmentException {
		// TODO Auto-generated method stub
		return null;
	}

}
