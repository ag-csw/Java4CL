package api4kbj;

import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = "http://www.omg.org/spec/API4KB/API4KBTerminology/Basic")
public interface Basic extends SourceLike {

	@Override
	default boolean isBasic() {
		return true;
	}

}
