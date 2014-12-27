package krhashmap;

import graphenvironment.GraphImmutableEnvironment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl2.CL;
import api4kbj.AbstractKRRDialectType;
import api4kbj.AbstractKRRLanguage;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.ImmutableEnvironment;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractBasicKnowledgeExpression extends
		AbstractKnowledgeResource implements BasicKnowledgeExpression {

	// initializing only constructor
	protected <T> AbstractBasicKnowledgeExpression(AbstractKRRLanguage lang) {
		LOG.debug("Starting initializing constructor for language: {}", lang);
		this.lang = lang;
		langs.add(lang);
	}

	// Lazy lifting constructor - argument is a Manifestation
	public <T> AbstractBasicKnowledgeExpression(
			AbstractKnowledgeManifestationG<T> manifestation) {
		this(manifestation.dialectType().language());
		LOG.debug(
				"Starting lazy lifting expression construtor with manifestation: {}",
				manifestation);
		initialValue = manifestation;
		manifestSafePut(manifestation);
	}

	// Lazy lowering constructor - argument is an Asset
	public AbstractBasicKnowledgeExpression(KnowledgeAssetLI asset,
			AbstractKRRLanguage lang) {
		this(lang);
		LOG.debug("Starting lazy lowering expression construtor");
		if (!asset.environment().containsLanguage(lang)) {
			throw new IllegalArgumentException(
					"Requested language is not in the environment.");
		}
		LOG.debug("Language compatibility verified");
		initialValue = asset;
		assetSafePut(asset);
	}

	// protected fields
	// final properties
	protected final AbstractKRRLanguage lang;
	protected final HashSet<AbstractKRRLanguage> langs = new HashSet<AbstractKRRLanguage>();
	protected final HashSet<KnowledgeExpression> components = new HashSet<KnowledgeExpression>();
	// cache for lifting and lowering methods
	protected final HashMap<AbstractKRRDialectType<?>, AbstractKnowledgeManifestationG<?>> mapManifest = new HashMap<AbstractKRRDialectType<?>, AbstractKnowledgeManifestationG<?>>();
	protected final HashMap<ImmutableEnvironment, KnowledgeAssetLI> mapAsset = new HashMap<ImmutableEnvironment, KnowledgeAssetLI>();
	//
	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	protected static final Logger SLOG = LoggerFactory
			.getLogger(AbstractBasicKnowledgeExpression.class);

	@Override
	public KnowledgeSourceLevel level() {
		LOG.debug("Getting level: {}", level);
		return level;
	}

	@Override
	public AbstractKRRLanguage language() {
		LOG.debug("Getting language: {}", lang);
		return lang;
	}

	// default lowering method returns a manifestation in the default dialect
	// for that language
	public AbstractKnowledgeManifestationG<?> manifest() {
		LOG.debug("Starting default manifest of expression");
		return manifest(lang.defaultDialectType());
	}

	// lowering method with a parameter indicating the dialect
	// with generic T for the format (e.g. String, XML Element)
	public <T> AbstractKnowledgeManifestationG<T> manifest(
			AbstractKRRDialectType<T> dialectType) {
		LOG.debug("Starting manifest of expression");
		LOG.debug("  Dialect of the manifestation: {}", dialectType);
		LOG.debug("  Language of the expression: {}", lang);
		if (dialectType.language() != lang) {
			throw new IllegalArgumentException(
					"Requested dialect type is not supported:"
							+ lang.toString() + " : " + dialectType.toString());
		}
		// TODO consider replacing level check with instanceof
		if ((initialValue != null)
				&& (initialValue.level() == KnowledgeSourceLevel.MANIFESTATION)) {
			AbstractKnowledgeManifestationG<?> manifestation = (AbstractKnowledgeManifestationG<?>) initialValue;
			LOG.debug("Found cached intial value for manifestation: {}",
					manifestation);
			if (manifestation.dialectType() == dialectType) {
				LOG.debug("Using cached intial value");
				return (AbstractKnowledgeManifestationG<T>) manifestation;
			}
		}
		if (mapManifest.containsKey(dialectType)) {
			LOG.debug("Found cached manifestation for requested dialect Type");
			@SuppressWarnings("unchecked")
			AbstractKnowledgeManifestationG<T> manifestation = (AbstractKnowledgeManifestationG<T>) mapManifest
					.get(dialectType);
			LOG.debug("Using cached manifestation: {}", manifestation);
			return manifestation;
		}
		LOG.debug("Found no cached manifestation for: {}", dialectType);
		// Last resort: create a new expression
		return newManifestation(dialectType);
	}

	// eager lowering
	protected abstract <T> AbstractKnowledgeManifestationG<T> newManifestation(
			AbstractKRRDialectType<T> dialectType);

	// return new AbstractKnowledgeManifestationG<T>(dialectType){}

	// default lifting method returns a asset in the default environment
	// for this language
	public AbstractKnowledgeAsset conceptualize() {
		if (lang.defaultEnvironment() != null) {
			return conceptualize((GraphImmutableEnvironment) lang
					.defaultEnvironment());
		}
		// TODO create a singleton environment containing the language
		return null;
	}

	// lifting method with a parameter indicating the language
	public KnowledgeAssetLI conceptualize(GraphImmutableEnvironment environment) {
		LOG.debug("Starting conceptualization relative to environment : {}",
				environment);
		if (!environment.containsLanguage(lang)) {
			throw new IllegalArgumentException("Requested envionment"
					+ environment.toString()
					+ " does not contain the expression language:" + lang);
		}
		// TODO consider replacing level check with instanceof
		if ((initialValue != null)
				&& (initialValue.level() == KnowledgeSourceLevel.ASSET)) {
			KnowledgeAssetLI asset = (KnowledgeAssetLI) initialValue;
			LOG.debug("Found cached intial value for asset: {}", asset);
			if (asset.environment().equals(environment)) {
				LOG.debug("Using cached intial value");
				return asset;
			}
		}
		if (mapAsset.containsKey(lang)) {
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
	protected KnowledgeAssetLI newAsset(GraphImmutableEnvironment environment) {
		return new KnowledgeAssetLI(this, environment);

	};

	<T> void manifestSafePut(AbstractKnowledgeManifestationG<T> manifest) {
		mapManifest.put(manifest.dialectType(), manifest);
	}

	void assetSafePut(KnowledgeAssetLI asset) {
		mapAsset.put(asset.environment(), asset);
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
	
	@Override
	public Set<KRRLanguage> languages() {
		HashSet<KRRLanguage> langs =  new HashSet<KRRLanguage>();
		langs.add(CL.lang);
		return langs;
	}

	@Override
	public Set<KnowledgeExpression> components() {
		// TODO Auto-generated method stub
		return components;
	}

	@Override
	public Boolean isBasic() {
		// TODO Auto-generated method stub
		return true;
	}


}
