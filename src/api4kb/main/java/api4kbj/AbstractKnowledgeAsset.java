package api4kbj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKnowledgeAsset implements
		KnowledgeAsset {

	public AbstractKnowledgeAsset(FocusedImmutableEnvironment environment) {
		this.environment = environment;
	}

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	
	
	protected final FocusedImmutableEnvironment environment;



	@Override
	public FocusedImmutableEnvironment environment() {
		return environment;
	}

}