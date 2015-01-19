import static org.junit.Assert.*;

import java.util.HashSet;

import fj.F;
import graphenvironment.GraphImmutableLanguageEnvironment;
import graphenvironment.GraphImmutableLanguageEnvironment.*;

import org.junit.Test;

import api4kbj.AbstractKRRLanguage;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;
import api4kbj.KRRLogic;
import api4kbj.KnowledgeExpression;
import api4kbj.LanguageMapping;

public class GraphImmutableLanguageEnvironmentTest {

	KRRLogic logic = new KRRLogic() {

		@Override
		public String name() {
			return "Logic A";
		}
	};
	
	class LangA extends AbstractKRRLanguage{

		public LangA(String name) {
			super(name, logic);
		}
		
	}

	KRRLanguage lang1 = new LangA("Language One");
	KRRLanguage lang2 = new LangA("Language Two");
	
	class TestKE1 implements BasicKnowledgeExpression {
		
		private final String value;

		TestKE1(String value){
			this.value = value.toUpperCase();
		}

		@Override
		public KRRLanguage language() {
			return lang1;
		}

	}

	class TestKE2 implements BasicKnowledgeExpression {
		
		private final String value;

		TestKE2(String value){
			this.value = value.toUpperCase();
		}

		@Override
		public KRRLanguage language() {
			return lang2;
		}

	}

	class AbstractLanguageMapping<S extends KnowledgeExpression, T extends KnowledgeExpression> 
	  implements LanguageMapping<S, T>{

		public AbstractLanguageMapping(F<S, T> function,
				KRRLanguage startLang, KRRLanguage endLang) {
			Function = function;
			this.startLang = startLang;
			this.endLang = endLang;
		}

		private F<S, T> Function;
		private KRRLanguage startLang;
		private KRRLanguage endLang;

		@Override
		public Class<? extends S> startClass() {
			return (Class<? extends S>) startLanguage().asClass();
		}

		@Override
		public Class<? extends T> endClass() {
			return (Class<? extends T>) endLanguage().asClass();
		}

		@Override
		public F<S, T> function() {
			return Function;
		}

		@Override
		public KRRLanguage startLanguage() {
			return startLang;
		}

		@Override
		public KRRLanguage endLanguage() {
			return endLang;
		}
		
	}


