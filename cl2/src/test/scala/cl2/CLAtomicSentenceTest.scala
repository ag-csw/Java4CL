package cl2

import org.scalacheck.Gen
import org.scalatest._
import org.scalatest.matchers._
import prop.PropertyChecks
import scala.collection.immutable.List

import collection.JavaConversions._
import java.util.Arrays;

import api4kbj.KnowledgeSourceLevel._
import cl2a._
import cl2array._

class CLAtomicSentenceTest extends FlatSpec with Matchers with PropertyChecks {
  implicit override val generatorDrivenConfig =
    PropertyCheckConfig(
      minSuccessful = 200,
      maxDiscarded = 1000,
      minSize = 10,
      maxSize = 20,
      workers = 1)

  val comments1 = new CLCommentSequenceArray()
  val comments2 = new CLCommentSequenceArray()
  val comments3 = new CLCommentSequenceArray(new CLStringComment("comment"))
  val operator1 = new CLStringInterpretableName("rel")
  val operator2 = new CLStringInterpretableName("rel")
  val operator3 = new CLStringInterpretableName("otherrel")
  val termsequence1 = new CLTermSequenceArray()
  val termsequence2 = new CLTermSequenceArray()
  val testexpression1 = new CLAtomicSentence(comments1, operator1, termsequence1)
  val testexpression2 = new CLAtomicSentence(comments2, operator2, termsequence2)
  val testfragment1 = new CLFunctionalTerm(comments1, operator1, termsequence1)
  val termsequence3 = new CLTermSequenceArray(testfragment1)
  val testexpression3 = new CLAtomicSentence(comments3, operator3, termsequence3)
  val testexpression4 = new CLAtomicSentence(comments3, operator1, termsequence1)
  val testexpression5 = new CLAtomicSentence(comments1, operator3, termsequence1)
  val testexpression6 = new CLAtomicSentence(comments1, operator1, termsequence3)

  val clstringsymbolgen: Gen[String] =
    for (
      a <- Gen listOf (
        for (i <- Gen choose (9, Character.MAX_CODE_POINT))
          yield String copyValueOf (Character toChars i))
    ) yield (a mkString) replaceAll ("[\\p{Cc}&&[^\r\n\t]]", "")

  val clstringinamegen: Gen[CLStringInterpretableName] =
    for (s <- clstringsymbolgen)
      yield new CLStringInterpretableName(s)

  val clliststringinamesymbolgen: Gen[List[String]] = 
      Gen listOf clstringsymbolgen

  val clliststringinamegen: Gen[List[CLStringInterpretableName]] = 
    Gen listOf clstringinamegen

  val clstringcommentgen: Gen[CLStringComment] =
    for (s <- clstringsymbolgen)
      yield new CLStringComment(s)

  val clliststringcommentgen: Gen[List[CLStringComment]] = 
    Gen listOf clstringcommentgen

  val clstringcommentsequencegen: Gen[CLCommentSequence] = 
    for (a <- Gen listOf clstringcommentgen)
      yield new CLCommentSequenceArray(a.toArray[CLStringComment]: _*)
  
  val cltermsequencegen: Gen[CLTermSequence] = 
    for (a <- clliststringinamegen)
      yield new CLTermSequenceArray(a.toArray[CLStringInterpretableName]: _*)

  "A CLAtomicSentence" should "be basic" in {
    (testexpression1 isBasic) should be(true)
  }

  "A CLAtomicSentence" should "use language CL" in {
    (testexpression1 language) should be(CL.LANG)
  }

  "A CLAtomicSentence" should "have knowledge source level EXPRESSION" in {
    (testexpression1 level) should be(EXPRESSION)
  }

  "The operator symbol of a CLAtomicSentence" should "be equal to the parameter passed to the operator constructor" in {
    forAll((clstringinamegen, "operator"), minSuccessful(100)) { (operator: CLStringInterpretableName) =>
      val testexpression = new CLAtomicSentence(comments1, operator, termsequence1)
      (testexpression operator) should be(operator)
    }
  }

  "The term sequence of a CLAtomicSentence from a length zero term sequence" should
    "be equal to the parameter passed to the term constructor" in {
      val termsequence = new CLTermSequenceArray()
      val testexpression = new CLAtomicSentence(comments1, operator1, termsequence)
      (testexpression args) should equal(termsequence1)
    }

  "The term sequence of a CLAtomicSentence from a length one term sequence" should
    "be equal to the parameter passed to the term constructor 1" in {
      forAll((clstringinamegen, "term1")) { (term1: CLStringInterpretableName) =>
        val termsequence = new CLTermSequenceArray(term1)
        val testexpression = new CLAtomicSentence(comments1, operator1, termsequence)
        (testexpression args) should equal(termsequence)
      }
    }

  "The term sequence of a CLAtomicSentence from a length one term sequence" should
    "be equal to the parameter passed to the term constructor 2" in {
      forAll((clstringinamegen, "operator"), minSuccessful(100)) { (operator: CLStringInterpretableName) =>
        val termsequence = new CLTermSequenceArray(testfragment1)
        val testexpression = new CLAtomicSentence(comments1, operator, termsequence)
        val testargs = testexpression.args()
        (testexpression args) should equal(termsequence)
      }
    }

  "The term sequence of a CLAtomicSentence constructed from a length two term sequence" should
    "be equal to the parameter passed to the term constructor" in {
      forAll((clstringinamegen, "term1"), (clstringinamegen, "term1")) { (term1: CLStringInterpretableName, term2: CLStringInterpretableName) =>
        val termsequence = new CLTermSequenceArray(term1, term2)
        val testexpression = new CLAtomicSentence(comments1, operator1, termsequence)
        (testexpression args) should equal(termsequence)
      }
    }

  "The components of a CLAtomicSentence" should "equal the argument passed to the term constructor" in {
    forAll((clstringcommentsequencegen, "commentsequence"), (clstringinamegen, "operator"), (cltermsequencegen, "termsequence")) {
      (commentsequence: CLCommentSequence, operator: CLStringInterpretableName, termsequence: CLTermSequence) =>
        val testexpression = new CLAtomicSentence(commentsequence, operator, termsequence)
        (testexpression comments) should equal(commentsequence)
        (testexpression operator) should equal(operator)
        (testexpression args) should equal(termsequence)
    }
  }

  "Equality of CLAtomicSentence" should "depend only on its fields" in {
    (testexpression1) should equal(testexpression2)
    (testexpression1) should not equal (testexpression3)
    (testexpression1) should not equal (testexpression4)
    (testexpression1) should not equal (testexpression5)
    (testexpression1) should not equal (testexpression6)
    (testexpression1) should not equal (null)
  }

  "Equality of CLAtomicSentence" should "depend only on its fields 2" in {
    forAll((clstringcommentsequencegen, "commentsequence"), (clstringinamegen, "operator"), (cltermsequencegen, "termsequence")) {
      (commentsequence: CLCommentSequence, operator: CLStringInterpretableName, termsequence: CLTermSequence) =>        
        val commentsequence2 = new CLCommentSequenceArray(commentsequence args)

        val operator2 = new CLStringInterpretableName(operator symbol)

        val termsarray: Array[CLTermOrSequenceMarker] = (termsequence args) toArray new Array[CLTermOrSequenceMarker](0)
        val termsequence2 = new CLTermSequenceArray(termsequence args)


        val testexpression = new CLAtomicSentence(commentsequence, operator, termsequence)
        val testexpression2 = new CLAtomicSentence(commentsequence2, operator2, termsequence2)

        (testexpression) should equal(testexpression2)
    }
  }

}
