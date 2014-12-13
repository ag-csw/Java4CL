package api4kb;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKnowledgeExpression extends AbstractKnowledgeResource implements
		KnowledgeExpression {

	// initializing only constructor
	protected <T> AbstractKnowledgeExpression(AbstractKRRLanguage lang) {
		LOG.debug("Starting initializing constructor for language: {}", lang);
		this.lang = lang;
	}

	// Lazy lifting constructor - argument is a Manifestation
	public <T> AbstractKnowledgeExpression(
			AbstractKnowledgeManifestation<T> manifestation) {
		this(manifestation.getDialectType().getLanguage());
		LOG.debug("Starting lazy lifting expression construtor with manifestation: {}", manifestation);
		manifestSafePut(manifestation);
	}

	// Lazy lowering constructor - argument is an Asset
	public AbstractKnowledgeExpression(KnowledgeAssetLI asset, AbstractKRRLanguage lang)
			throws UnsupportedTranslationException {
		this(lang);
		LOG.debug("Starting lazy lowering expression construtor with asset: {}", asset);
		LOG.debug("Starting expression construtor for language: {}", lang);
		mapAsset.put(asset.getEnvironment(), asset);
	}

	protected final HashMap<KRRDialectType<?>, AbstractKnowledgeManifestation<?>> mapManifest = new HashMap<KRRDialectType<?>, AbstractKnowledgeManifestation<?>>();
	protected final HashMap<ImmutableEnvironment, KnowledgeAssetLI> mapAsset = new HashMap<ImmutableEnvironment, KnowledgeAssetLI>();
	protected final AbstractKRRLanguage lang;
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public KnowledgeSourceLevel getLevel() {
		return level;
	}

	@Override
	public void clear() {
		clearManifest();
		clearAsset();
	}

	public void clearManifest() {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapManifest.clear();
	}

	public void clearAsset() {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapAsset.clear();
	}

	// clear memoization cache of the manifest method for the particular dialect
	public void clearManifest(KRRDialectType<?> dialectType) {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapManifest.remove(dialectType);
	}

	// clear memoization cache of the conceptualize method for the particular environment
	public void clearConceptualize(ImmutableEnvironment environment) {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapAsset.remove(environment);
	}

	@Override
	public AbstractKRRLanguage getLanguage() {
		return lang;
	}

	// provides a canonical String serialization of the Expression based on a
	// preferred Manifestation type
	// should give the same output as chaining the manifest method (called on the
	// preferred dialect and the default configuration) with the 
	// toString method of the manifestation.
	@Override
	public String toString(){
	  try {
		return manifest().toString();
	} catch (DialectTypeIncompatibleException e) {
		return toString();
	}	
	}

	// default lowering method returns a manifestation in the default dialect
	// for that language
	public AbstractKnowledgeManifestation<?> manifest() throws DialectTypeIncompatibleException{
		return manifest(lang.defaultDialectType());
	}
	
	// lowering method accepts a parameter indicating the dialect
	// with generic T for the format (e.g. String, XML Element)
	public <T> AbstractKnowledgeManifestation<T> manifest(KRRDialectType<T> dialectType)
			throws DialectTypeIncompatibleException {
		LOG.debug("Starting evaluation of the manifest of expression");
		LOG.debug("  Dialect of the manifestation: {}", dialectType);
		LOG.debug("  Language of the expression: {}", lang);
		if (dialectType.getLanguage() != lang){
			throw new DialectTypeIncompatibleException();
		}
		LOG.debug("Manifestation cache: {}", mapManifest);
		if (!mapManifest.containsKey(dialectType)) {
			LOG.debug("Found no cached manifestation for: {}", dialectType);
			AbstractKnowledgeManifestation<T> manifest = evalManifest(dialectType);
			manifestSafePut(manifest);
			return manifest;
		} else {
			// type compatibility is checked before caching
			// so that the type case is safe
			@SuppressWarnings("unchecked")
			AbstractKnowledgeManifestation<T> manifest = (AbstractKnowledgeManifestation<T>) mapManifest.get(dialectType);
			return manifest;
		}
	}

	// nonpublic helper method
	protected abstract <T> AbstractKnowledgeManifestation<T> evalManifest(
			KRRDialectType<T> dialectType) throws DialectTypeIncompatibleException;

	// lifting method
   public KnowledgeAssetLI conceptualize(GraphImmutableEnvironment e)
			throws EnvironmentIncompatibleException {
	   LOG.debug("Starting conceptualization relative to environment : {}", e);
		if (!mapAsset.containsKey(e)) {
			LOG.debug("No asset is cached for this environment. Evaluating...");
			KnowledgeAssetLI asset = evalAsset(e);
			LOG.debug("Asset evaluated: {}", asset);
			return asset;
		} else {
			KnowledgeAssetLI asset = mapAsset.get(e);
			LOG.debug("Asset obtained from cache: {}", asset);
			return asset;
		}

	}

	// nonpublic helper method
	protected KnowledgeAssetLI evalAsset(GraphImmutableEnvironment e)
			throws EnvironmentIncompatibleException{
		KnowledgeAssetLI asset = new KnowledgeAssetLI(this, e){};
		return asset;
	}
	
	<T> void manifestSafePut(AbstractKnowledgeManifestation<T> manifest) {
		mapManifest.put(manifest.getDialectType(), manifest);
	}
	
	// verify that some other equivalent property has been set
	// before forgetting initial value, to avoid leaving object
	// in inconsistent "state".
	@Override
	public void clearInitialValue() {
		if ((!mapManifest.isEmpty()) | (!mapAsset.isEmpty())) {
			super.unsafeClearInitialValue();
		}
	}


}
