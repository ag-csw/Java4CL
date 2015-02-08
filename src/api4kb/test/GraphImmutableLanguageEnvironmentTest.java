import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import fj.F;
import graphenvironment.GraphImmutableLanguageEnvironment;
import graphenvironment.GraphImmutableLanguageEnvironment.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeExpression;
import api4kbj.LanguageMapping;
import api4kbj.Mapping;
//import api4kbj7.IKRRLanguage;
import api4kbj7.IKRRLogic;
//import api4kbj7.IKnowledgeExpression;
//import api4kbj7.IMapping;
import api4kbja.AbstractKRRLanguage;

@RunWith(Parameterized.class)
public class GraphImmutableLanguageEnvironmentTest {
	public GraphImmutableLanguageEnvironmentTest(
			GraphImmutableLanguageEnvironment env,
			BasicKnowledgeExpression expression,
			KRRLanguage langNo,
			KRRLanguage langYes,
			Iterable<? extends KRRLanguage> langSet,
			Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> mapNo,
			Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> mapYes,
			Iterable<? extends Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> mapSetYes,
			Iterable<? extends Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> mapSetNo,
			Iterable<? extends Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> mapSetAll,
			GraphImmutableLanguageEnvironment envsup) {
		this.env = env;
		this.expression = expression;
		this.langYes = langYes;
		this.langNo = langNo;
		this.langSet = langSet;
		this.mapNo = mapNo;
		this.mapYes = mapYes;
		this.mapSetYes = mapSetYes;
		this.mapSetNo = mapSetNo;
		this.mapSetAll = mapSetAll;
		this.envsup = envsup;
	}

	public GraphImmutableLanguageEnvironment env;
	public BasicKnowledgeExpression expression;
	public KRRLanguage langYes;
	public KRRLanguage langNo;
	public Iterable<? extends KRRLanguage> langSet;
	public Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> mapNo;
	public Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> mapYes;
	public Iterable<? extends Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> mapSetYes;
	public Iterable<? extends Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> mapSetNo;
	public Iterable<? extends Mapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> mapSetAll;
	public GraphImmutableLanguageEnvironment envsup;

	@Test
	public void testTest() {
		assertTrue(true);
	}

	@Test
	public void MemberContainmentShouldBeFalse() {
		assertTrue(
				"Member containment test method should return true if the member is contained in the structure.",
				!env.containsMember(langNo));
	}

	@Test
	public void MemberContainmentShouldBeTrue() {
		assertTrue(
				"Member containment test method should return true if the member is contained in the structure.",
				env.containsMember(langYes));
	}

	@Test
	public void MembersContainmentShouldBeTrue() {

		assertTrue(
				"Members containment test method should return true if all of the members are contained in the structure.",
				env.containsMembers(langSet));
	}

	@Test
	public void MappingContainmentShouldBeFalse() {

		assertFalse(
				"Mapping containment test method should return true only if the mapping is contained in the structure.",
				env.containsMapping(mapNo));
	}

	@Test
	public void MappingContainmentShouldBeTrue() {
		assertTrue(
				"Mapping containment test method should return true if the mapping is contained in the structure.",
				env.containsMapping(mapYes)
						|| !env.mappings().iterator().hasNext());
	}

	@Test
	public void AccessorMethodShouldReturnMembersExactly() {
		assertEquals(
				"Accessor methods should describe what members are in the structure.",
				langSet, env.members());

	}

	@Test
	public void MappingsContainmentShouldBeFalse() {
		assertFalse(
				"Mapping containment test method should return true only if all of the mappings are contained in the structure.",
				env.containsMappings(mapSetNo));

	}

	@Test
	public void MappingsContainmentShouldBeTrue() {
		assertTrue(
				"Mapping containment test method should return true if all of the mappings are contained in the structure.",
				env.containsMappings(mapSetYes));

	}

	@Test
	public void AccessorMethodShouldReturnMappingsExactly() {
		assertEquals(
				"Accessor methods should describe what mappings are in the structure.",
				mapSetAll, env.mappings());

	}

	@Test
	public void StartEndClassesShouldBeCompatible() {
		assertTrue(
				"The start and end classes of each mapping should be compatible with the environment.).",

				checkDomains(env) && checkRanges(env)

		);

	}

	@Test
	public void EnvironmentWithFocusEnabledShouldBeFocused() {
		assertTrue(
				"If the environment is focused, it should contain a mapping from each member to the focus member.",
				checkFocus(env));

	}

	@Test
	public void ExpressionShouldBeCompatible() {
		assertTrue(
				"Compatibility tests for objects should return true if and only if the object is an instance of of the class of some member.",
				env.isCompatibleWith(expression));

	}

