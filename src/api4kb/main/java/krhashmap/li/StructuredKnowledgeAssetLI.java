package krhashmap.li;

import java.util.HashSet;

import krconfigured.KnowledgeResourceConfiguredTemplate;
import krconfigured.StructuredKnowledgeAssetConfigured;
import krconfigured.StructuredKnowledgeExpressionConfigured;
import krconfigured.StructuredKnowledgeResourceConfigured;
import api4kbj.FocusedImmutableEnvironment;
import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeResource;
import api4kbj.StructuredKnowledgeExpression;
import api4kbj.StructuredKnowledgeResource;

public class StructuredKnowledgeAssetLI extends AbstractKnowledgeAssetLI
		implements StructuredKnowledgeAssetConfigured<KnowledgeAsset, KnowledgeExpression> {

	// lazy initializing constructor - lifting
	public <R extends KnowledgeResource> StructuredKnowledgeAssetLI(
			StructuredKnowledgeResourceConfigured<R> initialValue,
			FocusedImmutableEnvironment env) {
		super(initialValue, env);
	}

	public StructuredKnowledgeAssetLI(
			KnowledgeResourceConfiguredTemplate template,
			FocusedImmutableEnvironment env, KnowledgeAsset... krs) {
		super(template, env);
		for (KnowledgeAsset kr : krs) {
			components.add(kr);
		}
		componentsInitialized = true;
		clearInitialValue();

	}

	private HashSet<KnowledgeAsset> components = new HashSet<KnowledgeAsset>();
	private Boolean componentsInitialized = false;

	@Override
	public int numComponents() {
		if (initialValue != null) {
			return ((StructuredKnowledgeResource<?>) initialValue).numComponents();
		}
		return components.size();
	}

	@Override
	public Iterable<KnowledgeAsset> components() {
		evalComponents();
		return components;
	}

	public static <R extends KnowledgeResource> StructuredKnowledgeAssetLI lazyNewInstance(
			StructuredKnowledgeResourceConfigured<R> kr,
			FocusedImmutableEnvironment environment) {
		return new StructuredKnowledgeAssetLI(kr, environment);
	}

	@Override
	public void clearInitialValue() {
		evalComponents();
		unsafeClearInitialValue();
	}

	private void evalComponents() {
		if (!componentsInitialized) {
			// TODO initialize the components from the initial value
		}
		componentsInitialized = true;

	}

	@Override
	/**
	 * Returns the canonical expression of the asset.
	 * 
	 * @return the canonical expression of the asset
	 */
	public StructuredKnowledgeExpression<KnowledgeExpression> canonicalExpression() {
		// TODO Auto-generated method stub
		return null;
	}

}
