import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cl2.CLCommentExpression;
import cl2.CLSentenceOrStatementOrText;
import cl2.FJCLTextConstructionCSet;
import fj.F;
import fj.data.List;
import functional.EqEither;
import functional.EqSet;

@RunWith(Suite.class)
@SuiteClasses({ CLTest.class,
	FJCLTextConstructionCSetTest.class,
	BasicKnowledgeAssetTest.class })
public class AllCLCSetTests {

	public static final EqSet<CLCommentExpression> comments0 = EqSet.empty();
	public static final CLSentenceOrStatementOrText expression0 = FJCLTextConstructionCSet.empty();
	public static final FJCLTextConstructionCSet<CLSentenceOrStatementOrText> singleton0 = FJCLTextConstructionCSet.unit(expression0);
	public static final EqEither<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> either0 = EqEither.unitLeft(expression0);
	public static final List<EqEither<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> 
              expressions0 = List.cons(either0, List.cons(either0, List.nil()));
	public static final List<CLSentenceOrStatementOrText> contents0 =  List.cons(expression0, List.cons(expression0, List.nil()));
	public static final FJCLTextConstructionCSet<CLSentenceOrStatementOrText> text0 = FJCLTextConstructionCSet.text(expressions0);
	public static final F<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> G0 = s -> FJCLTextConstructionCSet.unit(s).insertLeaf(expression0);
	public static final F<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>, FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> H0 = s -> FJCLTextConstructionCSet.unit(s).insertLeaf(text0);

	
	public static final EqSet<CLCommentExpression> comments1 = EqSet.eqSet(new CLCommentExpression("Comment 1"), 
			new CLCommentExpression("Comment 2"));
	public static final CLSentenceOrStatementOrText expression1 = FJCLTextConstructionCSet.empty().insertComments(comments1);
	public static final FJCLTextConstructionCSet<CLSentenceOrStatementOrText> singleton1 = FJCLTextConstructionCSet.unit(comments1, expression1);
	public static final EqEither<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> either1 = EqEither.unitLeft(expression1);
	public static final List<EqEither<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> 
              expressions1 = List.cons(either1, List.cons(either1, List.nil()));
	public static final List<CLSentenceOrStatementOrText> contents1 =  List.cons(expression1, List.cons(expression1, List.nil()));
	public static final FJCLTextConstructionCSet<CLSentenceOrStatementOrText> text1 = FJCLTextConstructionCSet.text(comments1, expressions1);
	public static final F<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> G1 = s -> FJCLTextConstructionCSet.unit(comments1, s).insertLeaf(expression1);
	public static final F<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>, FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> H1 = s -> FJCLTextConstructionCSet.unit(comments1,s).insertLeaf(text1);

	public static final EqSet<CLCommentExpression> comments2 = EqSet.eqSet(new CLCommentExpression("Comment 3"), 
			new CLCommentExpression("Comment 4"));
	public static final CLSentenceOrStatementOrText expression2 = FJCLTextConstructionCSet.empty().insertComments(comments2);
	public static final FJCLTextConstructionCSet<CLSentenceOrStatementOrText> singleton2 = FJCLTextConstructionCSet.unit(comments2, expression2);
	public static final EqEither<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> either2 = EqEither.unitLeft(expression2);
	public static final List<EqEither<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> 
              expressions2 = List.cons(either2, List.cons(either2, List.nil()));
	public static final List<CLSentenceOrStatementOrText> contents2 =  List.cons(expression2, List.cons(expression2, List.nil()));
	public static final FJCLTextConstructionCSet<CLSentenceOrStatementOrText> text2 = FJCLTextConstructionCSet.text(comments2, expressions2);
	public static final F<CLSentenceOrStatementOrText, FJCLTextConstructionCSet<CLSentenceOrStatementOrText>> G2 = s -> FJCLTextConstructionCSet.unit(comments2, s).insertComments(comments1);
	public static final F<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>, FJCLTextConstructionCSet<FJCLTextConstructionCSet<CLSentenceOrStatementOrText>>> H2 = s -> FJCLTextConstructionCSet.unit(comments2,s).insertComments(comments1);

}
