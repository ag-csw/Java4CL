package api4kb;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKnowledgeExpression implements
		KnowledgeExpression {

	public <T> AbstractKnowledgeExpression(KRRLanguage lang) {
		LOG.debug("Starting expression constructor for language: {}", lang);
		this.lang = lang;
		mapManifest = new HashMap<KRRDialect<?>, KnowledgeManifestation<?>>();
		mapAsset = new HashMap<ImmutableEnvironment, KnowledgeAsset>();
	}

	// Lazy lifting constructor - argument is a Manifestation
	public <T> AbstractKnowledgeExpression(
			KnowledgeManifestation<T> manifestation) {
		LOG.debug("Starting lazy lifting expression construtor with manifestation: {}", manifestation);
		mapManifest = new HashMap<KRRDialect<?>, KnowledgeManifestation<?>>();
		manifestSafePut(manifestation.getDialect(), manifestation);
		lang = manifestation.getDialect().getLanguage();
		mapAsset = new HashMap<ImmutableEnvironment, KnowledgeAsset>();
	}

	// Lazy lowering constructor - argument is an Asset
	public AbstractKnowledgeExpression(KnowledgeAsset asset, KRRLanguage lang)
			throws UnsupportedTranslationException {
		LOG.debug("Starting lazy lowering expression construtor with asset: {}", asset);
		LOG.debug("Starting expression construtor for language: {}", lang);
		this.lang = lang;
		mapManifest = new HashMap<KRRDialect<?>, KnowledgeManifestation<?>>();
		mapAsset = new HashMap<ImmutableEnvironment, KnowledgeAsset>();
		mapAsset.put(asset.getEnvironment(), asset);
	}

	protected final HashMap<KRRDialect<?>, KnowledgeManifestation<?>> mapManifest;
	protected final HashMap<ImmutableEnvironment, KnowledgeAsset> mapAsset;
	protected final KRRLanguage lang;
	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public void clear() {
		clearManifest();
		clearAsset();
	}

	@Override
	public void clearManifest() {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapManifest.clear();
	}

	@Override
	public void clearAsset() {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapAsset.clear();
	}

	@Override
	public void clearManifest(KRRDialect<?> dialect) {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapManifest.remove(dialect);
	}

	@Override
	public void clearConceptualize(ImmutableEnvironment environment) {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapAsset.remove(environment);
	}

	@Override
	public KRRLanguage getLanguage() {
		return lang;
	}

	@Override
	public <T> KnowledgeManifestation<T> manifest(KRRDialect<T> dialect)
			throws DialectIncompatibleException {
		LOG.debug("Starting evaluation of the manifest of expression");
		LOG.debug("  Dialect of the manifestation: {}", dialect);
		LOG.debug("  Language of the expression: {}", lang);
		if (dialect.getLanguage() != lang){
			throw new DialectIncompatibleException();
		}
		LOG.debug("Manifestation cache: {}", mapManifest);
		if (!mapManifest.containsKey(dialect)) {
			LOG.debug("Found no cached manifestation for: {}", dialect);
			KnowledgeManifestation<T> manifest = evalManifest(dialect);
			manifestSafePut(dialect, manifest);
			return manifest;
		} else {
			// type compatibility is checked before caching
			// so that the type case is safe
			@SuppressWarnings("unchecked")
			KnowledgeManifestation<T> manifest = (KnowledgeManifestation<T>) mapManifest.get(dialect);
			return manifest;
		}
	}

	// nonpublic helper method
	protected abstract <T> KnowledgeManifestation<T> evalManifest(
			KRRDialect<T> dialect) throws DialectIncompatibleException;

	@Override
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
	
	<T> void manifestSafePut(KRRDialect<T> dialect, KnowledgeManifestation<T> manifest) {
		mapManifest.put(dialect, manifest);
	}
	

}
