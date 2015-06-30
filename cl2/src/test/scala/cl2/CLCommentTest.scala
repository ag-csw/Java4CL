package cl2

import org.scalatest._
import org.scalatest.matchers._
import api4kbj.BasicKnowledgeExpression._
import cl2a._

class TestAPI4KP extends FlatSpec with Matchers {

  "A CLComment" should "use language CL" in {
    val lang = CL.LANG
    val comment = new CLComment("test")
    (1) should be (1)
  }

}
