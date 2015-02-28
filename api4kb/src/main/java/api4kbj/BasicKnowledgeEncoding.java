package api4kbj;

public interface BasicKnowledgeEncoding extends KnowledgeEncoding,
		BasicKnowledgeResource {

	KRRFormat format();

	@Override
	default boolean usesFormat(final KRRFormat format) {
		return format().equals(format);
	}

	<S> S build(final KRRFormatType<S> formatType);


	byte[] toByteArray();

}