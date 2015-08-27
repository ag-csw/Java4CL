package cl2

import org.scalatest._
import org.scalatest.matchers._
import cl2a._
import api4kbj.KnowledgeSourceLevel._
import prop.GeneratorDrivenPropertyChecks
import cl2array._
import collection.JavaConversions._
import scala.collection.immutable.List
import java.util.Arrays;

class CLConjunctionTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  implicit override val generatorDrivenConfig =
  PropertyCheckConfig(
      minSuccessful = 200,
      maxDiscarded = 1000,
      minSize = 10, 
      maxSize = 20,
      workers = 1)
    val comments = new CLCommentSetArray()
    val varx = new CLStringInterpretableName("x")
    val operator1 = new CLStringInterpretableName("allEqual")
    val operator2 = new CLStringInterpretableName("Thing")
    val termsequence1 = new CLTermSequenceArray(varx, varx)
    val termsequence2 = new CLTermSequenceArray(varx, varx)
    val conjunct1 = new CLAtomicSentence(comments, operator1, termsequence1)
    val conjunct2 = new CLAtomicSentence(comments, operator2, termsequence2)
    val testexpression = new CLConjunction(comments, new CLSentenceSequenceArray( conjunct1, conjunct2) )
      
      
  "A CLConjunction" should "be basic" in {
    (testexpression.isBasic()) should be(true)
  }

  "A CLConjunction" should "use language CL" in {
    val lang = CL.LANG
    (testexpression.language()) should be(lang)
  }

  "A CLConjunction" should "have knowledge source level EXPRESSION" in {
    (testexpression.level()) should be(EXPRESSION)
  }

  "The conjuncts of a CLConjunction" should "be equal to the parameter passed to the constructor" in {
    forAll("comment-symbols", "operator-string", minSuccessful(100)) { (commentSymbols: List[String], operatorString: String) =>
      val commentsarray:Array[CLComment] = commentSymbols.map(s => new CLStringComment(s).asInstanceOf[CLComment]).toArray[CLComment]
      val comments = new CLCommentSetArray(commentsarray:_*)
      val comments0 = new CLCommentSetArray()
      val operator = new CLStringInterpretableName(operatorString)
      val termsequence = new CLTermSequenceArray(varx, varx)
      val body = new CLAtomicSentence(comments0, operator, termsequence)
      val bindings = new CLBindingSequenceArray(varx)
      val conjuncts = new CLSentenceSequenceArray( conjunct1, conjunct2)
      val testexpression = new CLConjunction(comments,  conjuncts)
      val testconjuncts = (testexpression conjuncts)
      (testconjuncts) should be (conjuncts)
    }
  }
}
