package api4kb;

public interface KnowledgeResource extends ImmutableSource, KnowledgeSource {
	
	// returns the abstraction level 
	KnowledgeSourceLevel getLevel();

}
