package cl2;

import krhashmap.AbstractBasicKnowledgeEncoding;
import api4kbj.KRRFormat;
import api4kbj.KRRFormatType;
import api4kbj.KnowledgeResourceTemplate;

public abstract class CLEncoding extends AbstractBasicKnowledgeEncoding
		implements CLKnowledgeResource {

	CLEncoding(KnowledgeResourceTemplate template, KRRFormat format) {
		super(template, format);
		LOG.debug("Starting base nonlazy constructor for template: {}",
				template);
	}

	// Wrapper-based constructor
	public <T> CLEncoding(KnowledgeResourceTemplate template, T value,
			KRRFormatType<T> formatType) {
		super(template, value, formatType);
		LOG.debug("Starting wrapper constructor for value: {}", value);
	}

	@Override
	public <T> T build(KRRFormatType<T> formatType) {
		if (mapValue.containsKey(formatType)) {
			return (T) mapValue.get(formatType);
		}
		// TODO implement conversion between dialectTypes
		throw new IllegalArgumentException(
				"Conversion between dialect types is not yet supported.");
	}

}
