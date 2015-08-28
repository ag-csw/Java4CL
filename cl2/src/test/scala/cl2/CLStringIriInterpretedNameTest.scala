package cl2

import org.scalatest._
import org.scalatest.matchers._
import prop.GeneratorDrivenPropertyChecks

import cl2a._
import api4kbj.KnowledgeSourceLevel._
import CLStringIriInterpretedName._
import scala.language.postfixOps

class CLStringIriInterpretedNameTest extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  val symbol1 = "test"
  val symbol2 = "test"
  val symbol3 = "other"
  val datatypeString1 = "http://www.w3.org/2001/XMLSchema#string"
  val datatypeString2 = "http://www.w3.org/2001/XMLSchema#string"
  val datatypeString3 = "http://www.w3.org/2001/XMLSchema#token"
  val datatype1 = new CLIRI(datatypeString1)
  val testfragment1 = createCLStringIriInterpretedNameFromStringIRI(symbol1, datatypeString1)
  val testfragment2 = createCLStringIriInterpretedNameFromStringIRI(symbol2, datatypeString2)
  val testfragment3 = createCLStringIriInterpretedNameFromStringIRI(symbol3, datatypeString3)
  val testfragment4 = createCLStringIriInterpretedNameFromStringIRI(symbol3, datatypeString1)
  val testfragment5 = createCLStringIriInterpretedNameFromStringIRI(symbol1, datatypeString3)

  "A CLStringIriInterpretedName constructor call with null argument" should "throw a NullPointerException" in {
    intercept[NullPointerException]{
      val testfragment = createCLStringIriInterpretedNameFromStringIRI(null, datatypeString1)
    }
    intercept[NullPointerException]{
      val testfragment = createCLStringIriInterpretedNameFromStringIRI(symbol1, null)
    }
    intercept[NullPointerException]{
      val testfragment = new CLStringIriInterpretedName(null, datatype1)
    }
    intercept[NullPointerException]{
      val testfragment = new CLStringIriInterpretedName(symbol1, null)
    }
  }
  
  "A CLStringName is basic" should "be true" in {
    (testfragment1 isBasic) should be(true)
  }

  "A CLStringIriInterpretedName's language" should "be CL" in {
    (testfragment1 language) should be(CL.LANG)
  }

  "A CLStringIriInterpretedName's knowledge source level" should "be EXPRESSION" in {
    (testfragment1 level) should be(EXPRESSION)
  }

  "The symbol of a CLStringIriInterpretedName" should "be equal to the parameter passed to the constructor" in {
    (testfragment1 symbol) should be(symbol1)
    forAll("symbol") { (symbol: String) =>
      {
        val testfragment = createCLStringIriInterpretedNameFromStringIRI(symbol, datatypeString1)
        (testfragment symbol) should be(symbol)
      }
    }
  }

  "The datatype of a CLStringIriInterpretedName" should "be equal to the parameter passed to the constructor" in {
    ((testfragment1 datatype) toString) should be(datatypeString1)
    ((testfragment2 datatype) toString) should be(datatypeString2)
    ((testfragment3 datatype) toString) should be(datatypeString3)
    //TODO generate random XSD datatype and valid lexical item
    /*forAll("datatypeString") { (datatypeString: String) =>
      {
        val testfragment = createCLStringIriInterpretedNameFromStringIRI(symbol1, datatypeString)
        ((testfragment datatype) toString) should be(datatypeString)
      }
    }*/
  }

  "The hashcode of CLStringIriInterpretedNames" should "depend only on its symbol" in {
    (testfragment1.hashCode().equals(testfragment2.hashCode())) should be(true)
    //TODO generate random XSD datatype and valid lexical item
    forAll("symbola", "symbolb") { (symbola: String, symbolb: String) =>
      {
        val testfragmenta = createCLStringIriInterpretedNameFromStringIRI(symbola, datatypeString1)
        val testfragmentb = createCLStringIriInterpretedNameFromStringIRI(symbolb, datatypeString1)
        if (symbola.equals(symbolb))
          (testfragmenta hashCode) should equal(testfragmentb hashCode)
      }
    }
  }
  
  "Equality of CLStringIriInterpretedNames" should "depend only on equality of their symbols" in {
    (testfragment1.equals(testfragment2)) should be(true)
    (testfragment1) should not equal (testfragment3)
    (testfragment1) should not equal (null)
    //TODO generate random XSD datatype and valid lexical item
    forAll("symbola", "symbolb") { (symbola: String, symbolb: String) =>
      {
        val testfragmenta = createCLStringIriInterpretedNameFromStringIRI(symbola, datatypeString1)
        val testfragmentb = createCLStringIriInterpretedNameFromStringIRI(symbolb, datatypeString1)
        if ((symbola.equals(symbolb)) && (datatypeString1.equals(datatypeString2)))
          (testfragmenta) should equal(testfragmentb)
        else
          (testfragmenta) should not equal (testfragmentb)
      }
    }
  }

  "A CLStringIriInterpretedName's string representation" should 
    "be the XML element cl:Data with datatypeString as an attribute and symbol as content, with appropriate escaping" in {
    (testfragment1 toString) should equal("<cl:Data datatype=" + CL.xmlAttributeEncode(datatypeString1) + ">test<\\cl:Data>")
    (testfragment1 toString) should equal("<cl:Data datatype=" + CL.xmlAttributeEncode(datatypeString1) + ">" + CL.xmlContentEncode(symbol1) + "<\\cl:Data>")
    //TODO generate random XSD datatype and valid lexical item
  }
}
