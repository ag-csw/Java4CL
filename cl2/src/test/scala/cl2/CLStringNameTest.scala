package cl2

import org.scalatest._
import org.scalatest.matchers._
import cl2a._
import api4kbj.KnowledgeSourceLevel._
import prop.GeneratorDrivenPropertyChecks

class CLStringNameTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

    "A CLStringName" should "be basic" in {
    val comment = new CLStringName("test")
    (comment.isBasic()) should be (true)
  }
   
    "A CLStringName" should "use language CL" in {
    val lang = CL.LANG
    val comment = new CLStringName("test")
    (comment.language()) should be (lang)
  }
    
    "A CLStringName" should "have knowledge source level EXPRESSION" in {
    val comment = new CLStringName("test")
    (comment.level()) should be (EXPRESSION)
  }
    
  "The symbol of a CLStringName" should "be equal to the parameter passed to the constructor" in {
    forAll ("symbol") { (symbol: String) =>
      {val comment = new CLStringName(symbol)
    (comment.symbol()) should be (symbol)}
    }
  }
}
