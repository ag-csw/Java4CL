package api4kbj;

public interface ImmutableDialectTypeEnvironment extends
		ImmutableEnvironment<KRRDialectType<?>, Object> {

	// T - KRRDialectType<?>
	// S = Object
	/*
	 * @Override Iterable<? extends KRRDialectType<?>> members();
	 * 
	 * @Override Iterable<? extends Mapping<?, ?>> mappings();
	 * 
	 * @Override default boolean containsMember(KRRDialectType<?> t){ for
	 * (KRRDialectType<?> member:members()){ if(member.equals(t)){ return true;
	 * } } return false; }
	 * 
	 * @Override default boolean containsMembers(Iterable<? extends
	 * KRRDialectType<?>> t){ for (KRRDialectType<?> s:t){
	 * if(!containsMember(s)){ return false; } } return true; }
	 * 
	 * @Override boolean containsMapping( Mapping<?, ?> t);
	 * 
	 * @Override default boolean containsMappings(Iterable<? extends Mapping<?,
	 * ?>> t){ for (Mapping<?, ?> s:t){ if(!containsMapping(s)){ return false; }
	 * } return true; }
	 * 
	 * @Override boolean isFocused();
	 * 
	 * @Override default <S> Object apply(S arg, KRRDialectType<?> member){ for
	 * ( Mapping<?, ?> mp: mappings()){ Class<?> clazz = mp.startClass();
	 * if(clazz.isAssignableFrom(arg.getClass())){
	 * 
	 * @SuppressWarnings("unchecked") Mapping<S, ?> mp2 = (Mapping<S, ?>) mp; if
	 * (member.asClass().isAssignableFrom(mp2.endClass())){ return mp2.f(arg); }
	 * } } throw new IllegalArgumentException(
	 * "A mapping is not registered to requested target member from the member associated with the input argument"
	 * ); }
	 */
}
