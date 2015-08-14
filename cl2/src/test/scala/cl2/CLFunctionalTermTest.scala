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
  "A CLFunctionalTerm" should "be basic" in {
    val comments = new CLCommentSequenceArray()
    val operator = new CLStringInterpretableName("rel")
    val termsequence = new CLTermSequenceArray()
    val functionalterm = new CLFunctionalTerm(comments, operator, termsequence)
    (functionalterm.isBasic()) should be(true)
  }

  "A CLFunctionalTerm" should "use language CL" in {
    val lang = CL.LANG
    val comments = new CLCommentSequenceArray()
    val operator = new CLStringInterpretableName("rel")
    val termsequence = new CLTermSequenceArray()
    val functionalterm = new CLFunctionalTerm(comments, operator, termsequence)
    (functionalterm.language()) should be(lang)
  }

  "A CLFunctionalTerm" should "have knowledge source level EXPRESSION" in {
    val comments = new CLCommentSequenceArray()
    val operator = new CLStringInterpretableName("rel")
    val termsequence = new CLTermSequenceArray()
    val functionalterm = new CLFunctionalTerm(comments, operator, termsequence)
    (functionalterm.level()) should be(EXPRESSION)
  }

  "The operator symbol of a CLFunctionalTerm" should "be equal to the parameter passed to the operator constructor" in {
    forAll("operator-string", minSuccessful(100)) { (operatorString: String) =>
      val comments = new CLCommentSequenceArray()
      val operator = new CLStringInterpretableName(operatorString)
      val termsequence = new CLTermSequenceArray()
      val functionalterm = new CLFunctionalTerm(comments, operator, termsequence)
      val functionaltermop = functionalterm.operator()
      (functionaltermop) should be(operator)
      val functionaltermopresult = functionaltermop match {
        case functionaltermopname: CLName => functionaltermopname
        case _                  => throw new ClassCastException
      }
      (functionaltermopresult.symbol()) should be(operatorString)
    }
  }

  "The term sequence of a CLFunctionalTerm" should "be equal to the parameter passed to the term constructor" in {
    forAll("arg-string") { (argString: String) =>
      val comments = new CLCommentSequenceArray()
      val operator = new CLStringInterpretableName("rel")
      val term1 = new CLStringInterpretableName(argString)
      val termsequence = new CLTermSequenceArray(term1)
      val functionalterm = new CLFunctionalTerm(comments, operator, termsequence)
      val functionaltermargs = functionalterm.args()
      (functionaltermargs) should be(termsequence)
      val functionaltermargsscala: Iterable[CLTermOrSequenceMarker] = functionaltermargs.args()
      val functionaltermnameterm1 = functionaltermargsscala.head match {
        case functionaltermargsname: CLName => functionaltermargsname
        case _                    => throw new ClassCastException
      }
      (functionaltermnameterm1.symbol()) should be(argString)
    }
  }
  
   "The term sequence of a CLFunctionalTerm constructed from a length two term sequence" should "have length two" in {
    forAll("arg-string1", "arg-string2") { (argString1: String, argString2: String) =>
      val comments = new CLCommentSequenceArray()
      val operator = new CLStringInterpretableName("rel")
      val term1 = new CLStringInterpretableName(argString1)
      val term2 = new CLStringInterpretableName(argString2)
      val termsequence = new CLTermSequenceArray(term1, term2)
      val functionalterm = new CLFunctionalTerm(comments, operator, termsequence)
      val functionaltermargs = functionalterm.args()
      (functionaltermargs.length()) should be(termsequence.length())
    }
  } 
   "The term sequence of a CLFunctionalTerm" should "have the same length as the one passed to the term constructor" in {
    forAll("symbols") { (symbols: List[String]) =>
      val comments = new CLCommentSequenceArray()
      val operator = new CLStringInterpretableName("rel")
      val terms:List[CLTermOrSequenceMarker] = symbols.map(s => new CLStringInterpretableName(s))
      var termsequence:CLTermSequenceArray = new CLTermSequenceArray()
      for (term <- terms){
        termsequence = termsequence.concat(new CLTermSequenceArray(term))
      }
      val functionalterm = new CLFunctionalTerm(comments, operator, termsequence)
      ((functionalterm args) length) should be (termsequence length)
    }
  }
}
