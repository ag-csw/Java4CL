import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cl2.CLExpression;
import cl2.FJCLTextConstruction;
import fj.F;
import fj.data.List;

@RunWith(Suite.class)
@SuiteClasses({ CLTest.class,
	//FJCLTextConstructionTest.class,
	//BasicKnowledgeAssetTest.class 
	})
public class AllCLTests {

	//public static final CLExpression expression0 = FJCLTextConstruction.empty();
	//public static final FJCLTextConstruction<CLExpression> singleton0 = FJCLTextConstruction.unit(expression0);
	//public static final List<CLExpression> expressions0 = List.list(expression0, expression0);
	//public static final FJCLTextConstruction<CLExpression> text0 = FJCLTextConstruction.text(expressions0);
	//public static final F<CLExpression, FJCLTextConstruction<CLExpression>> G0 = s -> FJCLTextConstruction.unit(s);
	//public static final F<FJCLTextConstruction<CLExpression>, FJCLTextConstruction<FJCLTextConstruction<CLExpression>>> H0 = s -> FJCLTextConstruction.unit(s);

}
