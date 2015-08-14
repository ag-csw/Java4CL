package cl2

import org.scalatest._
import org.scalatest.matchers._
import cl2a._
import api4kbj.KnowledgeSourceLevel._
import prop.GeneratorDrivenPropertyChecks

class CLCommentTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "The symbol of a CLComment" should "be equal to the string test" in {
    val symbol = "test"
    val testexpression = new CLStringComment(symbol)
    (testexpression.symbol()) should be(symbol)
  }

  "A CLComment" should "be basic" in {
    val testexpression = new CLStringComment("test")
    (testexpression.isBasic()) should be(true)
  }

  "A CLComment" should "use language CL" in {
    val lang = CL.LANG
    val testexpression = new CLStringComment("test")
    (testexpression.language()) should be(lang)
  }

  "A CLComment" should "have knowledge source level EXPRESSION" in {
    val testexpression = new CLStringComment("test")
    (testexpression.level()) should be(EXPRESSION)
  }

  "The symbol of a CLComment" should "be equal to the parameter passed to the constructor" in {
    forAll("symbol") { (symbol: String) =>
      {
        val testexpression = new CLStringComment(symbol)
        (testexpression.symbol()) should be(symbol)
      }
    }
  }
}
