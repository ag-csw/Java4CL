package krconfigured;

import api4kbj.KRRFormat;
import api4kbj.KRRFormatType;
import api4kbj.KnowledgeEncoding;

public interface BasicKnowledgeEncodingConfigured extends KnowledgeEncoding,
		BasicKnowledgeResourceConfigured {

	KRRFormat format();

	<S> S build(KRRFormatType<S> formatType);

}
