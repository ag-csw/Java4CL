package api4kbj;

import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = 
  "http://www.omg.org/spec/API4KB/API4KBTerminology/Mapping")
public interface Mapping<T, R> {

	Class<? extends T> startClass();

	Class<? extends R> endClass();

	Object function();

	R f(final T arg);

}
