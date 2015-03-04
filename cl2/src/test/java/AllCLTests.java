import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cl2.CLSentenceOrStatementOrText;
import cl2.FJCLTextConstruction;
import fj.F;
import fj.data.List;

@RunWith(Suite.class)
@SuiteClasses({ CLTest.class,
	BasicKnowledgeAssetTest.class })
public class AllCLTests {

	public static final CLSentenceOrStatementOrText expression0 = FJCLTextConstruction.empty();
	public static final FJCLTextConstruction<CLSentenceOrStatementOrText> singleton0 = FJCLTextConstruction.unit(expression0);
	public static final List<CLSentenceOrStatementOrText> expressions0 = List.list(expression0, expression0);
	public static final FJCLTextConstruction<CLSentenceOrStatementOrText> text0 = FJCLTextConstruction.text(expressions0);
	public static final F<CLSentenceOrStatementOrText, FJCLTextConstruction<CLSentenceOrStatementOrText>> G0 = s -> FJCLTextConstruction.unit(s);
	public static final F<FJCLTextConstruction<CLSentenceOrStatementOrText>, FJCLTextConstruction<FJCLTextConstruction<CLSentenceOrStatementOrText>>> H0 = s -> FJCLTextConstruction.unit(s);

}
