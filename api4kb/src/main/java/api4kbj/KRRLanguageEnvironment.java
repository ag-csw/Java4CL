package api4kbj;

/**
 * Interface for immutable environments of KRR languages.
 * 
 * @author taraathan
 *
 */
public interface KRRLanguageEnvironment extends
		Environment<KRRLanguage, KnowledgeExpression> {

	/*
	 * @Override default boolean isCompatibleWith(KnowledgeExpression arg) { if
	 * (arg == null){ return false; } if (arg instanceof
	 * BasicKnowledgeExpression) { final BasicKnowledgeExpression barg =
	 * (BasicKnowledgeExpression) arg; return containsMember(barg.language()); }
	 * if (arg instanceof StructuredKnowledgeExpression) { final
	 * StructuredKnowledgeExpression sarg = (StructuredKnowledgeExpression) arg;
	 * return containsMembers(sarg.languages()); } return false; }
	 */

}
