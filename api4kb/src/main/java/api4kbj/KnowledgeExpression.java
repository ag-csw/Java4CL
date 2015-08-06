package api4kbj;

import elevation.Liftable;
import elevation.Lowerable;
import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = "http://www.omg.org/spec/API4KB/API4KBTerminology/KnowledgeExpression")
public interface KnowledgeExpression extends KnowledgeResource, KnowledgeExpressionLike, Liftable,
Lowerable {


}
