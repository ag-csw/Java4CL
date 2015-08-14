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

class CLInDiscourseStatementTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  implicit override val generatorDrivenConfig =
  PropertyCheckConfig(
      minSuccessful = 200,
      maxDiscarded = 1000,
      minSize = 10, 
      maxSize = 20,
      workers = 1)
  "A CLInDiscourseStatement" should "be basic" in {
    val comments = new CLCommentSequenceArray()

    val termsequence = new CLTermSequenceArray()
    val testexpression = new CLInDiscourseStatement(comments, termsequence)
    (testexpression.isBasic()) should be(true)
  }

  "A CLInDiscourseStatement" should "use language CL" in {
    val lang = CL.LANG
    val comments = new CLCommentSequenceArray()

    val termsequence = new CLTermSequenceArray()
    val testexpression = new CLInDiscourseStatement(comments, termsequence)
    (testexpression.language()) should be(lang)
  }

  "A CLInDiscourseStatement" should "have knowledge source level EXPRESSION" in {
    val comments = new CLCommentSequenceArray()

    val termsequence = new CLTermSequenceArray()
    val testexpression = new CLInDiscourseStatement(comments, termsequence)
    (testexpression.level()) should be(EXPRESSION)
  }


  "The term sequence of a CLInDiscourseStatement" should "be equal to the parameter passed to the term constructor" in {
    forAll("arg-string") { (argString: String) =>
      val comments = new CLCommentSequenceArray()
  
      val term1 = new CLStringInterpretableName(argString)
      val termsequence = new CLTermSequenceArray(term1)
      val testexpression = new CLInDiscourseStatement(comments, termsequence)
      val statementargs = testexpression.args()
      (statementargs) should be(termsequence)
      val statementargsscala: Iterable[CLTermOrSequenceMarker] = statementargs.args()
      val statementnameterm1 = statementargsscala.head match {
        case statementargsname: CLName => statementargsname
        case _                    => throw new ClassCastException
      }
      (statementnameterm1.symbol()) should be(argString)
    }
  }
  
   "The term sequence of a CLInDiscourseStatement constructed from a length two term sequence" should "have length two" in {
    forAll("arg-string1", "arg-string2") { (argString1: String, argString2: String) =>
      val comments = new CLCommentSequenceArray()
  
      val term1 = new CLStringInterpretableName(argString1)
      val term2 = new CLStringInterpretableName(argString2)
      val termsequence = new CLTermSequenceArray(term1, term2)
      val testexpression = new CLInDiscourseStatement(comments, termsequence)
      val statementargs = testexpression.args()
      (statementargs.length()) should be(termsequence.length())
    }
  } 
   "The term sequence of a CLInDiscourseStatement" should "have the same length as the one passed to the term constructor" in {
    forAll("symbols") { (symbols: List[String]) =>
      val comments = new CLCommentSequenceArray()
  
      val terms:List[CLTermOrSequenceMarker] = symbols.map(s => new CLStringInterpretableName(s))
      var termsequence:CLTermSequenceArray = new CLTermSequenceArray()
      for (term <- terms){
        termsequence = termsequence.concat(new CLTermSequenceArray(term))
      }
      val testexpression = new CLInDiscourseStatement(comments, termsequence)
      ((testexpression args) length) should be (termsequence length)
    }
  }
}
