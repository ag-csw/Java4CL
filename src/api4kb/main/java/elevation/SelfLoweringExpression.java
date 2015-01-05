package elevation;

import api4kbj.KnowledgeExpression;
import api4kbj.KnowledgeManifestation;
import api4kbj.KnowledgeSourceLevel;

public interface SelfLoweringExpression extends SelfLowering,
		KnowledgeExpression {

	@Override
	default KnowledgeManifestation lower(Object... args) {
		return (KnowledgeManifestation) lower(
				KnowledgeSourceLevel.MANIFESTATION, args);
	}

}
