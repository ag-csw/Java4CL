package krhashmap.li.mse;

import java.util.HashSet;

import krconfigured.EnvironmentConfigured;
import krconfigured.StructuredKnowledgeResourceConfigured;
import krhashmap.li.AbstractKnowledgeExpressionLI;
import krhashmap.li.StructuredKnowledgeAssetLI;
import api4kbj.FocusedImmutableEnvironment;
import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeSourceLevel;
import elevation.Liftable;
import elevation.Lowerer;
import elevation.SelfLowering;

public class StructuredKnowledgeAssetLIMSE extends StructuredKnowledgeAssetLI
		implements SelfLowering {

	// empty asset constructor
	// public StructuredKnowledgeAssetLIMSE(FocusedImmutableEnvironment env) {
	// super(env);
	// }

	// lazy initializing constructor
	public StructuredKnowledgeAssetLIMSE(
			StructuredKnowledgeResourceConfigured kr,
			FocusedImmutableEnvironment env) {
		super(kr, env);
	}

	public EnvironmentConfigured lower(KnowledgeSourceLevel level) {
		switch (level) {
		case ASSET:
			return this;
		case EXPRESSION:
			return express();
			// TODO implement other cases
		default:
			return null;
		}
	}

	@Override
	public void clearInitialValue() {
		// TODO Auto-generated method stub

	}

	public EnvironmentConfigured lower() {
		return express();
	}

	// no-argument lowering method returns the no-argument lowering of each
	// component
	public AbstractKnowledgeExpressionLI express() {
		// TODO switch to Set Monad and apply map
		HashSet<KnowledgeExpression> expressionComponents = new HashSet<KnowledgeExpression>();
		for (KnowledgeAsset asset : components()) {
			// TODO check if asset is Self-Lowering
			// if not, use the standard Lifter
			expressionComponents
					.add((KnowledgeExpression) ((SelfLowering) asset).lower());
		}
		// return new StructuredKnowledgeExpressionLI(this.environment,
		// expressionComponents);
		return null;
	}

	@Override
	public KnowledgeSourceLevel level() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBasic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Lowerer lowerer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Liftable lower(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

}
