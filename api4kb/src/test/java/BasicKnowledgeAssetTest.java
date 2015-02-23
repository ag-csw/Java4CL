import static org.junit.Assert.*;
import hashenvironment.HashFocusedKRRLanguageEnvironment;
import hashenvironment.HashKRRLanguageEnvironment;
import hashenvironment.HashKRRLanguageEnvironment.Builder;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.runner.RunWith;

import fj.F;
import api4kba.AbstractBasicKnowledgeExpression;
import api4kba.AbstractKRRLanguage;
import api4kbc.API4KB;
import api4kbc.BasicKnowledgeAssetCanonical;
import api4kbc.FLanguageMapping;
import api4kbj.BasicKnowledgeAsset;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.FocusedLanguageEnvironment;
import api4kbj.KRRLanguage;
import api4kbj.KRRLogic;
import api4kbj.KnowledgeExpression;
import api4kbj.LanguageMapping;

@RunWith(Parameterized.class)
public class BasicKnowledgeAssetTest {
	public BasicKnowledgeAssetTest(BasicKnowledgeAssetCanonical asset,
			HashFocusedKRRLanguageEnvironment environment, TestKE expression) {
		this.asset = asset;
		this.environment = environment;
		this.expression = expression;
	}


	public BasicKnowledgeAssetCanonical asset;
	public HashFocusedKRRLanguageEnvironment environment;
	public TestKE expression;

	@Test
	public void assetShouldBeBasic() {
		assertTrue(asset.isBasic());
	}

	@Test
	public void assetEnvironmentShouldBeAsConstructed() {
		assertEquals(asset.environment(), environment);
	}

	@Test
	public void assetExpressionShouldBeAsConstructed() {
		assertEquals(asset.canonicalExpression(), expression);
	}

	@Test
	public void assetAndItsEnvironmentShouldSatisfyAccordingToRelation() {
		assertTrue(API4KB.accordingTo(asset, asset.environment()));
		assertTrue(asset.accordingTo(asset.environment()));
	}

	@Parameterized.Parameters
	public static Collection<Object[]> instancesToTest() {
		KRRLogic logicA = new KRRLogic() {

			@Override
			public String name() {
				return "Logic A";
			}
		};
		class LangA extends AbstractKRRLanguage {

			public LangA(String name) {
				super(name, logicA);
			}

		}

		KRRLogic logicB = new KRRLogic() {

			@Override
			public String name() {
				return "Logic B";
			}
		};
		class LangB extends AbstractKRRLanguage {

			public LangB(String name) {
				super(name, logicB);
			}

		}

		LangA lang0 = new LangA("Language Zero");
		class TestKE0 extends TestKE {

			TestKE0(String value) {
				super(value.toLowerCase(), lang0);
			}

		}
		lang0.setClass(TestKE0.class);


		AbstractKRRLanguage lang1 = new LangA("Language One");
		class TestKE1 extends TestKE {

			TestKE1(String value) {
				super(value, lang1);
			}

		}
		lang1.setClass(TestKE1.class);

		AbstractKRRLanguage lang2 = new LangB("Language Two");
		class TestKE2 extends TestKE {

			TestKE2(String value) {
				super(value.toUpperCase(), lang2);
			}

		}
		lang2.setClass(TestKE2.class);
		
		F<TestKE1, TestKE2> up = new F<TestKE1, TestKE2>() {

			@Override
			public TestKE2 f(TestKE1 e) {
				return new TestKE2(e.symbol());
			}

		};
		LanguageMapping<TestKE1, TestKE2> upMap = new FLanguageMapping<TestKE1, TestKE2>(
				up, lang1, lang2);

		F<TestKE2, TestKE1> embed2  = new F<TestKE2, TestKE1>() {

			@Override
			public TestKE1 f(TestKE2 e) {
				return new TestKE1(e.symbol());
			}

		};
		LanguageMapping<TestKE2, TestKE1> embed2Map = new FLanguageMapping<TestKE2, TestKE1>(
				embed2, lang2, lang1);

		F<TestKE1, TestKE0> down = new F<TestKE1, TestKE0>() {

			@Override
			public TestKE0 f(TestKE1 e) {
				return new TestKE0(e.symbol());
			}

		};
		LanguageMapping<TestKE1, TestKE0> downMap = new FLanguageMapping<TestKE1, TestKE0>(
				down, lang1, lang0);


		F<TestKE0, TestKE1> embed0  = new F<TestKE0, TestKE1>() {

			@Override
			public TestKE1 f(TestKE0 e) {
				return new TestKE1(e.symbol());
			}

		};
		LanguageMapping<TestKE0, TestKE1> embed0Map = new FLanguageMapping<TestKE0, TestKE1>(
				embed0, lang0, lang1);

		HashFocusedKRRLanguageEnvironment env0 = new HashFocusedKRRLanguageEnvironment(lang0);
		HashFocusedKRRLanguageEnvironment env1 = new HashFocusedKRRLanguageEnvironment(lang1);
		HashFocusedKRRLanguageEnvironment env2 = new HashFocusedKRRLanguageEnvironment(lang2);

        String str = "Hello World!";
        TestKE0 expression0 = new TestKE0(str);
        TestKE1 expression1 = new TestKE1(str);
        TestKE2 expression2 = new TestKE2(str);

		
		BasicKnowledgeAssetCanonical asset0 = new BasicKnowledgeAssetCanonical(env0, expression0);
		BasicKnowledgeAssetCanonical asset1 = new BasicKnowledgeAssetCanonical(env1, expression1);
		BasicKnowledgeAssetCanonical asset2 = new BasicKnowledgeAssetCanonical(env2, expression2);


		return Arrays.asList(new Object[][] { { asset0, env0, expression0 },
				{ asset1, env1, expression1 }, { asset2, env2, expression2 } });
	}
}
