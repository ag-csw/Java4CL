package cl2

import org.scalatest._
import org.scalatest.matchers._

import cl2a._
import api4kbj.KnowledgeSourceLevel._
import prop.GeneratorDrivenPropertyChecks

class CLStringNameTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "A CLStringName" should "be basic" in {
    val testexpression = new CLStringInterpretableName("test")
    (testexpression.isBasic()) should be(true)
  }

  "A CLStringInterpretableNamee" should "use language CL" in {
    val lang = CL.LANG
    val testexpression = new CLStringInterpretableName("test")
    (testexpression.language()) should be(lang)
  }

  "A CLStringInterpretableNamee" should "have knowledge source level EXPRESSION" in {
    val testexpression = new CLStringInterpretableName("test")
    (testexpression.level()) should be(EXPRESSION)
  }

  "The symbol of a CLStringInterpretableNamee" should "be equal to the parameter passed to the constructor" in {
    forAll("symbol") { (symbol: String) =>
      {
        val testexpression = new CLStringInterpretableName(symbol)
        (testexpression.symbol()) should be(symbol)
      }
    }
  }
}
