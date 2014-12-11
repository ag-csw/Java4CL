package api4kb;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKnowledgeExpression implements
		KnowledgeExpression {

	public <T> AbstractKnowledgeExpression(KRRLanguage lang) {
		LOG.debug("Starting expression constructor for language: {}", lang);
		this.lang = lang;
		mapManifest = new HashMap<KRRDialectType<?>, AbstractKnowledgeManifestation<?>>();
		mapAsset = new HashMap<ImmutableEnvironment, AbstractKnowledgeAsset>();
	}

	// Lazy lifting constructor - argument is a Manifestation
	public <T> AbstractKnowledgeExpression(
			AbstractKnowledgeManifestation<T> manifestation) {
		LOG.debug("Starting lazy lifting expression construtor with manifestation: {}", manifestation);
		mapManifest = new HashMap<KRRDialectType<?>, AbstractKnowledgeManifestation<?>>();
		manifestSafePut(manifestation.getDialect(), manifestation);
		lang = manifestation.getDialect().getLanguage();
		mapAsset = new HashMap<ImmutableEnvironment, AbstractKnowledgeAsset>();
	}

	// Lazy lowering constructor - argument is an Asset
	public AbstractKnowledgeExpression(AbstractKnowledgeAsset asset, KRRLanguage lang)
			throws UnsupportedTranslationException {
		LOG.debug("Starting lazy lowering expression construtor with asset: {}", asset);
		LOG.debug("Starting expression construtor for language: {}", lang);
		this.lang = lang;
		mapManifest = new HashMap<KRRDialectType<?>, AbstractKnowledgeManifestation<?>>();
		mapAsset = new HashMap<ImmutableEnvironment, AbstractKnowledgeAsset>();
		mapAsset.put(asset.getEnvironment(), asset);
	}

	protected final HashMap<KRRDialectType<?>, AbstractKnowledgeManifestation<?>> mapManifest;
	protected final HashMap<ImmutableEnvironment, AbstractKnowledgeAsset> mapAsset;
	protected final KRRLanguage lang;
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

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
	public void clearManifest(KRRDialectType<?> dialect) {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapManifest.remove(dialect);
	}

	// clear memoization cache of the conceptualize method for the particular environment
	public void clearConceptualize(ImmutableEnvironment environment) {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapAsset.remove(environment);
	}

	@Override
	public KRRLanguage getLanguage() {
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
		return manifest(lang.defaultDialect());
	}
	
	// lowering method accepts a parameter indicating the dialect
	// with generic T for the format (e.g. String, XML Element)
	public <T> AbstractKnowledgeManifestation<T> manifest(KRRDialectType<T> dialect)
			throws DialectTypeIncompatibleException {
		LOG.debug("Starting evaluation of the manifest of expression");
		LOG.debug("  Dialect of the manifestation: {}", dialect);
		LOG.debug("  Language of the expression: {}", lang);
		if (dialect.getLanguage() != lang){
			throw new DialectTypeIncompatibleException();
		}
		LOG.debug("Manifestation cache: {}", mapManifest);
		if (!mapManifest.containsKey(dialect)) {
			LOG.debug("Found no cached manifestation for: {}", dialect);
			AbstractKnowledgeManifestation<T> manifest = evalManifest(dialect);
			manifestSafePut(dialect, manifest);
			return manifest;
		} else {
			// type compatibility is checked before caching
			// so that the type case is safe
			@SuppressWarnings("unchecked")
			AbstractKnowledgeManifestation<T> manifest = (AbstractKnowledgeManifestation<T>) mapManifest.get(dialect);
			return manifest;
		}
	}

	// nonpublic helper method
	protected abstract <T> AbstractKnowledgeManifestation<T> evalManifest(
			KRRDialectType<T> dialect) throws DialectTypeIncompatibleException;

	// lifting method
   public KnowledgeAsset conceptualize(ImmutableEnvironment e)
			throws EnvironmentIncompatibleException {
		if (!mapAsset.containsKey(e)) {
			KnowledgeAsset asset = evalAsset(e);
			return asset;
		} else {
			KnowledgeAsset asset = mapAsset.get(e);
			return asset;
		}

	}

	// nonpublic helper method
	protected abstract KnowledgeAsset evalAsset(ImmutableEnvironment e)
			throws EnvironmentIncompatibleException;
	
	<T> void manifestSafePut(KRRDialectType<T> dialect, AbstractKnowledgeManifestation<T> manifest) {
		mapManifest.put(dialect, manifest);
	}
	

}
