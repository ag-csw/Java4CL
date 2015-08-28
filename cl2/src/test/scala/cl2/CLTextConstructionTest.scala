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
import scala.language.postfixOps

class CLTextConstructionTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  implicit override val generatorDrivenConfig =
    PropertyCheckConfig(
      minSuccessful = 200,
      maxDiscarded = 1000,
      minSize = 10,
      maxSize = 20,
      workers = 1)
  val comments = new CLCommentSetArray()
  val varx = new CLStringInterpretableName("x")
  val operator = new CLStringInterpretableName("allEqual")
  val termsequence = new CLTermSequenceArray(varx, varx)
  val atom = new CLAtomicSentence(comments, operator, termsequence)
  val expressions = new CLExpressionSequenceArray(atom)
  val testexpression = new CLTextConstruction(comments, expressions)

  "A CLTextConstruction" should "be basic" in {
    (testexpression isBasic) should be (true)
  }

  "A CLTextConstruction" should "use language CL" in {
    val lang = CL.LANG
    (testexpression language) should be (lang)
  }

  "A CLTextConstruction" should "have knowledge source level EXPRESSION" in {
    (testexpression level) should be (EXPRESSION)
  }

  "The expression sequence of a CLTextConstruction" should "be equal to the parameter passed to the constructor" in {
    (testexpression expressions) should be (expressions)
  }
    
  "The comments of a CLTextConstruction" should "be equal to the parameter passed to the constructor" in {
    forAll("comment-symbols", "operator-string", minSuccessful(100)) { (commentSymbols: List[String], operatorString: String) =>
      val commentsarray: Array[CLComment] = commentSymbols.map(s => new CLStringComment(s).asInstanceOf[CLComment]).toArray[CLComment]
      val comments = new CLCommentSetArray(commentsarray: _*)
      val testexpression = new CLTextConstruction(comments, expressions)
      val testcomments = (testexpression comments)
      (testcomments) should be (comments)
    }
  }

}
