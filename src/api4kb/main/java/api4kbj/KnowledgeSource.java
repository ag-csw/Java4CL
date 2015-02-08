package api4kbj;

import api4kbj7.IKnowledgeSource;

public interface KnowledgeSource extends Source, IKnowledgeSource {
	/**
	 * Returns the abstraction level.
	 * 
	 * @return the abstraction level
	 * @see KnowledgeAsset, KnowledgeExpression, KnowledgeManifestation,
	 *      KnowledgeEncoding, KnowledgeIO
	 */
	KnowledgeSourceLevel level();

}
