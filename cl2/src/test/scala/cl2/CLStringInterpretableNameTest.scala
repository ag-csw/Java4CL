package cl2

import org.scalatest._
import org.scalatest.matchers._

import cl2a._
import api4kbj.KnowledgeSourceLevel._
import prop.GeneratorDrivenPropertyChecks

class CLStringInterpretableNameTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  val symbol = "test"
  val testfragment = new CLStringInterpretableName(symbol)
  val testfragment2 = new CLStringInterpretableName(symbol)

  "A CLStringName" should "be basic" in {
    (testfragment isBasic) should be(true)
  }

  "A CLStringInterpretableNamee" should "use language CL" in {
    (testfragment language) should be(CL.LANG)
  }

  "A CLStringInterpretableNamee" should "have knowledge source level EXPRESSION" in {
    (testfragment level) should be(EXPRESSION)
  }

  "The symbol of a CLStringName" should "be equal to the original symbol" in {
    (testfragment symbol) should be(symbol)
  }


  "The symbol of a CLStringInterpretableNamee" should "be equal to the parameter passed to the constructor" in {
    forAll("symbol") { (symbol: String) =>
      {
        val testfragment = new CLStringInterpretableName(symbol)
        (testfragment symbol) should be(symbol)
      }
    }
  }
  "Equality of CLStringInterpretableName" should "depend only on the value of its symbol" in {
    (testfragment) should equal(testfragment2)
  }
}
