package krhashmap.li;

import krconfigured.BasicKnowledgeAssetConfigured;
import krconfigured.BasicKnowledgeExpressionConfigured;
import krconfigured.BasicKnowledgeResourceConfigured;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.FocusedImmutableLanguageEnvironment;

public class BasicKnowledgeAssetLI extends AbstractKnowledgeAssetLI implements
		BasicKnowledgeAssetConfigured {

	protected static final Logger SLOG = LoggerFactory
			.getLogger(BasicKnowledgeAssetLI.class);

	// private lazy lifting constructor
	protected BasicKnowledgeAssetLI(
			BasicKnowledgeResourceConfigured initialValue,
			FocusedImmutableLanguageEnvironment env) {
		super(initialValue, env);
	}

	// lazy lifting static factory method
	public static BasicKnowledgeAssetLI lazyNewInstance(
			BasicKnowledgeResourceConfigured initialValue,
			FocusedImmutableLanguageEnvironment env) {
		SLOG.debug("");
		return new BasicKnowledgeAssetLI(initialValue, env);
	}

	// TODO this method is required, but it is not possible
	// to clear the initial value for an ASSET unless another
	// cache is implemented (see BasicKnowledgeAssetLISME
	@Override
	public void clearInitialValue() throws UnsupportedOperationException {
		throw new UnsupportedOperationException(
				"The initial value cache of a BasicKnowledgeAssetLI cannot be cleared.");
	}

	@Override
	public BasicKnowledgeExpressionConfigured canonicalExpression() {
		// TODO Auto-generated method stub
		return null;
	}

}
