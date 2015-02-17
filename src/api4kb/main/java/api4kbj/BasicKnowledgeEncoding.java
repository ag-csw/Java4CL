package api4kbj;

public interface BasicKnowledgeEncoding extends KnowledgeEncoding,
		BasicKnowledgeResource {

	KRRFormat format();

	@Override
	default boolean usesFormat(KRRFormat format) {
		return format().equals(format);
	}

	<S> S build(KRRFormatType<S> formatType);

}