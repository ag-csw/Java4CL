package krhashmap.li;

import java.util.HashSet;
import java.util.Set;

import krconfigured.KnowledgeResourceConfiguredTemplate;
import krconfigured.StructuredKnowledgeExpressionConfigured;
import krconfigured.StructuredKnowledgeResourceConfigured;
import api4kbj.Decomposable;
import api4kbj.FocusedImmutableEnvironment;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeSourceLevel;

public abstract class StructuredKnowledgeExpressionLI extends
		AbstractKnowledgeExpressionLI implements
		StructuredKnowledgeExpressionConfigured<KnowledgeExpression> {

	// lazy intialization constructor
	public StructuredKnowledgeExpressionLI(
			AbstractKnowledgeResourceLI initialValue) {
		super(initialValue);
	}

	// component-based structure constructor
	public StructuredKnowledgeExpressionLI(
			KnowledgeResourceConfiguredTemplate template,
			KnowledgeExpression... components) {
		super(template);
		for (KnowledgeExpression component : components) {
			this.components.add(component);
		}
	}

	public StructuredKnowledgeExpressionLI(
			KnowledgeResourceConfiguredTemplate template,
			HashSet<KnowledgeExpression> components) {
		super(template);
		this.components.addAll(components);
	}

	protected final HashSet<KnowledgeExpression> components = new HashSet<KnowledgeExpression>();
	protected final Set<KRRLanguage> langs = new HashSet<KRRLanguage>();

	@Override
	public Set<KnowledgeExpression> components() {
		return components;
	}

	public StructuredKnowledgeAssetLI conceptualize(
			FocusedImmutableEnvironment environment) {
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
		LOG.debug("Found no cached asset for: {}", environment);
		// Last resort: create a new asset
		return newAsset(environment);
	}

	protected StructuredKnowledgeAssetLI newAsset(
			FocusedImmutableEnvironment environment) {
		return StructuredKnowledgeAssetLI.lazyNewInstance(this, environment);

	}

	@Override
	public void clearInitialValue() throws Exception {
		// TODO implement this
		throw new Exception(
				"Clearing the lazy initialization cache is not supported yet.");
	}

	@Override
	public int numComponents() {
		// TODO Auto-generated method stub
		return components.size();
	}

}
