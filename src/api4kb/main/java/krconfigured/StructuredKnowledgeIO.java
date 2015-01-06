package krconfigured;

import api4kbj.Decomposable;
import api4kbj.KnowledgeIO;

public interface StructuredKnowledgeIO extends KnowledgeIO,
		StructuredKnowledgeResource, Decomposable<KnowledgeIO> {

}