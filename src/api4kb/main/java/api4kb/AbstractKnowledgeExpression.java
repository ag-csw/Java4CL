package api4kb;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKnowledgeExpression extends
		AbstractKnowledgeResource implements KnowledgeExpression {

	// initializing only constructor
	protected <T> AbstractKnowledgeExpression(AbstractKRRLanguage lang) {
		LOG.debug("Starting initializing constructor for language: {}", lang);
		this.lang = lang;
	}

	// Lazy lifting constructor - argument is a Manifestation
	public <T> AbstractKnowledgeExpression(
			AbstractKnowledgeManifestationG<T> manifestation) {
		this(manifestation.getDialectType().getLanguage());
		LOG.debug(
				"Starting lazy lifting expression construtor with manifestation: {}",
				manifestation);
		initialValue = manifestation;
		manifestSafePut(manifestation);
	}

	// Lazy lowering constructor - argument is an Asset
	public AbstractKnowledgeExpression(KnowledgeAssetLI asset,
			AbstractKRRLanguage lang) throws LanguageIncompatibleException {
		this(lang);
		LOG.debug(
				"Starting lazy lowering expression construtor");
		if (!asset.getEnvironment().containsLanguage(lang)) {
			throw new LanguageIncompatibleException("Requested language is not in the environment.");
		}
		LOG.debug("Language compatibility verified");
		initialValue = asset;
		assetSafePut(asset);
	}

	// protected fields
    // final properties 
	protected final AbstractKRRLanguage lang;
	// cache for lifting and lowering methods
	protected final HashMap<AbstractKRRDialectType<?>, AbstractKnowledgeManifestationG<?>> mapManifest = new HashMap<AbstractKRRDialectType<?>, AbstractKnowledgeManifestationG<?>>();
	protected final HashMap<ImmutableEnvironment, KnowledgeAssetLI> mapAsset = new HashMap<ImmutableEnvironment, KnowledgeAssetLI>();
	//
	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	protected static final Logger SLOG = LoggerFactory.getLogger(AbstractKnowledgeExpression.class);

	@Override
	public KnowledgeSourceLevel getLevel() {
		LOG.debug("Getting level: {}", level);
		return level;
	}

	@Override
	public AbstractKRRLanguage getLanguage() {
		LOG.debug("Getting language: {}", lang);
		return lang;
	}

	// default lowering method returns a manifestation in the default dialect
	// for that language
	public AbstractKnowledgeManifestationG<?> manifest()
			throws DialectTypeIncompatibleException {
		LOG.debug("Starting default manifest of expression");
		return manifest(lang.defaultDialectType());
	}

	// lowering method with a parameter indicating the dialect
	// with generic T for the format (e.g. String, XML Element)
	public <T> AbstractKnowledgeManifestationG<T> manifest(
			AbstractKRRDialectType<T> dialectType)
			throws DialectTypeIncompatibleException {
		LOG.debug("Starting manifest of expression");
		LOG.debug("  Dialect of the manifestation: {}", dialectType);
		LOG.debug("  Language of the expression: {}", lang);
		if (dialectType.getLanguage() != lang) {
			throw new DialectTypeIncompatibleException(
					"Requested dialect type is not supported:"
							+ lang.toString() + " : " + dialectType.toString());
		}
		// TODO consider replacing level check with instanceof
		if ((initialValue != null) && (initialValue.getLevel() == KnowledgeSourceLevel.MANIFESTATION)){
			AbstractKnowledgeManifestationG<?> manifestation = (AbstractKnowledgeManifestationG<?>) initialValue;
			LOG.debug("Found cached intial value for manifestation: {}", manifestation);
			if (manifestation.getDialectType() == dialectType){
			  LOG.debug("Using cached intial value");
			  return (AbstractKnowledgeManifestationG<T>) manifestation;
			}
		}
		if (mapManifest.containsKey(dialectType)) {
			LOG.debug("Found cached manifestation for requested dialect Type");
			@SuppressWarnings("unchecked")
			AbstractKnowledgeManifestationG<T> manifestation = (AbstractKnowledgeManifestationG<T>) mapManifest.get(dialectType);
			LOG.debug("Using cached manifestation: {}", manifestation);
			return manifestation;
		}
		LOG.debug("Found no cached manifestation for: {}", dialectType);
		// Last resort: create a new expression
		return newManifestation(dialectType);
	}

	// eager lowering
	protected abstract<T> AbstractKnowledgeManifestationG<T> newManifestation(AbstractKRRDialectType<T> dialectType);
	//	return new AbstractKnowledgeManifestationG<T>(dialectType){}

	// default lifting method returns a asset in the default environment
	// for this language
	public AbstractKnowledgeAsset conceptualize() throws EnvironmentIncompatibleException{
		if (lang.getDefaultEnvironment() != null){	
		  return conceptualize(lang.getDefaultEnvironment());
		}
		// TODO create a singleton environment containing the language
		throw new EnvironmentIncompatibleException();
	}

	// lifting method with a parameter indicating the language
	public KnowledgeAssetLI conceptualize(GraphImmutableEnvironment environment)
			throws EnvironmentIncompatibleException {
		LOG.debug("Starting conceptualization relative to environment : {}", environment);
		if (!environment.containsLanguage(lang)){
			throw new EnvironmentIncompatibleException("Requested envionment does not contain the language:" + lang);
		}
		// TODO consider replacing level check with instanceof
		if ((initialValue != null) && (initialValue.getLevel() == KnowledgeSourceLevel.ASSET)){
			KnowledgeAssetLI asset = (KnowledgeAssetLI) initialValue;
			LOG.debug("Found cached intial value for asset: {}", asset);
			if (asset.getEnvironment().equals(environment)){
			  LOG.debug("Using cached intial value");
			  return asset;
			}
		}
		if (mapAsset.containsKey(lang)){			
			LOG.debug("Found cached asset in this language");
			KnowledgeAssetLI asset = mapAsset.get(environment);
			LOG.debug("Using cached expression: {}", asset);
            return asset;
		}
		LOG.debug("Found no cached expression for: {}", environment);
		// Last resort: create a new asset
		return newAsset(environment);
	}
	
	// eager lifting
	protected KnowledgeAssetLI newAsset(GraphImmutableEnvironment environment){
		return new KnowledgeAssetLI(this, environment);

	};



	<T> void manifestSafePut(AbstractKnowledgeManifestationG<T> manifest) {
		mapManifest.put(manifest.getDialectType(), manifest);
	}

	void assetSafePut(KnowledgeAssetLI asset) {
		mapAsset.put(asset.getEnvironment(), asset);
	}

	// verify that some other equivalent property has been set
	// before forgetting initial value, to avoid leaving object
	// in inconsistent "state".
	@Override
	public void clearInitialValue() {
		LOG.debug("Starting clearInitialValue");
		if ((!mapManifest.isEmpty()) | (!mapAsset.isEmpty())) {
			LOG.debug("Safe to clear initial value");
			super.unsafeClearInitialValue();
		}
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
	public void clearManifest(AbstractKRRDialectType<?> dialectType) {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapManifest.remove(dialectType);
	}

	// clear memoization cache of the conceptualize method for the particular
	// environment
	public void clearConceptualize(ImmutableEnvironment environment) {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapAsset.remove(environment);
	}




}
