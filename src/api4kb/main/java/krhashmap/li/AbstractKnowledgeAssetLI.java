package krhashmap.li;

import krconfigured.KnowledgeResourceConfigured;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api4kbj.FocusedImmutableLanguageEnvironment;
import api4kbj.KnowledgeAsset;
import api4kbj.KnowledgeSourceLevel;

public abstract class AbstractKnowledgeAssetLI extends
		AbstractKnowledgeResourceLI implements KnowledgeAsset {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	// lazy initializing constructor - lifting
	protected AbstractKnowledgeAssetLI(
			KnowledgeResourceConfigured initialValue,
			FocusedImmutableLanguageEnvironment env) {
		// call lazy intializing constructor of super class
		super(initialValue, KnowledgeSourceLevel.ASSET, initialValue
				.defaultInput(), initialValue.defaultReceiver(), initialValue
				.defaultCodecSystem(), initialValue.defaultFormatType(),
				initialValue.defaultFormat(),
				initialValue.defaultDialectType(), initialValue
						.defaultDialect(), env, initialValue.defaultLanguage());
		LOG.debug(
				"Starting lazy lifting asset construtor with initial value: {}",
				initialValue);
		// TODO check that environment is compatible with initialValue
		this.environment = env;
	}

	protected final FocusedImmutableLanguageEnvironment environment;

	@Override
	public FocusedImmutableLanguageEnvironment environment() {
		return environment;
	}

}
