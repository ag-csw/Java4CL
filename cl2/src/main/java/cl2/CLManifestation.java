package cl2;

import api4kbj.KRRDialectType;
import krconfigured.KnowledgeResourceConfiguredTemplate;
import krhashmap.li.mse.AbstractBasicKnowledgeManifestationLIMSE;

public abstract class CLManifestation extends
		AbstractBasicKnowledgeManifestationLIMSE implements CLKnowledgeResource {

	CLManifestation(KnowledgeResourceConfiguredTemplate template) {
		super(template);
		LOG.debug("Starting base nonlazy constructor for template: {}",
				template);
	}

	// Wrapper-based constructor
	public <T> CLManifestation(KnowledgeResourceConfiguredTemplate template,
			T value, KRRDialectType<T> dialectType) {
		super(template, value, dialectType);
		LOG.debug("Starting wrapper constructor for value: {}", value);
	}

	@Override
	public <T> T build(KRRDialectType<T> dialectType) {
		if (mapValue.containsKey(dialectType)) {
			return (T) mapValue.get(dialectType);
		}
		// TODO implement conversion between dialectTypes
		throw new IllegalArgumentException(
				"Conversion between dialect types is not yet supported.");
	}

}
