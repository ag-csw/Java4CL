package krhashmap.li;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.BasicKnowledgeExpression;
import api4kbj.BasicKnowledgeResource;
import api4kbj.BasicKnowledgeAsset;
import api4kbj.FocusedImmutableEnvironment;

public class BasicKnowledgeAssetLI extends AbstractKnowledgeAssetLI implements
		BasicKnowledgeAsset {

	protected static final Logger SLOG = LoggerFactory
			.getLogger(BasicKnowledgeAssetLI.class);

	// private lazy lifting constructor
	protected BasicKnowledgeAssetLI(BasicKnowledgeResource initialValue,
			FocusedImmutableEnvironment env) {
		super(initialValue, env);
	}

	// lazy lifting static factory method
	public static BasicKnowledgeAssetLI lazyNewInstance(
			BasicKnowledgeResource initialValue, FocusedImmutableEnvironment env) {
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
	public BasicKnowledgeExpression canonicalExpression() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAtomic() {
		return true;
	}

}
