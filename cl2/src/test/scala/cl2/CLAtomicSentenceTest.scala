
package cl2

import org.scalacheck.Gen
import org.scalatest._
import org.scalatest.matchers._
import prop.PropertyChecks
import scala.collection.immutable.List
import org.typelevel.discipline.scalatest._

import collection.JavaConversions._
import java.util.Arrays;

import api4kbj.KnowledgeSourceLevel._
import cl2a._
import cl2array._

import CLGenerators._
import scala.language.postfixOps

class CLAtomicSentenceTest extends FlatSpec
    with Matchers
    with PropertyChecks {
  implicit override val generatorDrivenConfig =
    PropertyCheckConfig(
      minSuccessful = 200,
      maxDiscarded = 1000,
      minSize = 10,
      maxSize = 20,
      workers = 1)

  val comments1 = new CLCommentSetArray()
  val comments2 = new CLCommentSetArray()
  val comments3 = new CLCommentSetArray(new CLStringComment("comment"))
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

  "A CLAtomicSentence constructor call with null argument" should "throw a NullPointerException" in {
    intercept[NullPointerException] {
      val testfragment = new CLAtomicSentence(comments1, null, termsequence1)
    }
    intercept[NullPointerException] {
      val testfragment = new CLAtomicSentence(comments1, operator1, null)
    }
    intercept[NullPointerException] {
      val testfragment = new CLAtomicSentence(comments1, null, null)
    }
  }

  "The operator symbol of a CLAtomicSentence" should "be equal to the parameter passed to the operator constructor" in {
    forAll("operator", minSuccessful(100)) { (operator: CLTerm) =>
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
      forAll("term1") { (term1: CLTerm) =>
        val termsequence = new CLTermSequenceArray(term1)
        val testexpression = new CLAtomicSentence(comments1, operator1, termsequence)
        (testexpression args) should equal(termsequence)
      }
    }

  "The term sequence of a CLAtomicSentence from a length one term sequence" should
    "be equal to the parameter passed to the term constructor 2" in {
      forAll("operator", minSuccessful(100)) { (operator: CLTerm) =>
        val termsequence = new CLTermSequenceArray(testfragment1)
        val testexpression = new CLAtomicSentence(comments1, operator, termsequence)
        val testargs = testexpression.args()
        (testexpression args) should equal(termsequence)
      }
    }

  "The term sequence of a CLAtomicSentence constructed from a length two term sequence" should
    "be equal to the parameter passed to the term constructor" in {
      forAll("term1", "term2") { (term1: CLTerm, term2: CLTerm) =>
        val termsequence = new CLTermSequenceArray(term1, term2)
        val testexpression = new CLAtomicSentence(comments1, operator1, termsequence)
        (testexpression args) should equal(termsequence)
      }
    }

  "The components of a CLAtomicSentence" should "equal the argument passed to the term constructor" in {
    forAll("commentsequence", "operator", "termsequence") {
      (commentsequence: CLCommentSet, operator: CLTerm, termsequence: CLTermSequence) =>
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

}
