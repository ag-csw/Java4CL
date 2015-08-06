package api4kbj;

import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = 
  "http://www.omg.org/spec/API4KB/API4KBTerminology/MutableSource")
public interface MutableSource extends Mutable<ImmutableSource>, Source {

}
