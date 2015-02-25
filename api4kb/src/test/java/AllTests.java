import hashenvironment.HashFocusedKRRLanguageEnvironment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import functional.None;
import functional.Option;
import api4kba.AbstractKRRLanguage;
import api4kbj.BasicKnowledgeExpression;
import api4kbj.KRRLanguage;
import api4kbj.KRRLogic;
import api4kbj.KnowledgeExpression;

@RunWith(Suite.class)
@SuiteClasses({ 
    BasicKnowledgeExpressionTest.class,//
    BasicKnowledgeAssetTest.class,//
    BasicKnowledgeManifestationTest.class,//
		HashLanguageEnvironmentTest.class,//
		KnowledgeSourceLevelTest.class, //
		KRRLanguageTest.class })
public class AllTests {

	public static KRRLogic logicA = new KRRLogic() {

		@Override
		public String name() {
			return "Logic A";
		}
	};
	
	public static KRRLogic logicB = new KRRLogic() {

		@Override
		public String name() {
			return "Logic B";
		}
	};
	
	public static AbstractKRRLanguage lang0 = new LangA("Language Zero"){
		@Override
		public Class<? extends KnowledgeExpression> asClass() {
			return TestKE0.class;
		}

	};

	public static AbstractKRRLanguage lang1 = new LangA("Language One"){
		@Override
		public Class<? extends KnowledgeExpression> asClass() {
			return TestKE1.class;
		}

	};

	//KRRLanguage lang2 = new LangB("Language Two");
	public static AbstractKRRLanguage lang2 = new LangA("Language Two"){
		@Override
		public Class<? extends KnowledgeExpression> asClass() {
			return TestKE2.class;
		}

	};
	
	public static HashFocusedKRRLanguageEnvironment env0 = new HashFocusedKRRLanguageEnvironment(AllTests.lang0);
	public static HashFocusedKRRLanguageEnvironment env1 = new HashFocusedKRRLanguageEnvironment(AllTests.lang1);
	public static HashFocusedKRRLanguageEnvironment env2 = new HashFocusedKRRLanguageEnvironment(AllTests.lang2);

	
	public static String str = "Hello World!";
	public static Option<TestKE0> arg0 = new None<TestKE0>();
	public static TestKE0 expression0 = new TestKE0(str, arg0);
	public static TestKE1 expression1 = new TestKE1(str);
	public static TestKE2 expression2 = new TestKE2(str);

	public static String str0 = "hello world!";
	public static String str1 = str;
	public static String str2 = "HELLO WORLD!";

}
class LangA extends AbstractKRRLanguage {

	public LangA(String name) {
		super(name, AllTests.logicA);
	}

}
class LangB extends AbstractKRRLanguage {

	public LangB(String name) {
		super(name, AllTests.logicB);
	}

}
class TestKE0 extends TestKE {

	TestKE0(String value) {
		this(value, new None<TestKE0>());
	}

	TestKE0(String value, Option<TestKE0> arg) {
		super(value.toLowerCase(), AllTests.lang0, arg);
	}


}
class TestKE1 extends TestKE {

	TestKE1(String value) {
		this(value, new None<TestKE1>());
	}

	TestKE1(String value, Option<TestKE1> arg) {
		super(value, AllTests.lang1, arg);
	}


}

class TestKE2 extends TestKE {

	TestKE2(String value) {
		this(value, new None<TestKE2>());
	}

	TestKE2(String value, Option<TestKE2> arg) {
		super(value.toUpperCase(), AllTests.lang2, arg);
	}


}
abstract class TestKE implements BasicKnowledgeExpression {

	private final KRRLanguage lang;
	private final String symbol;
	private final Option<? extends TestKE> arg;

	TestKE(String value, KRRLanguage lang) {
		this(value, lang, new None<TestKE>());
	}
	
	TestKE(String value, KRRLanguage lang, Option<? extends TestKE> arg) {
		this.symbol = value;
		this.lang = lang;
		this.arg = arg;
	}
	
	@Override
	public KRRLanguage language() {
		return lang;
	}


	public String symbol() {
		return symbol;
	}

	public Option<? extends TestKE> arg() {
		return arg;
	}


}