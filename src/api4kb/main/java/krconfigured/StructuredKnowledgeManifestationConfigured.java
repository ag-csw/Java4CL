package krconfigured;

import api4kbj.Decomposable;
import api4kbj.KnowledgeManifestation;
import api4kbj.StructuredKnowledgeManifestation;

public interface StructuredKnowledgeManifestationConfigured<T extends KnowledgeManifestation> extends
		KnowledgeManifestation, StructuredKnowledgeResourceConfigured<T>,
		 StructuredKnowledgeManifestation<T> {

}
