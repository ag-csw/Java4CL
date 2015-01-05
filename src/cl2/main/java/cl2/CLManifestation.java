package cl2;

import api4kbj.KRRDialectType;
import api4kbj.KnowledgeResourceTemplate;
import krhashmap.AbstractBasicKnowledgeManifestation;

public abstract class CLManifestation extends
		AbstractBasicKnowledgeManifestation implements CLKnowledgeResource {

	CLManifestation(KnowledgeResourceTemplate template) {
		super(template);
		LOG.debug("Starting base nonlazy constructor for template: {}",
				template);
	}

	// Wrapper-based constructor
	public <T> CLManifestation(KnowledgeResourceTemplate template, T value,
			KRRDialectType<T> dialectType) {
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
