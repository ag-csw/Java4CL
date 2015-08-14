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

class CLAtomicSentenceTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
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
    val testexpression = new CLAtomicSentence(comments, operator, termsequence)
    (testexpression.isBasic()) should be(true)
  }

  "A CLAtomicSentence" should "use language CL" in {
    val lang = CL.LANG
    val comments = new CLCommentSequenceArray()
    val operator = new CLStringInterpretableName("rel")
    val termsequence = new CLTermSequenceArray()
    val testexpression = new CLAtomicSentence(comments, operator, termsequence)
    (testexpression.language()) should be(lang)
  }

  "A CLAtomicSentence" should "have knowledge source level EXPRESSION" in {
    val comments = new CLCommentSequenceArray()
    val operator = new CLStringInterpretableName("rel")
    val termsequence = new CLTermSequenceArray()
    val testexpression = new CLAtomicSentence(comments, operator, termsequence)
    (testexpression.level()) should be(EXPRESSION)
  }

  "The operator symbol of a CLAtomicSentence" should "be equal to the parameter passed to the operator constructor" in {
    forAll("operator-string", minSuccessful(100)) { (operatorString: String) =>
      val comments = new CLCommentSequenceArray()
      val operator = new CLStringInterpretableName(operatorString)
      val termsequence = new CLTermSequenceArray()
      val testexpression = new CLAtomicSentence(comments, operator, termsequence)
      val testop = testexpression.operator()
      (testop) should be(operator)
      val testopresult = testop match {
        case testopname: CLName => testopname
        case _                  => throw new ClassCastException
      }
      (testopresult.symbol()) should be(operatorString)
    }
  }

  "The comment sequence of a CLAtomicSentence" should "be equal to the parameter passed to the term constructor" in {
    forAll("arg-string") { (argString: String) =>
      val comments = new CLCommentSequenceArray()
      val operator = new CLStringInterpretableName("rel")
      val term1 = new CLStringInterpretableName(argString)
      val termsequence = new CLTermSequenceArray(term1)
      val testexpression = new CLAtomicSentence(comments, operator, termsequence)
      val testargs = testexpression.args()
      (testargs) should be(termsequence)
      val testargsscala: Iterable[CLTermOrSequenceMarker] = testargs.args()
      val testnameterm1 = testargsscala.head match {
        case testargsname: CLName => testargsname
        case _                    => throw new ClassCastException
      }
      (testnameterm1.symbol()) should be(argString)
    }
  }
  
  "The term sequence of a CLAtomicSentence" should "be equal to the parameter passed to the term constructor" in {
    forAll("arg-string") { (argString: String) =>
      val comments = new CLCommentSequenceArray()
      val operator = new CLStringInterpretableName("rel")
      val term1 = new CLStringInterpretableName(argString)
      val termsequence = new CLTermSequenceArray(term1)
      val testexpression = new CLAtomicSentence(comments, operator, termsequence)
      val testargs = testexpression.args()
      (testargs) should be(termsequence)
      val testargsscala: Iterable[CLTermOrSequenceMarker] = testargs.args()
      val testnameterm1 = testargsscala.head match {
        case testargsname: CLName => testargsname
        case _                    => throw new ClassCastException
      }
      (testnameterm1.symbol()) should be(argString)
    }
  }

  "The comments of a CLAtomicSentence" should "be equal to the parameter passed to the constructor" in {
    forAll("comment-symbols", "operator-string", "symbols", minSuccessful(100)) { 
      (commentSymbols: List[String], operatorString: String, symbols: List[String] ) =>
      val commentsarray: Array[CLComment] = commentSymbols.map(s => new CLStringComment(s).asInstanceOf[CLComment]).toArray[CLComment]
      val comments = new CLCommentSequenceArray(commentsarray: _*)
      val comments0 = new CLCommentSequenceArray()
      val operator = new CLStringInterpretableName("rel")
      val terms:List[CLTermOrSequenceMarker] = symbols.map(s => new CLStringInterpretableName(s))
      var termsequence:CLTermSequenceArray = new CLTermSequenceArray()
      for (term <- terms){
        termsequence = termsequence.concat(new CLTermSequenceArray(term))
      }
      val testexpression = new CLAtomicSentence(comments, operator, termsequence)
      val testcomments = (testexpression comments)
      (testcomments) should be (comments)
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
      val testexpression = new CLAtomicSentence(comments, operator, termsequence)
      ((testexpression args) length) should be (termsequence length)
    }
  }
}
