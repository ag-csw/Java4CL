package api4kba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.FocusedLanguageEnvironment;
import api4kbj.KnowledgeAsset;

public abstract class AbstractKnowledgeAsset implements KnowledgeAsset {

	public AbstractKnowledgeAsset(
			FocusedLanguageEnvironment environment) {
		this.environment = environment;
	}

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	protected final FocusedLanguageEnvironment environment;

	@Override
	public FocusedLanguageEnvironment environment() {
		return environment;
	}

}