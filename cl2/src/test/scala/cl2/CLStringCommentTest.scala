package cl2

import org.scalatest._
import org.scalatest.matchers._
import cl2a._
import api4kbj.KnowledgeSourceLevel._
import prop.GeneratorDrivenPropertyChecks

class CLStringCommentTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  val symbol = "test"
  val testfragment = new CLStringComment(symbol)
  val testfragment2 = new CLStringComment(symbol)

  "A CLComment" should "be basic" in {
    (testfragment isBasic) should be(true)
  }

  "A CLComment" should "use language CL" in {
    (testfragment language) should be(CL.LANG)
  }

  "A CLComment" should "have knowledge source level EXPRESSION" in {
    (testfragment level) should be(EXPRESSION)
  }

  "The symbol of a CLComment" should "be equal to the string test" in {
    (testfragment symbol) should be(symbol)
  }

  "The symbol of a CLComment" should "be equal to the parameter passed to the constructor" in {
    forAll("symbol") { (symbol: String) =>
      {
        val testfragment = new CLStringComment(symbol)
        (testfragment symbol) should be(symbol)
      }
    }
  }

  "Equality of CLComment" should "depend only on the value of its symbol" in {
    (testfragment) should equal(testfragment2)
  }

}
