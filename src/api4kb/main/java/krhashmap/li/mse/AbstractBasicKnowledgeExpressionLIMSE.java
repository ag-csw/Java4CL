package krhashmap.li.mse;

import java.util.HashMap;

import krconfigured.BasicKnowledgeAssetConfigured;
import krconfigured.BasicKnowledgeManifestationConfigured;
import krconfigured.BasicKnowledgeResourceConfigured;
import krconfigured.KnowledgeResourceConfiguredTemplate;
import krhashmap.AbstractBasicKnowledgeExpression;
import krhashmap.li.BasicKnowledgeAssetLI;
import api4kbj.FocusedImmutableEnvironment;
import api4kbj.KRRDialect;
import api4kbj.KRRDialectType;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeSourceLevel;
import elevation.SelfLiftingExpression;
import elevation.SelfLoweringExpression;

public abstract class AbstractBasicKnowledgeExpressionLIMSE extends
		AbstractBasicKnowledgeExpression implements SelfLoweringExpression,
		SelfLiftingExpression {

	// base non-lazy constructor
	public AbstractBasicKnowledgeExpressionLIMSE(
			KnowledgeResourceConfiguredTemplate template, KRRLanguage lang) {
		super(template, lang);
	}

	// No-parameter Lazy initializing constructor
	public AbstractBasicKnowledgeExpressionLIMSE(BasicKnowledgeResourceConfigured kr) {
		super(kr);
		// TODO level generalization needed
		manifestSafePut((BasicKnowledgeManifestationConfigured) kr);
	}

	// Lazy lowering constructor - argument is an Asset
	public AbstractBasicKnowledgeExpressionLIMSE(BasicKnowledgeAssetConfigured kr,
			KRRLanguage lang) {
		super(kr, lang);
		assetSafePut(kr);
	}

	// Asset cache for lifting
	protected final HashMap<FocusedImmutableEnvironment, KnowledgeAsset> mapAsset = new HashMap<FocusedImmutableEnvironment, KnowledgeAsset>();
	protected final HashMap<KRRDialect, BasicKnowledgeManifestationConfigured> mapManifest = new HashMap<KRRDialect, BasicKnowledgeManifestationConfigured>();

	// default lowering method returns a manifestation in the default dialect
	// for that language
	public BasicKnowledgeManifestationConfigured manifest() {
		LOG.debug("Starting default manifest of expression");
		return manifest(defaultDialect());
	}

	// default lifting method returns a asset in the default environment
	public BasicKnowledgeAssetLI conceptualize() {
		KRRLanguage lang = language();
		if (defaultEnvironment() != null) {
			return conceptualize(defaultEnvironment());
		}
		// TODO create a singleton environment containing the language
		return null;
	}

	public BasicKnowledgeAssetLI conceptualize(
			FocusedImmutableEnvironment environment) {
		LOG.debug("Starting conceptualization relative to environment : {}",
				environment);
		if (!environment.containsLanguage(language())) {
			throw new IllegalArgumentException("Requested envionment"
					+ environment.toString()
					+ " does not contain the expression languages:"
					+ language());
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

	// lowering method with a parameter indicating the dialect
	public BasicKnowledgeManifestationConfigured manifest(KRRDialect dialect) {
		LOG.debug("Starting manifest of expression");
		LOG.debug("  Dialect of the manifestation: {}", dialect);
		KRRLanguage lang = language();
		LOG.debug("  Language of the expression: {}", lang);
		if (dialect.language() != lang) {
			throw new IllegalArgumentException(
					"Requested dialect type is not supported:"
							+ lang.toString() + " : " + dialect.toString());
		}
		// TODO consider replacing level check with instanceof
		if ((initialValue != null)
				&& (initialValue.level() == KnowledgeSourceLevel.MANIFESTATION)) {
			BasicKnowledgeManifestationConfigured manifestation = (BasicKnowledgeManifestationConfigured) initialValue;
			LOG.debug("Found cached intial value for manifestation: {}",
					manifestation);
			if (manifestation.dialect() == dialect) {
				LOG.debug("Using cached intial value");
				return manifestation;
			}
		}
		if (mapManifest.containsKey(dialect)) {
			LOG.debug("Found cached manifestation for requested dialect Type");
			@SuppressWarnings("unchecked")
			BasicKnowledgeManifestationConfigured manifestation = mapManifest
					.get(dialect);
			LOG.debug("Using cached manifestation: {}", manifestation);
			return manifestation;
		}
		LOG.debug("Found no cached manifestation for: {}", dialect);
		// Last resort: create a new expression
		return newManifestation(dialect);
	}

	protected BasicKnowledgeAssetLI newAsset(
			FocusedImmutableEnvironment environment) {
		return BasicKnowledgeAssetLI.lazyNewInstance(this, environment);

	}

	protected abstract BasicKnowledgeManifestationConfigured newManifestation(
			KRRDialect dialect);

	protected void assetSafePut(BasicKnowledgeAssetConfigured kr) {
		mapAsset.put(kr.environment(), kr);
	}

	protected <T> void manifestSafePut(BasicKnowledgeManifestationConfigured manifest) {
		mapManifest.put(manifest.dialect(), manifest);
	}

	public void clear() {
		clearManifest();
		clearAsset();
	}

	public void clearAsset() {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapAsset.clear();
	}

	public void clearConceptualize(FocusedImmutableEnvironment environment) {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapAsset.remove(environment);
	}

	public void clearManifest() {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapManifest.clear();
	}

	public void clearManifest(KRRDialectType<?> dialectType) {
		// TODO check that this removal will not put object into
		// inconsistent state before removing
		mapManifest.remove(dialectType);
	}

	@Override
	public void clearInitialValue() {
		// TODO move to LIMSE: check other caches to see if it is OK to clear
		// the initial value
		LOG.debug("Starting clearInitialValue");
		if ((!mapManifest.isEmpty()) | (!mapAsset.isEmpty())) {
			LOG.debug("Safe to clear initial value");
			this.unsafeClearInitialValue();
		}
	}

}
