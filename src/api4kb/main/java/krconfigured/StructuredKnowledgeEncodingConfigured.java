package krconfigured;

import api4kbj.KnowledgeEncoding;
import api4kbj.StructuredKnowledgeEncoding;

public interface StructuredKnowledgeEncodingConfigured<T extends KnowledgeEncoding> extends
		StructuredKnowledgeResourceConfigured<T>, StructuredKnowledgeEncoding<T> {

}
