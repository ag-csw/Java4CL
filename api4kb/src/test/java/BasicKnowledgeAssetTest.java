import static org.junit.Assert.*;
import hashenvironment.HashFocusedKRRLanguageEnvironment;
import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.runner.RunWith;

import fj.F;
import api4kbc.API4KB;
import api4kbc.CanonicalBasicKnowledgeAsset;
import api4kbc.FLanguageMapping;
import api4kbj.LanguageMapping;

@RunWith(Parameterized.class)
public class BasicKnowledgeAssetTest {
	public BasicKnowledgeAssetTest(CanonicalBasicKnowledgeAsset asset,
			HashFocusedKRRLanguageEnvironment environment, TestKE expression) {
		this.asset = asset;
		this.environment = environment;
		this.expression = expression;
	}


	public CanonicalBasicKnowledgeAsset asset;
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
		
		F<TestKE1, TestKE2> up = new F<TestKE1, TestKE2>() {

			@Override
			public TestKE2 f(TestKE1 e) {
				return new TestKE2(e.symbol());
			}

		};
		LanguageMapping<TestKE1, TestKE2> upMap = new FLanguageMapping<TestKE1, TestKE2>(
				up, AllTests.lang1, AllTests.lang2);

		F<TestKE2, TestKE1> embed2  = new F<TestKE2, TestKE1>() {

			@Override
			public TestKE1 f(TestKE2 e) {
				return new TestKE1(e.symbol());
			}

		};
		LanguageMapping<TestKE2, TestKE1> embed2Map = new FLanguageMapping<TestKE2, TestKE1>(
				embed2, AllTests.lang2, AllTests.lang1);

		F<TestKE1, TestKE0> down = new F<TestKE1, TestKE0>() {

			@Override
			public TestKE0 f(TestKE1 e) {
				return new TestKE0(e.symbol());
			}

		};
		LanguageMapping<TestKE1, TestKE0> downMap = new FLanguageMapping<TestKE1, TestKE0>(
				down, AllTests.lang1, AllTests.lang0);


		F<TestKE0, TestKE1> embed0  = new F<TestKE0, TestKE1>() {

			@Override
			public TestKE1 f(TestKE0 e) {
				return new TestKE1(e.symbol());
			}

		};
		LanguageMapping<TestKE0, TestKE1> embed0Map = new FLanguageMapping<TestKE0, TestKE1>(
				embed0, AllTests.lang0, AllTests.lang1);


		
		CanonicalBasicKnowledgeAsset asset0 = new CanonicalBasicKnowledgeAsset(AllTests.env0, AllTests.expression0);
		CanonicalBasicKnowledgeAsset asset1 = new CanonicalBasicKnowledgeAsset(AllTests.env1, AllTests.expression1);
		CanonicalBasicKnowledgeAsset asset2 = new CanonicalBasicKnowledgeAsset(AllTests.env2, AllTests.expression2);


		return Arrays.asList(new Object[][] { { asset0, AllTests.env0, AllTests.expression0 },
				{ asset1, AllTests.env1, AllTests.expression1 }, { asset2, AllTests.env2, AllTests.expression2 } });
	}
}
