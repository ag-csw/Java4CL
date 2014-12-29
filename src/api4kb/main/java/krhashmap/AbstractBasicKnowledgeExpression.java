package krhashmap;

import java.util.HashMap;

import graphenvironment.GraphImmutableEnvironment;
import api4kbj.AbstractKRRDialectType;
import api4kbj.AbstractKRRLanguage;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractBasicKnowledgeExpression extends
		AbstractKnowledgeExpression implements BasicKnowledgeExpression {

	// initializing only constructor
	protected <T> AbstractBasicKnowledgeExpression(AbstractKRRLanguage lang) {
		super(true, lang);
		LOG.debug("Starting initializing constructor for language: {}", lang);
		this.lang = lang;
	}

	// Lazy lifting constructor - argument is a ManifestationG<T>
	public <T> AbstractBasicKnowledgeExpression(
			AbstractBasicKnowledgeManifestationG<T> manifestation) {
		this(manifestation.dialectType().language());
		LOG.debug(
				"Starting lazy lifting expression construtor with manifestation: {}",
				manifestation);
		initialValue = manifestation;
		manifestSafePut(manifestation);
	}

	// Lazy lifting constructor - argument is a Manifestation
	public <T> AbstractBasicKnowledgeExpression(
			AbstractBasicKnowledgeManifestation manifestation) {
		this(manifestation.dialect().language());
		LOG.debug(
				"Starting lazy lifting expression construtor with manifestation: {}",
				manifestation);
		initialValue = manifestation;
	}

	// Lazy lowering constructor - argument is an Asset
	public AbstractBasicKnowledgeExpression(BasicKnowledgeAssetLI asset,
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

	// protected and private fields
	// final properties
	private final KRRLanguage lang;
	protected final HashMap<AbstractKRRDialectType<?>, AbstractBasicKnowledgeManifestationG<?>> mapManifest = new HashMap<AbstractKRRDialectType<?>, AbstractBasicKnowledgeManifestationG<?>>();

	// default lowering method returns a manifestation in the default dialect
	// for that language
	public AbstractBasicKnowledgeManifestationG<?> manifest() {
		LOG.debug("Starting default manifest of expression");
		return manifest((AbstractKRRDialectType<?>) language()
				.defaultDialectType());
	}

	// lowering method with a parameter indicating the dialect
	// with generic T for the format (e.g. String, XML Element)
	public <T> AbstractBasicKnowledgeManifestationG<T> manifest(
			AbstractKRRDialectType<T> dialectType) {
		LOG.debug("Starting manifest of expression");
		LOG.debug("  Dialect of the manifestation: {}", dialectType);
		KRRLanguage lang = language();
		LOG.debug("  Language of the expression: {}", lang);
		if (dialectType.language() != lang) {
			throw new IllegalArgumentException(
					"Requested dialect type is not supported:"
							+ lang.toString() + " : " + dialectType.toString());
		}
		// TODO consider replacing level check with instanceof
		if ((initialValue != null)
				&& (initialValue.level() == KnowledgeSourceLevel.MANIFESTATION)) {
			AbstractBasicKnowledgeManifestationG<?> manifestation = (AbstractBasicKnowledgeManifestationG<?>) initialValue;
			LOG.debug("Found cached intial value for manifestation: {}",
					manifestation);
			if (manifestation.dialectType() == dialectType) {
				LOG.debug("Using cached intial value");
				return (AbstractBasicKnowledgeManifestationG<T>) manifestation;
			}
		}
		if (mapManifest.containsKey(dialectType)) {
			LOG.debug("Found cached manifestation for requested dialect Type");
			@SuppressWarnings("unchecked")
			AbstractBasicKnowledgeManifestationG<T> manifestation = (AbstractBasicKnowledgeManifestationG<T>) mapManifest
					.get(dialectType);
			LOG.debug("Using cached manifestation: {}", manifestation);
			return manifestation;
		}
		LOG.debug("Found no cached manifestation for: {}", dialectType);
		// Last resort: create a new expression
		return newManifestation(dialectType);
	}

	protected abstract <T> AbstractBasicKnowledgeManifestationG<T> newManifestation(
			AbstractKRRDialectType<T> dialectType);

	// default lifting method returns a asset in the default environment
	// for this language
	public BasicKnowledgeAssetLI conceptualize() {
		KRRLanguage lang = language();
		if (lang.defaultEnvironment() != null) {
			return conceptualize((GraphImmutableEnvironment) lang
					.defaultEnvironment());
		}
		// TODO create a singleton environment containing the language
		return null;
	}

	public BasicKnowledgeAssetLI conceptualize(
			GraphImmutableEnvironment environment) {
		LOG.debug("Starting conceptualization relative to environment : {}",
				environment);
		if (!environment.containsLanguage(lang)) {
			throw new IllegalArgumentException("Requested envionment"
					+ environment.toString()
					+ " does not contain the expression languages:" + lang);
		}
		// TODO consider replacing level check with instanceof
		if ((initialValue != null)
				&& (initialValue.level() == KnowledgeSourceLevel.ASSET)) {
			BasicKnowledgeAssetLI asset = (BasicKnowledgeAssetLI) initialValue;
			LOG.debug("Found cached intial value for asset: {}", asset);
			if (asset.environment().equals(environment)) {
				LOG.debug("Using cached intial value");
				return asset;
			}
		}
		if (mapAsset.containsKey(environment)) {
			LOG.debug("Found cached asset in this environment");
			BasicKnowledgeAssetLI asset = (BasicKnowledgeAssetLI) mapAsset
					.get(environment);
			LOG.debug("Using cached asset: {}", asset);
			return asset;
		}
		LOG.debug("Found no cached asset for: {}", environment);
		// Last resort: create a new asset
		return newAsset(environment);
	}

	protected BasicKnowledgeAssetLI newAsset(
			GraphImmutableEnvironment environment) {
		return BasicKnowledgeAssetLI.lazyNewInstance(this, environment);

	}

	@Override
	public KRRLanguage language() {
		return lang;
	}

	protected <T> void manifestSafePut(
			AbstractBasicKnowledgeManifestationG<T> manifest) {
		mapManifest.put(manifest.dialectType(), manifest);
	}

	@Override
	public void clearInitialValue() {
		LOG.debug("Starting clearInitialValue");
		if ((!mapManifest.isEmpty()) | (!mapAsset.isEmpty())) {
			LOG.debug("Safe to clear initial value");
			this.unsafeClearInitialValue();
		}
	}

	public void clearManifest() {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapManifest.clear();
	}

	@Override
	public void clear() {
		super.clear();
		clearManifest();
	}

	public void clearManifest(AbstractKRRDialectType<?> dialectType) {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapManifest.remove(dialectType);
	}

}
