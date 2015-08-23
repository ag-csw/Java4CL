import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import fj.F;
import hashenvironment.HashKRRLanguageEnvironment;
import hashenvironment.HashKRRLanguageEnvironment.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import api4kbc.FJMapping;
import api4kbc.FLanguageMapping;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;
import api4kbj.KnowledgeExpression;
import api4kbj.LanguageMapping;

@RunWith(Parameterized.class)
public class HashLanguageEnvironmentTest {
	public HashLanguageEnvironmentTest(
			HashKRRLanguageEnvironment env,
			BasicKnowledgeExpression expression,
			KRRLanguage langNo,
			KRRLanguage langYes,
			Iterable<? extends KRRLanguage> langSet,
			FJMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> mapNo,
			FJMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> mapYes,
			Iterable<? extends FJMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> mapSetYes,
			Iterable<? extends FJMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> mapSetNo,
			Iterable<? extends FJMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> mapSetAll,
			HashKRRLanguageEnvironment envsup) {
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

	
	public HashKRRLanguageEnvironment env;
	public BasicKnowledgeExpression expression;
	public KRRLanguage langYes;
	public KRRLanguage langNo;
	public Iterable<? extends KRRLanguage> langSet;
	public FJMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> mapNo;
	public FJMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> mapYes;
	public Iterable<? extends FJMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> mapSetYes;
	public Iterable<? extends FJMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> mapSetNo;
	public Iterable<? extends FJMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression>> mapSetAll;
	public HashKRRLanguageEnvironment envsup;

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

	boolean checkDomains(HashKRRLanguageEnvironment env) {
		for (LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> map : env
				.mappings()) {
			if (!env.isCompatibleWithClass(map.startClass())) {
				return false;
			}
		}
		return true;
	}

	boolean checkRanges(HashKRRLanguageEnvironment env) {
		for (LanguageMapping<? extends KnowledgeExpression, ? extends KnowledgeExpression> map : env
				.mappings()) {
			if (!env.isCompatibleWithClass(map.endClass())) {
				return false;
			}
		}
		return true;
	}

	boolean checkFocus(HashKRRLanguageEnvironment env) {
		if (env.isFocused()) {
			KRRLanguage foc = env.optionalFocusMember().value();
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
				return new TestKE2(a.symbol());
			}

		};

		LanguageMapping<TestKE1, TestKE1> idMap1 = new FLanguageMapping<TestKE1, TestKE1>(
				id1, AllTests.lang1, AllTests.lang1);
		LanguageMapping<TestKE2, TestKE2> idMap2 = new FLanguageMapping<TestKE2, TestKE2>(
				id2, AllTests.lang2, AllTests.lang2);

		LanguageMapping<TestKE1, TestKE2> upMap = new FLanguageMapping<TestKE1, TestKE2>(
				up, AllTests.lang1, AllTests.lang2);

		HashKRRLanguageEnvironment env = new HashKRRLanguageEnvironment(
				AllTests.lang1);

		HashSet<KRRLanguage> langSet1 = new HashSet<KRRLanguage>();
		langSet1.add(AllTests.lang1);
		HashSet<KRRLanguage> langSet2 = new HashSet<KRRLanguage>();
		langSet2.add(AllTests.lang1);
		langSet2.add(AllTests.lang2);

		Builder builder = HashKRRLanguageEnvironment.init(AllTests.lang1);
		builder.addMapping(idMap1);
		HashKRRLanguageEnvironment env1 = builder.build();

		builder.addMapping(upMap);
		builder.addFocusLanguage(AllTests.lang2);
		HashKRRLanguageEnvironment env2 = builder.build();

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
				{ env, expression, AllTests.lang2, AllTests.lang1, langSet1, idMap2, idMap1,
						mapSet3, mapSet2, mapSet3, env1 },
				{ env1, expression, AllTests.lang2, AllTests.lang1, langSet1, idMap2, idMap1,
						mapSet1, mapSet2, mapSet1, env2 },
				{ env2, expression2, null, AllTests.lang1, langSet2, idMap2, upMap,
						mapSet2, mapSet4, mapSet2, env2 } }

		);
	}

}
