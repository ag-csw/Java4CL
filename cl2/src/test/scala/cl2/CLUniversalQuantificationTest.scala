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

class CLUniversalQuantificationTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  implicit override val generatorDrivenConfig =
  PropertyCheckConfig(
      minSuccessful = 200,
      maxDiscarded = 1000,
      minSize = 10, 
      maxSize = 20,
      workers = 1)
  "A CLAtomicSentence" should "be basic" in {
    val comments = new CLCommentSequenceArray()
    val operator = new CLStringInterpretableName("rel")
    val termsequence = new CLTermSequenceArray()
    val atomicsent = new CLAtomicSentence(comments, operator, termsequence)
    (atomicsent.isBasic()) should be(true)
  }

  "A CLAtomicSentence" should "use language CL" in {
    val lang = CL.LANG
    val comments = new CLCommentSequenceArray()
    val operator = new CLStringInterpretableName("rel")
    val termsequence = new CLTermSequenceArray()
    val atomicsent = new CLAtomicSentence(comments, operator, termsequence)
    (atomicsent.language()) should be(lang)
  }

  "A CLAtomicSentence" should "have knowledge source level EXPRESSION" in {
    val comments = new CLCommentSequenceArray()
    val operator = new CLStringInterpretableName("rel")
    val termsequence = new CLTermSequenceArray()
    val atomicsent = new CLAtomicSentence(comments, operator, termsequence)
    (atomicsent.level()) should be(EXPRESSION)
  }

  "The operator symbol of a CLAtomicSentence" should "be equal to the parameter passed to the operator constructor" in {
    forAll("operator-string", minSuccessful(100)) { (operatorString: String) =>
      val comments = new CLCommentSequenceArray()
      val operator = new CLStringInterpretableName(operatorString)
      val termsequence = new CLTermSequenceArray()
      val atomicsent = new CLAtomicSentence(comments, operator, termsequence)
      val atomop = atomicsent.operator()
      (atomop) should be(operator)
      val atomopresult = atomop match {
        case atomopname: CLName => atomopname
        case _                  => throw new ClassCastException
      }
      (atomopresult.symbol()) should be(operatorString)
    }
  }

  "The term sequence of a CLAtomicSentence" should "be equal to the parameter passed to the term constructor" in {
    forAll("arg-string") { (argString: String) =>
      val comments = new CLCommentSequenceArray()
      val operator = new CLStringInterpretableName("rel")
      val term1 = new CLStringInterpretableName(argString)
      val termsequence = new CLTermSequenceArray(term1)
      val atomicsent = new CLAtomicSentence(comments, operator, termsequence)
      val atomargs = atomicsent.args()
      (atomargs) should be(termsequence)
      val atomargsscala: Iterable[CLTermOrSequenceMarker] = atomargs.args()
      val atomnameterm1 = atomargsscala.head match {
        case atomargsname: CLName => atomargsname
        case _                    => throw new ClassCastException
      }
      (atomnameterm1.symbol()) should be(argString)
    }
  }
  
   "The term sequence of a CLAtomicSentence constructed from a length two term sequence" should "have length two" in {
    forAll("arg-string1", "arg-string2") { (argString1: String, argString2: String) =>
      val comments = new CLCommentSequenceArray()
      val operator = new CLStringInterpretableName("rel")
      val term1 = new CLStringInterpretableName(argString1)
      val term2 = new CLStringInterpretableName(argString2)
      val termsequence = new CLTermSequenceArray(term1, term2)
      val atomicsent = new CLAtomicSentence(comments, operator, termsequence)
      val atomargs = atomicsent.args()
      (atomargs.length()) should be(termsequence.length())
    }
  } 
   "The term sequence of a CLAtomicSentence" should "have the same length as the one passed to the term constructor" in {
    forAll("symbols") { (symbols: List[String]) =>
      val comments = new CLCommentSequenceArray()
      val operator = new CLStringInterpretableName("rel")
      val terms:List[CLTermOrSequenceMarker] = symbols.map(s => new CLStringInterpretableName(s))
      var termsequence:CLTermSequenceArray = new CLTermSequenceArray()
      for (term <- terms){
        termsequence = termsequence.concat(new CLTermSequenceArray(term))
      }
      val atomicsent = new CLAtomicSentence(comments, operator, termsequence)
      ((atomicsent args) length) should be (termsequence length)
    }
  }
}
