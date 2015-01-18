package api4kbj;

/**
 * Interface for immutable environments of KRR languages.
 * 
 * @author taraathan
 *
 */
public interface ImmutableLanguageEnvironment extends
		ImmutableEnvironment<KRRLanguage, KnowledgeExpression> {

	/*
	 * @Override Iterable<LanguageMapping<? extends KnowledgeExpression,?
	 * extends KnowledgeExpression>> mappings();
	 */

	/*
	 * @Override default boolean containsMapping( Mapping<? extends
	 * KnowledgeExpression, ? extends KnowledgeExpression> t) { for
	 * (LanguageMapping<? extends KnowledgeExpression, ? extends
	 * KnowledgeExpression> map : mappings()) { if (map.equals(t)) { return
	 * true; } } return false; }
	 */

	@Override
	default boolean isCompatibleWith(KnowledgeExpression arg) {
		if (arg instanceof BasicKnowledgeExpression) {
			final BasicKnowledgeExpression barg = (BasicKnowledgeExpression) arg;
			return containsMember(barg.language());
		}
		if (arg instanceof StructuredKnowledgeExpression) {
			final StructuredKnowledgeExpression sarg = (StructuredKnowledgeExpression) arg;
			return containsMembers(sarg.languages());
		}
		return false;
	}

	/*
	 * @Override default <S extends KnowledgeExpression> KnowledgeExpression
	 * apply(S arg, KRRLanguage member) { // TODO this default simply takes the
	 * first mapping from the iterable where the // domain and range match what
	 * is requested. // There is no allowance for composition, so the iterable
	 * must range over the compositions as well. // This is a brute force
	 * approach. for (LanguageMapping<? extends KnowledgeExpression,? extends
	 * KnowledgeExpression> mp: mappings()){ Class<? extends
	 * KnowledgeExpression> clazz = mp.startClass();
	 * SLOG.debug("Start class of the mapping: {}", clazz);
	 * SLOG.debug("Class of the argument: {}", arg.getClass());
	 * SLOG.debug("Conditional Comparison: {}",
	 * clazz.isAssignableFrom(arg.getClass()));
	 * if(clazz.isAssignableFrom(arg.getClass())){
	 * SLOG.debug("isAssignableFrom: {}", arg.getClass());
	 * 
	 * @SuppressWarnings("unchecked") Mapping<S, ? extends KnowledgeExpression>
	 * mp2 = (LanguageMapping<S, ? extends KnowledgeExpression>) mp; if
	 * (member.asClass().isAssignableFrom(mp2.endClass())){ return mp2.f(arg); }
	 * } } throw new IllegalArgumentException(
	 * "A mapping is not registered to requested target member from the member associated with the input argument"
	 * ); }
	 */

}
