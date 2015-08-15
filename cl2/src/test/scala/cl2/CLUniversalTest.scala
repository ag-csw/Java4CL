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

class CLUniversalTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  implicit override val generatorDrivenConfig =
    PropertyCheckConfig(
      minSuccessful = 200,
      maxDiscarded = 1000,
      minSize = 10,
      maxSize = 20,
      workers = 1)
  val comments = new CLCommentSequenceArray()
  val varx = new CLStringInterpretableName("x")
  val operator = new CLStringInterpretableName("allEqual")
  val termsequence = new CLTermSequenceArray(varx, varx)
  val body = new CLAtomicSentence(comments, operator, termsequence)
  val bindings = new CLBindingSequenceArray(varx)
  val testexpression = new CLUniversal(comments, bindings, body)

  "A CLUniversal" should "be basic" in {
    (testexpression isBasic) should be (true)
  }

  "A CLUniversal" should "use language CL" in {
    val lang = CL.LANG
    (testexpression language) should be (lang)
  }

  "A CLUniversal" should "have knowledge source level EXPRESSION" in {
    (testexpression level) should be (EXPRESSION)
  }

  "The binding sequence of a CLUniversal" should "be equal to the parameter passed to the constructor" in {
    (testexpression bindings) should be (bindings)
  }
    
  "The comments of a CLUniversal" should "be equal to the parameter passed to the constructor" in {
    forAll("comment-symbols", "operator-string", minSuccessful(100)) { (commentSymbols: List[String], operatorString: String) =>
      val commentsarray: Array[CLComment] = commentSymbols.map(s => new CLStringComment(s).asInstanceOf[CLComment]).toArray[CLComment]
      val comments = new CLCommentSequenceArray(commentsarray: _*)
      val comments0 = new CLCommentSequenceArray()
      val operator = new CLStringInterpretableName(operatorString)
      val termsequence = new CLTermSequenceArray(varx, varx)
      val body = new CLAtomicSentence(comments0, operator, termsequence)
      val bindings = new CLBindingSequenceArray(varx)
      val testexpression = new CLUniversal(comments, bindings, body)
      val testcomments = (testexpression comments)
      (testcomments) should be (comments)
    }
  }

  "The body of a CLUniversal" should "be equal to the parameter passed to the constructor" in {
    forAll("comment-symbols", "operator-string", minSuccessful(100)) { (commentSymbols: List[String], operatorString: String) =>
      val commentsarray: Array[CLComment] = commentSymbols.map(s => new CLStringComment(s).asInstanceOf[CLComment]).toArray[CLComment]
      val comments = new CLCommentSequenceArray(commentsarray: _*)
      val comments0 = new CLCommentSequenceArray()
      val operator = new CLStringInterpretableName(operatorString)
      val termsequence = new CLTermSequenceArray(varx, varx)
      val body = new CLAtomicSentence(comments0, operator, termsequence)
      val bindings = new CLBindingSequenceArray(varx)
      val testexpression = new CLUniversal(comments, bindings, body)
      val testbody = (testexpression body)
      (testbody) should be(body)
    }
  }
}
