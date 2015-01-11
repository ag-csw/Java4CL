import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BasicKnowledgeAssetTest.class, BasicKnowledgeExpressionTest.class, 
	KnowledgeSourceLevelTest.class, KRRLanguageTest.class,
	GraphImmutableLanguageEnvironmentTest.class})
public class AllTests {

}