	@Test
	public void test() {

		lang1.setClass(TestKE1.class);
		lang2.setClass(TestKE2.class);

		F<TestKE1, TestKE1> id1 = new F<TestKE1, TestKE1>(){

			@Override
			public TestKE1 f(TestKE1 a) {
				return a;
			}
		};
			


		F<TestKE2, TestKE2> id2 = new F<TestKE2, TestKE2>(){

			@Override
			public TestKE2 f(TestKE2 a) {
				return a;
			}
		};
			
		F<TestKE1, TestKE2> up = new F<TestKE1, TestKE2>(){

			@Override
			public TestKE2 f(TestKE1 a) {
				return new TestKE2(a.value);
			}
			
		};

		LanguageMapping<TestKE1, TestKE1> idMap1 = 
				new AbstractLanguageMapping<TestKE1, TestKE1>(id1 , lang1, lang1) {

		};
		LanguageMapping<TestKE2, TestKE2> idMap2 = 
				new AbstractLanguageMapping<TestKE2, TestKE2>(id2 , lang2, lang2) {

		};

		LanguageMapping<TestKE1, TestKE2> upMap = 
				new AbstractLanguageMapping<TestKE1, TestKE2>(up , lang1, lang2) {

		};

		GraphImmutableLanguageEnvironment env = new GraphImmutableLanguageEnvironment(
				lang1);

		assertTrue(
				"Member containment test method should return true if the member is contained in the structure.",
				env.containsMember(lang1));

		HashSet<KRRLanguage> langSet = new HashSet<KRRLanguage>();
		langSet.add(lang1);
		HashSet<KRRLanguage> langSet2 = new HashSet<KRRLanguage>();
		langSet2.add(lang1);
		langSet2.add(lang2);

		assertTrue(
				"Member containment test method should return true if all of the members are contained in the structure.",
				env.containsMembers(langSet));

		Builder builder = GraphImmutableLanguageEnvironment.init(lang1);
		builder.addMapping(idMap1);
		GraphImmutableLanguageEnvironment env1 = builder.build();
		
		builder.addMapping(upMap);
		builder.addFocusLanguage(lang2);
		GraphImmutableLanguageEnvironment env2 = builder.build();

		assertFalse(
				"Mapping containment test method should return true if the mapping is contained in the structure.",
				env.containsMapping(idMap1));
		assertTrue(
				"Mapping containment test method should return true if the mapping is contained in the structure.",
				env1.containsMapping(idMap1));
		assertTrue(
				"Mapping containment test method should return true if the mapping is contained in the structure.",
				env2.containsMapping(idMap1));
		assertTrue(
				"Mapping containment test method should return true if the mapping is contained in the structure.",
				env2.containsMapping(upMap));
		assertEquals(
				"Accessor methods should describe what members are in the structure.",
				(Iterable<KRRLanguage>) langSet, env.members());
		assertEquals(
				"Accessor methods should describe what members are in the structure.",
				(Iterable<KRRLanguage>) langSet, env1.members());
		assertEquals(
				"Accessor methods should describe what members are in the structure.",
				(Iterable<KRRLanguage>) langSet2, env2.members());

		HashSet<LanguageMapping<?, ?>> mapSet = new HashSet<LanguageMapping<?, ?>>();
		mapSet.add(idMap1);

		HashSet<LanguageMapping<?, ?>> mapSet2 = new HashSet<LanguageMapping<?, ?>>();
		mapSet2.add(idMap1);
		mapSet2.add(upMap);

		assertFalse(
				"Mapping containment test method should return true only if all of the mappings are contained in the structure.",
				env.containsMappings(mapSet));
		assertTrue(
				"Mapping containment test method should return true if all of the mappings are contained in the structure.",
				env1.containsMappings(mapSet));
		assertTrue(
				"Mapping containment test method should return true if all of the mappings are contained in the structure.",
				env2.containsMappings(mapSet));
		assertFalse(
				"Mapping containment test method should return true if all of the mappings are contained in the structure.",
				env1.containsMappings(mapSet2));
		assertTrue(
				"Mapping containment test method should return true if all of the mappings are contained in the structure.",
				env2.containsMappings(mapSet2));

		assertEquals(
				"Accessor methods should describe what mappings are in the structure.",
				(Iterable<LanguageMapping<?, ?>>) mapSet2, env2.mappings());

		assertTrue(
				"The start and end classes of each mapping should be compatible with the environment.).",

				checkDomains(env2) && checkRanges(env2)

		);

		assertTrue(
				"If the environment is focused, it should contain a mapping from each member to the focus member.",
				checkFocus(env));
		assertTrue(
				"If the environment is focused, it should contain a mapping from each non-focus member to the focus member.",
				checkFocus(env2));

		TestKE1 expression = new TestKE1("") {};

		assertTrue(
				"Compatibility tests for objects should return true if and only if the object is an instance of of the class of some member.",
				env.isCompatibleWith(expression));

		assertEquals("If the argument is an instance of the class of the target ClassWrapper, then the apply method should return the original argument.",
				expression, env.apply(expression, lang1));
		assertEquals("If the argument is an instance of the class of the target ClassWrapper, then the apply method should return the original argument.",
				expression, env2.apply(expression, lang1));

		assertTrue("Two environments may be compared according to containment of their collections of mappings and members.", 
				env2.contains(env));

	}

	boolean checkDomains(GraphImmutableLanguageEnvironment env) {
		for (LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> map : env
				.mappings()) {
			if (!env.isCompatibleWithClass(map.startClass())) {
				return false;
			}
		}
		return true;
	}

	boolean checkRanges(GraphImmutableLanguageEnvironment env) {
		for (LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> map : env
				.mappings()) {
			if (!env.isCompatibleWithClass(map.endClass())) {
				return false;
			}
		}
		return true;
	}

	boolean checkFocus(GraphImmutableLanguageEnvironment env) {
		if (env.isFocused()) {
			KRRLanguage foc = env.focusMember().value();
			for (KRRLanguage member : env.members()) {
				if (!(member.equals(foc))
						&& (env.findMapping(member.asClass(), foc.asClass())
								.isEmpty())) {
					return false;
				}

			}
		}
		return true;

	}

}
