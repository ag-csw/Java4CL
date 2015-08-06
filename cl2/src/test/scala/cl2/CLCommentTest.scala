package cl2

import org.scalatest._
import org.scalatest.matchers._
import cl2a._
import api4kbj.KnowledgeSourceLevel._
import prop.GeneratorDrivenPropertyChecks

class CLCommentTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  "The symbol of a CLComment" should "be equal to the string test" in {
    val symbol = "test"
    val comment = new CLStringComment(symbol)
    (comment.symbol()) should be (symbol)
  }

    "A CLComment" should "be basic" in {
    val comment = new CLStringComment("test")
    (comment.isBasic()) should be (true)
  }
   
    "A CLComment" should "use language CL" in {
    val lang = CL.LANG
    val comment = new CLStringComment("test")
    (comment.language()) should be (lang)
  }
    
    "A CLComment" should "have knowledge source level EXPRESSION" in {
    val comment = new CLStringComment("test")
    (comment.level()) should be (EXPRESSION)
  }
    
  "The symbol of a CLComment" should "be equal to the parameter passed to the constructor" in {
    forAll ("symbol") { (symbol: String) =>
      {val comment = new CLStringComment(symbol)
    (comment.symbol()) should be (symbol)}
    }
  }
}