	@Test
	public void MappingToArgumentsLanguageShouldReturnArgument() {
		assertEquals(
				"If the argument is an instance of the class of the target ClassWrapper, then the apply method should return the original argument.",
				expression, env.apply(expression, expression.language()));

	}

	@Test
	public void EnvironmentShouldContainSubEnvironment() {
		assertTrue(
				"Two environments may be compared according to containment of their collections of mappings and members.",
				envsup.contains(env));

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

	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {

		IKRRLogic logic = new IKRRLogic() {

			@Override
			public String name() {
				return "Logic A";
			}
		};

		class LangA extends AbstractKRRLanguage {

			public LangA(String name) {
				super(name, logic);
			}

		}

		KRRLanguage lang1 = new LangA("Language One");
		KRRLanguage lang2 = new LangA("Language Two");

		class TestKE1 implements BasicKnowledgeExpression {

			private final String value;

			TestKE1(String value) {
				this.value = value.toLowerCase();
			}

			@Override
			public KRRLanguage language() {
				return lang1;
			}

		}
		class TestKE2 implements BasicKnowledgeExpression {

			private final String value;

			TestKE2(String value) {
				this.value = value.toUpperCase();
			}

			@Override
			public KRRLanguage language() {
				return lang2;
			}

		}

		class AbstractLanguageMapping<S extends KnowledgeExpression, T extends KnowledgeExpression>
				implements LanguageMapping<S, T> {

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

		lang2.setClass(TestKE2.class);
		lang1.setClass(TestKE1.class);

		F<TestKE1, TestKE1> id1 = new F<TestKE1, TestKE1>() {

			@Override
			public TestKE1 f(TestKE1 a) {
				return a;
			}
		};

		F<TestKE2, TestKE2> id2 = new F<TestKE2, TestKE2>() {

			@Override
			public TestKE2 f(TestKE2 a) {
				return a;
			}
		};

		F<TestKE1, TestKE2> up = new F<TestKE1, TestKE2>() {

			@Override
			public TestKE2 f(TestKE1 a) {
				return new TestKE2(a.value);
			}

		};

		LanguageMapping<TestKE1, TestKE1> idMap1 = new AbstractLanguageMapping<TestKE1, TestKE1>(
				id1, lang1, lang1) {

		};
		LanguageMapping<TestKE2, TestKE2> idMap2 = new AbstractLanguageMapping<TestKE2, TestKE2>(
				id2, lang2, lang2) {

		};

		LanguageMapping<TestKE1, TestKE2> upMap = new AbstractLanguageMapping<TestKE1, TestKE2>(
				up, lang1, lang2) {

		};

		GraphImmutableLanguageEnvironment env = new GraphImmutableLanguageEnvironment(
				lang1);

		HashSet<KRRLanguage> langSet1 = new HashSet<KRRLanguage>();
		langSet1.add(lang1);
		HashSet<KRRLanguage> langSet2 = new HashSet<KRRLanguage>();
		langSet2.add(lang1);
		langSet2.add(lang2);

		Builder builder = GraphImmutableLanguageEnvironment.init(lang1);
		builder.addMapping(idMap1);
		GraphImmutableLanguageEnvironment env1 = builder.build();

		builder.addMapping(upMap);
		builder.addFocusLanguage(lang2);
		GraphImmutableLanguageEnvironment env2 = builder.build();

		HashSet<LanguageMapping<?, ?>> mapSet1 = new HashSet<LanguageMapping<?, ?>>();
		mapSet1.add(idMap1);

		HashSet<LanguageMapping<?, ?>> mapSet2 = new HashSet<LanguageMapping<?, ?>>();
		mapSet2.add(idMap1);
		mapSet2.add(upMap);

		HashSet<LanguageMapping<?, ?>> mapSet3 = new HashSet<LanguageMapping<?, ?>>();
		HashSet<LanguageMapping<?, ?>> mapSet4 = new HashSet<LanguageMapping<?, ?>>();
		mapSet4.add(idMap2);

		TestKE1 expression = new TestKE1("") {
		};
		TestKE2 expression2 = new TestKE2("test") {
		};

		return Arrays.asList(new Object[][] {
				{ env, expression, lang2, lang1, langSet1, idMap2, idMap1,
						mapSet3, mapSet2, mapSet3, env1 },
				{ env1, expression, lang2, lang1, langSet1, idMap2, idMap1,
						mapSet1, mapSet2, mapSet1, env2 },
				{ env2, expression2, null, lang1, langSet2, idMap2, upMap,
						mapSet2, mapSet4, mapSet2, env2 } }

		);
	}

}
