import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
    BasicKnowledgeExpressionTest.class,//
    BasicKnowledgeAssetTest.class,//
    BasicKnowledgeManifestationTest.class,//
		HashLanguageEnvironmentTest.class,//
		KnowledgeSourceLevelTest.class, //
		KRRLanguageTest.class })
public class AllTests {

}
