package api4kbj;

import krconfigured.BasicKnowledgeResourceConfigured;

public interface BasicKnowledgeEncoding extends KnowledgeEncoding,
		BasicKnowledgeResourceConfigured {

	KRRFormat format();

	<S> S build(KRRFormatType<S> formatType);

}
