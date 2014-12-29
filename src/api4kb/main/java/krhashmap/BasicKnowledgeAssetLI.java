package krhashmap;

import graphenvironment.GraphImmutableEnvironment;
import api4kbj.KnowledgeResource;
import api4kbj.BasicKnowledgeAsset;
import api4kbj.KRRLanguage;

public class BasicKnowledgeAssetLI extends AbstractKnowledgeAsset implements
		BasicKnowledgeAsset {

	// private lazy initializing constructor
	private BasicKnowledgeAssetLI(KnowledgeResource initialValue,
			GraphImmutableEnvironment env) {
		super(initialValue, env);
	}

	// lazy lifting static factory method
	public static BasicKnowledgeAssetLI lazyNewInstance(
			KnowledgeResource initialValue, GraphImmutableEnvironment env) {
		return new BasicKnowledgeAssetLI(initialValue, env);
	}

	@Override
	AbstractBasicKnowledgeExpression newExpression(KRRLanguage lang) {
		// TODO check the initial value first
		AbstractBasicKnowledgeExpression expression = super.mapExpression
				.values().iterator().next();
		return (AbstractBasicKnowledgeExpression) environment().translate(
				expression, lang);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BasicKnowledgeAsset)) {
			return false;
		}
		final BasicKnowledgeAsset other = (BasicKnowledgeAsset) obj;
		if (!other.defaultCodecSystem().equals(this.defaultCodecSystem())
				|| !other.defaultDialect().equals(this.defaultDialect())
				|| !other.defaultDialectType()
						.equals(this.defaultDialectType())
				|| !other.defaultEnvironment()
						.equals(this.defaultEnvironment())
				|| !other.defaultLanguage().equals(this.defaultLanguage())
				|| !other.defaultReceiver().equals(this.defaultReceiver())
				|| !other.defaultSender().equals(this.defaultSender())
				|| !other.environment().equals(this.environment())) {
			return false;
		}
		// TODO implement evaluation against equivalence relation
		return true;
	}

	@Override
	public AbstractKnowledgeExpression express() {
		// TODO Auto-generated method stub
		return null;
	}

}
