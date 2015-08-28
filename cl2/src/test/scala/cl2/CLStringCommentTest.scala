package cl2

import org.scalatest._
import org.scalatest.matchers._
import cl2a._
import api4kbj.KnowledgeSourceLevel._
import prop.GeneratorDrivenPropertyChecks
import scala.language.postfixOps

class CLStringCommentTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  val data = "test"
  val data2 = "test"
  val data3 = "other"
  val testfragment1 = new CLStringComment(data)
  val testfragment2 = new CLStringComment(data2)
  val testfragment3 = new CLStringComment(data3)

  "A CLStringComment constructor call with null argument" should "throw a NullPointerException" in {
    intercept[NullPointerException]{
      val testfragment4 = new CLStringComment(null)
    }
  }
  
  "A CLComment is basic" should "be true" in {
    (testfragment1 isBasic) should be(true)
  }

  "A CLComment's language" should "be CL" in {
    (testfragment1 language) should be(CL.LANG)
  }

  "A CLComment's knowledge source level" should "be EXPRESSION" in {
    (testfragment1 level) should be(EXPRESSION)
  }

  "The data of a CLComment" should "be equal to the parameter passed to the constructor" in {
    (testfragment1 data) should be(data)
    forAll((CLGenerators.clstringsymbolgen, "data")) { (data: String) =>
      {
        val testfragment = new CLStringComment(data)
        (testfragment data) should be(data)
      }
    }
  }

  "Equality of CLComment" should "depend only on the value of its data" in {
    (testfragment1) should equal(testfragment2)
    (testfragment1) should not equal (testfragment3)
    (testfragment1) should not equal (null)
    forAll((CLGenerators.clstringsymbolgen, "dataa"), 
        (CLGenerators.clstringsymbolgen, "datab")) { (dataa: String, datab: String) =>
      {
        val testfragmenta = new CLStringComment(dataa)
        val testfragmentb = new CLStringComment(datab)
        if (dataa.equals(datab))
          (testfragmenta) should equal(testfragmentb)
        else
          (testfragmenta) should not equal (testfragmentb)
      }
    }
  }

  "A CLStringComment's string representation" should
    "be the XML element cl:Comment with data as content, with appropriate escaping" in {
    (testfragment1 toString) should equal("<cl:Comment>test<\\cl:Comment>")
    forAll((CLGenerators.clstringsymbolgen, "data")) { (data: String) =>
      {
        val testfragment = new CLStringComment(data)
       (testfragment1 toString) should equal("<cl:Comment>" + CL.xmlContentEncode(testfragment1 data) + "<\\cl:Comment>")
      }
    }
  }
    
}
