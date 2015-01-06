package krconfigured;

import api4kbj.Decomposable;
import api4kbj.KnowledgeManifestation;
import api4kbj.StructuredKnowledgeManifestation;

public interface StructuredKnowledgeManifestationConfigured extends
		KnowledgeManifestation, StructuredKnowledgeResourceConfigured,
		Decomposable<KnowledgeManifestation>, StructuredKnowledgeManifestation {

}
