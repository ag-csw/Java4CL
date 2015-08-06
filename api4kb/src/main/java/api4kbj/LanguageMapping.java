package api4kbj;

import api4kb.doc.annotation.OntologyClass;

@OntologyClass(value = 
  "http://www.omg.org/spec/API4KB/API4KBTerminology/LanguageMapping")
public interface LanguageMapping<T extends KnowledgeExpression, R extends KnowledgeExpression>
		extends Mapping<T, R> {

	KRRLanguage startLanguage();

	KRRLanguage endLanguage();

}
