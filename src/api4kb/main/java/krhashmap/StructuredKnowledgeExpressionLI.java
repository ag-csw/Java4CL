package krhashmap;

import graphenvironment.GraphImmutableEnvironment;

import java.util.HashSet;
import java.util.Set;

import api4kbj.AbstractKRRLanguage;
import api4kbj.Decomposable;
import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeSourceLevel;
import api4kbj.StructuredKnowledgeResource;

public class StructuredKnowledgeExpressionLI extends
		AbstractKnowledgeExpression implements
		Decomposable<KnowledgeExpression>, StructuredKnowledgeResource {

	public StructuredKnowledgeExpressionLI(
			AbstractKnowledgeResourceLI initialValue) {
		super(initialValue);
	}

	public StructuredKnowledgeExpressionLI(GraphImmutableEnvironment env,
			KnowledgeExpression... components) {
		super(false, env);
		for (KnowledgeExpression component : components) {
			this.components.add(component);
		}
	}

	public StructuredKnowledgeExpressionLI(GraphImmutableEnvironment env,
			HashSet<KnowledgeExpression> components) {
		super(false, env);
		this.components.addAll(components);
	}

	protected final HashSet<KnowledgeExpression> components = new HashSet<KnowledgeExpression>();
	protected final HashSet<AbstractKRRLanguage> langs = new HashSet<AbstractKRRLanguage>();

	@Override
	public Set<KnowledgeExpression> components() {
		return components;
	}

	public StructuredKnowledgeAssetLI conceptualize(
			GraphImmutableEnvironment environment) {
		LOG.debug("Starting conceptualization relative to environment : {}",
				environment);
		if (!environment.containsLanguages(langs)) {
			throw new IllegalArgumentException("Requested envionment"
					+ environment.toString()
					+ " does not contain all of the expression languages:"
					+ langs);
		}
		// TODO consider replacing level check with instanceof
		if ((initialValue != null)
				&& (initialValue.level() == KnowledgeSourceLevel.ASSET)) {
			StructuredKnowledgeAssetLI asset = (StructuredKnowledgeAssetLI) initialValue;
			LOG.debug("Found cached intial value for asset: {}", asset);
			if (asset.environment().equals(environment)) {
				LOG.debug("Using cached intial value");
				return asset;
			}
		}
		if (mapAsset.containsKey(environment)) {
			LOG.debug("Found cached asset in this environment");
			StructuredKnowledgeAssetLI asset = (StructuredKnowledgeAssetLI) mapAsset
					.get(environment);
			LOG.debug("Using cached asset: {}", asset);
			return asset;
		}
		LOG.debug("Found no cached asset for: {}", environment);
		// Last resort: create a new asset
		return newAsset(environment);
	}

	protected StructuredKnowledgeAssetLI newAsset(
			GraphImmutableEnvironment environment) {
		return StructuredKnowledgeAssetLI.lazyNewInstance(this, environment);

	}

	@Override
	public void clearInitialValue() throws Exception {
		// TODO implement this
		throw new Exception(
				"Clearing the lazy initialization cache is not supported yet.");
	}

}
