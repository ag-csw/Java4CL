package api4kbj;

public interface BasicKnowledgeEncoding extends KnowledgeEncoding,
		BasicKnowledgeResource {

	KRRFormat format();

	<S> S build(KRRFormatType<S> formatType);

}
