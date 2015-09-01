package hashenvironment;


import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fj.F;
import functional.EquivalenceRelation;
import functional.Functional;
import functional.None;
import functional.Option;
import functional.Some;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.DialectTypeEnvironment;
import api4kbj.KRRDialectType;
import api4kbj.DialectTypeMapping;

public class HashKRRDialectTypeEnvironment implements DialectTypeEnvironment {

	protected final Logger LOG = LoggerFactory.getLogger(getClass());

	public HashKRRDialectTypeEnvironment(HashSet<KRRDialectType<?>> dialectTypes, 
			Option<KRRDialectType<?>> focus, 
			Iterable<DialectTypeMapping<?, ?>> mappings, 
			Option<EquivalenceRelation> preserves, 
			Option<F<BasicKnowledgeExpression, ?>> lowerer) {
		this.dialectTypes = dialectTypes;
		this.focus = focus;
		this.mappings = mappings;
		this.preserves = preserves;
		this.lowerer = lowerer;
	}

	public HashKRRDialectTypeEnvironment(final KRRDialectType<?> dialectType) {
		this(init(dialectType));
	}

	public static Builder init(final KRRDialectType<?> dialectType) {
		Builder builder = new Builder();
		builder.addMembers(dialectType);
		builder.addFocusLanguage(dialectType);
		builder.addPreserves(Functional.ID);
		return builder;
	}

	public static class Builder {
		// mutable properties to configure builder
		private HashSet<KRRDialectType<?>> dialectTypes = new HashSet<KRRDialectType<?>>();
		private HashSet<DialectTypeMapping<?, ?>> mappings = new HashSet<DialectTypeMapping<?, ?>>();
		private Option<KRRDialectType<?>> focus = new None<KRRDialectType<?>>();
		private Option<EquivalenceRelation> preserves = new None<EquivalenceRelation>();
		private Option<F<BasicKnowledgeExpression, ?>> lowerer = new None<F<BasicKnowledgeExpression, ?>>();

		//
		public Builder() {
		}

		public void addMembers(final KRRDialectType<?>... dialectTypes) {
			for (KRRDialectType<?> dialectType : dialectTypes) {
				this.dialectTypes.add(dialectType);
			}
		}

		public void addFocusLanguage(final KRRDialectType<?> dialectType) {
			if (!this.dialectTypes.contains(dialectType)) {
				throw new IllegalArgumentException("The dialect type " + dialectType
						+ " is not contained in the environment.");
			}
			// TODO verify that dialect type is really a focus using OntoIOp
			this.focus = new Some<KRRDialectType<?>>(dialectType);
		}

		public void addPreserves(final EquivalenceRelation preservesValue) {
			this.preserves = new Some<EquivalenceRelation>(preservesValue);
		}

		public void addLowerer(final F<BasicKnowledgeExpression, ?> lowererValue) {
			this.lowerer = new Some<F<BasicKnowledgeExpression, ?>>(lowererValue);
		}

		public void addMappings(
				final Iterable<DialectTypeMapping<?, ?>> mappings) {
			for (DialectTypeMapping<?, ?> map : mappings) {
				addMapping(map);
			}
		}

		public HashKRRDialectTypeEnvironment build() {
			return new HashKRRDialectTypeEnvironment(this) {
			};
		}

		public void addMapping(
				final DialectTypeMapping<?, ?> map) {
			this.mappings.add(map);
			this.dialectTypes.add(map.startType());
			this.dialectTypes.add(map.endType());

		}
	}

	private HashKRRDialectTypeEnvironment(final Builder builder) {
		this.dialectTypes = (HashSet<KRRDialectType<?>>) builder.dialectTypes.clone();
		this.focus = builder.focus.clone();
		this.mappings = (Iterable<DialectTypeMapping<?, ?>>) builder.mappings
				.clone();
		this.preserves = builder.preserves.clone();
		this.lowerer = builder.lowerer.clone();
	}

	private final Option<KRRDialectType<?>> focus;
	// TODO replace HashSets with a graph data structure from some
	// library
	private final HashSet<KRRDialectType<?>> dialectTypes;
	private final Iterable<DialectTypeMapping<?, ?>> mappings;
	private final Option<EquivalenceRelation> preserves;
	private final Option<F<BasicKnowledgeExpression, ?>> lowerer;

	// TODO modify to change return type to immutable collection
	@Override
	public Iterable<KRRDialectType<?>> members() {
		return dialectTypes;
	}

	@Override
	public boolean containsMember(final KRRDialectType<?> dialectType) {
		return dialectTypes.contains(dialectType);
	}

	@Override
	public boolean isFocused() {
		return !focus.isEmpty();
	}

	@Override
	public Option<KRRDialectType<?>> optionalFocusMember() {
		return focus;
	}

	@Override
	public Option<EquivalenceRelation> optionalPreserves() {
		return preserves;
	}

	@Override
	public Iterable<DialectTypeMapping<?, ?>> mappings() {
		return mappings;
	}

	@Override
	public boolean isPreserving() {
		return !preserves.isEmpty();
	}

	@Override
	public boolean isDescending() {
		return !lowerer.isEmpty();
	}
	
	@Override
	public <T> T build(final BasicKnowledgeExpression e, final KRRDialectType<T> dialectType){
		if(!isDescending()){
			return null;
		}
		return (T) apply(lowerer.value().f(e), dialectType);
	}

}
