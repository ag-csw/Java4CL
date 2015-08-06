package api4kbj;

import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = "http://www.omg.org/spec/API4KB/API4KBTerminology/KnowledgeResource")
public interface KnowledgeResource extends ImmutableSource, KnowledgeSource, KnowledgeResourceLike {

}
