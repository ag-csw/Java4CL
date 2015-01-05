package elevation;

import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeSourceLevel;

public interface SelfLiftingExpression extends SelfLifting, KnowledgeExpression {

	@Override
	default KnowledgeAsset lift() {
		return (KnowledgeAsset) lift(KnowledgeSourceLevel.ASSET);
	}

}
