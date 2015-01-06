package api4kbj;

public interface KnowledgeSource extends Source {
	/**
	 * Returns the abstraction level.
	 * 
	 * @return the abstraction level
	 * @see KnowledgeAsset, KnowledgeExpression, KnowledgeManifestation,
	 *      KnowledgeEncoding, KnowledgeItem
	 */
	KnowledgeSourceLevel level();

}
