package api4kbj;

import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = "http://www.omg.org/spec/API4KB/API4KBTerminology/BasicKnowedgeEncoding")
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