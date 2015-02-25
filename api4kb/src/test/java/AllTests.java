import hashenvironment.HashFocusedKRRLanguageEnvironment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import functional.None;
import functional.Option;
import functional.Some;
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
	public static TestKE1 expression1a = new TestKE1("Foo");
	public static Option<TestKE1> arg1 = new Some<TestKE1>(expression1a);
	public static TestKE1 expression1 = new TestKE1(str, arg1);
	public static TestKE2 expression2a = new TestKE2("Foo");
	public static Option<TestKE2> arg2b = new Some<TestKE2>(expression2a);
	public static TestKE2 expression2b = new TestKE2("Bah", arg2b);
	public static Option<TestKE2> arg2 = new Some<TestKE2>(expression2b);
	public static TestKE2 expression2 = new TestKE2(str, arg2);

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arg == null) ? 0 : arg.hashCode());
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestKE other = (TestKE) obj;
		if (arg == null) {
			if (other.arg != null)
				return false;
		} else if (!arg.equals(other.arg))
			return false;
		if (lang == null) {
			if (other.lang != null)
				return false;
		} else if (!lang.equals(other.lang))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}


}