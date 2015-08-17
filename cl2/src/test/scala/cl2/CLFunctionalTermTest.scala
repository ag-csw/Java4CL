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

class CLFunctionalTermTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
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
    val testfragment1 = new CLFunctionalTerm(comments1, operator1, termsequence1)
    val testfragment2 = new CLFunctionalTerm(comments2, operator2, termsequence2)
    val termsequence3 = new CLTermSequenceArray(testfragment1)
    val testfragment3 = new CLFunctionalTerm(comments3, operator3, termsequence3)
    val testfragment4 = new CLFunctionalTerm(comments3, operator1, termsequence1)
    val testfragment5 = new CLFunctionalTerm(comments1, operator3, termsequence1)
    val testfragment6 = new CLFunctionalTerm(comments1, operator1, termsequence3)
    val testfragment7 = new CLFunctionalTerm(comments1, null, termsequence3)
  

  "A CLFunctionalTerm" should "be basic" in {
    (testfragment1 isBasic) should be(true)
  }

  "A CLFunctionalTerm" should "use language CL" in {
    (testfragment1 language) should be(CL.LANG)
  }

  "A CLFunctionalTerm" should "have knowledge source level EXPRESSION" in {
    (testfragment1 level) should be(EXPRESSION)
  }

  "The operator of a CLFunctionalTerm" should "be equal to the parameter passed to the term constructor 1" in {
    forAll("operator-string", minSuccessful(100)) { (operatorString: String) =>
      val operator = new CLStringInterpretableName(operatorString)
      val testfragment = new CLFunctionalTerm(comments1, operator, termsequence1)
      (testfragment operator) should equal(operator)
    }
  }

  "The operator of a CLFunctionalTerm" should "be equal to the parameter passed to the term constructor 2" in {
    forAll("operator-string", minSuccessful(100)) { (operatorString: String) =>
      val operator = new CLFunctionalTerm(comments1, new CLStringInterpretableName(operatorString), termsequence1)
      val testfragment = new CLFunctionalTerm(comments1, operator, termsequence1)
      (testfragment operator) should equal(operator)
    }
  }

  "The term sequence of a CLFunctionalTerm from a length zero term sequence" should 
  "be equal to the parameter passed to the term constructor" in {
      val termsequence = new CLTermSequenceArray()
      val testfragment = new CLFunctionalTerm(comments1, operator1, termsequence)
      val testargs = testfragment.args()
      (testargs) should equal(termsequence1)
  }
  
  "The term sequence of a CLFunctionalTerm from a length one term sequence" should 
  "be equal to the parameter passed to the term constructor 1" in {
    forAll("arg-string") { (argString: String) =>
      val term1 = new CLStringInterpretableName(argString)
      val termsequence = new CLTermSequenceArray(term1)
      val testfragment = new CLFunctionalTerm(comments1, operator1, termsequence)
      (testfragment args) should equal(termsequence)
    }
  }

  "The term sequence of a CLFunctionalTerm from a length one term sequence" should 
  "be equal to the parameter passed to the term constructor 2" in {
    forAll("arg-string") { (argString: String) =>
      val term1 = new CLStringSequenceMarker(argString)
      val termsequence = new CLTermSequenceArray(term1)
      val testfragment = new CLFunctionalTerm(comments1, operator1, termsequence)
      (testfragment args) should equal(termsequence)
    }
  }

  "The term sequence of a CLFunctionalTerm from a length one term sequence" should 
  "be equal to the parameter passed to the term constructor 3" in {
    forAll("operator-string") { (operatorString: String) =>
      val operator = new CLStringInterpretableName(operatorString)
      val termsequence = new CLTermSequenceArray(testfragment1)
      val testfragment = new CLFunctionalTerm(comments1, operator, termsequence)
      val testargs = testfragment.args()
      (testfragment args) should equal(termsequence)
    }
  }

   "The term sequence of a CLFunctionalTerm constructed from a length two term sequence" should 
   "be equal to the parameter passed to the term constructor" in {
    forAll("arg-string1", "arg-string2") { (argString1: String, argString2: String) =>
      val term1 = new CLStringInterpretableName(argString1)
      val term2 = new CLStringSequenceMarker(argString2)
      val termsequence = new CLTermSequenceArray(term1, term2)
      val testfragment = new CLFunctionalTerm(comments1, operator1, termsequence)
      (testfragment args) should equal(termsequence)
    }
  } 

   "The term sequence of a CLFunctionalTerm constructed from a length three term sequence" should 
   "be equal to the parameter passed to the term constructor" in {
    forAll("arg-string1", "arg-string2", "arg-string2") { (argString1: String, argString2: String, argString3: String) =>
      val term1 = new CLStringInterpretableName(argString1)
      val term2 = new CLStringSequenceMarker(argString2)
      val term3 = new CLFunctionalTerm(comments1, new CLStringInterpretableName(argString3), termsequence1)
      val termsequence = new CLTermSequenceArray(term1, term2, term3)
      val testfragment = new CLFunctionalTerm(comments1, operator1, termsequence)
      (testfragment args) should equal(termsequence)
    }
  } 

   "The components of a CLFunctionalTerm" should "equal the argument passed to the term constructor" in {
    forAll("comment-symbols", "operator-symbols", "arg-symbols") { 
      (commentSymbols: List[String], operatorSymbol: String, argsymbols: List[String]) =>
      val commentsarray = commentSymbols.map(s => new CLStringComment(s).asInstanceOf[CLComment]).toArray[CLComment]
      val comments = new CLCommentSequenceArray(commentsarray: _*)
      val operator = new CLStringInterpretableName(operatorSymbol)
      val termsarray = argsymbols.map(s => new CLStringInterpretableName(s).asInstanceOf[CLTermOrSequenceMarker]).toArray[CLTermOrSequenceMarker]
      val termsequence = new CLTermSequenceArray(termsarray: _*)
      val testfragment = new CLFunctionalTerm(comments, operator, termsequence)
      (testfragment comments) should equal(comments)
      (testfragment operator) should equal(operator)
      (testfragment args) should equal(termsequence)
    }
  }
   
  "Equality of CLFunctionalTerm" should "depend only on its fields" in {
    (testfragment1) should equal(testfragment2)
    (testfragment1) should not equal(testfragment3)
    (testfragment1) should not equal(testfragment4)
    (testfragment1) should not equal(testfragment5)
    (testfragment1) should not equal(testfragment6)
    (testfragment1) should not equal(null)
  }

    "Equality of CLFunctionalTerm" should "depend only on its fields 2" in {
    forAll("comment-symbols", "operator-symbols", "arg-symbols") { 
      (commentSymbols: List[String], operatorSymbol: String, argsymbols: List[String]) =>
      val commentsarray: Array[CLComment] = commentSymbols.map(s => new CLStringComment(s).asInstanceOf[CLComment]).toArray[CLComment]
      val comments = new CLCommentSequenceArray(commentsarray: _*)
      val operator = new CLStringInterpretableName(operatorSymbol)
      val termsarray: Array[CLTermOrSequenceMarker] = argsymbols.map(s => new CLStringInterpretableName(s).asInstanceOf[CLTermOrSequenceMarker]).toArray[CLTermOrSequenceMarker]
      val termsequence:CLTermSequenceArray = new CLTermSequenceArray(termsarray: _*)
      val testfragment = new CLFunctionalTerm(comments, operator, termsequence)
      val commentsarray2: Array[CLComment] = commentSymbols.map(s => new CLStringComment(s).asInstanceOf[CLComment]).toArray[CLComment]
      val comments2 = new CLCommentSequenceArray(commentsarray: _*)
      val operator2 = new CLStringInterpretableName(operatorSymbol)
      val termsarray2 = argsymbols.map(s => new CLStringInterpretableName(s).asInstanceOf[CLTermOrSequenceMarker]).toArray[CLTermOrSequenceMarker]
      val termsequence2 = new CLTermSequenceArray(termsarray: _*)
      val testfragment2 = new CLFunctionalTerm(comments2, operator2, termsequence2)
      (testfragment) should equal(testfragment2)
    }
  }

}
