// Copyright (C) 2015 Athan Services.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package cl2

import org.scalatest.prop.PropertyChecks
import org.scalatest.{ FlatSpec, Matchers }
import scala.language.postfixOps

class CLStringInterpretableNameTest extends FlatSpec with Matchers with PropertyChecks {
  implicit override val generatorDrivenConfig =
    PropertyCheckConfig(
      minSuccessful = MIN_SUCCESSFUL,
      maxDiscarded = MAX_DISCARDED,
      minSize = MIN_SIZE,
      maxSize = MAX_SIZE,
      workers = WORKERS)
  val symbol1 = "test"
  val symbol2 = "test"
  val symbol3 = "other"
  val testfragment1 = new CLStringInterpretableName(symbol1)
  val testfragment2 = new CLStringInterpretableName(symbol2)
  val testfragment3 = new CLStringInterpretableName(symbol3)

  "A CLStringInterpretableName constructor call with null argument" should "throw a NullPointerException" in {
    intercept[NullPointerException] {
      val testfragment = new CLStringInterpretableName(null)
    }
  }

  "A CLStringInterpretableName constructor call with argument containg banned characters" should
    "throw an IllegalArgumentException" in {
      forAll(CLTables.badcodepoints) { (i: Int) =>
        an[IllegalArgumentException] should be thrownBy
          new CLStringInterpretableName(new String(Character.toChars(i)))
      }
    }

  "A CLStringName is basic" should "be true" in {
    (testfragment1 isBasic) should be(true)
  }

  "A CLStringInterpretableName's language" should "be CL" in {
    (testfragment1 language) should be(CL.LANG)
  }

  "A CLStringInterpretableName's knowledge source level" should "be EXPRESSION" in {
    (testfragment1 level) should be(EXPRESSION)
  }

  "The symbol of a CLStringInterpretableName" should "be equal to the parameter passed to the constructor" in {
    (testfragment1 symbol) should be(symbol1)
    forAll((CLGenerators.clstringsymbolgen, "symbol")) { (symbol: String) =>
      {
        val testfragment = new CLStringInterpretableName(symbol)
        (testfragment symbol) should be(symbol)
      }
    }
  }

  "The hashcode of CLStringInterpretableNames" should "depend only on its symbol" in {
    (testfragment1.hashCode().equals(testfragment2.hashCode())) should be(true)
    forAll((CLGenerators.clstringsymbolgen, "symbola"), (CLGenerators.clstringsymbolgen, "symbolb")) {
      (symbola: String, symbolb: String) =>
        {
          val testfragmenta = new CLStringInterpretableName(symbola)
          val testfragmentb = new CLStringInterpretableName(symbolb)
          val testfragmenta2 = new CLStringInterpretableName(symbola)
          if (symbola.equals(symbolb))
            (testfragmenta hashCode) should equal(testfragmentb hashCode)
          (testfragmenta hashCode) should equal(testfragmenta2 hashCode)
        }
    }
    forAll((CLGenerators.clstringinamegen, "namea"), (CLGenerators.clstringinamegen, "nameb")) {
      (namea: CLStringInterpretableName, nameb: CLStringInterpretableName) =>
        {
          if (namea.equals(nameb))
            (namea hashCode) should equal(nameb hashCode)
        }
    }
    (testfragment1 hashCode) should equal(testfragment2 hashCode)
  }

  "Equality of CLStringInterpretableNames" should "depend only on equality of their symbols" in {
    (testfragment1.equals(testfragment2)) should be(true)
    (testfragment1) should not equal (testfragment3)
    (testfragment1) should not equal (null)
    forAll((CLGenerators.clstringsymbolgen, "symbola"), (CLGenerators.clstringsymbolgen, "symbolb")) { (symbola: String, symbolb: String) =>
      {
        val testfragmenta = new CLStringInterpretableName(symbola)
        val testfragmentb = new CLStringInterpretableName(symbolb)
        val testfragmenta2 = new CLStringInterpretableName(symbola)
        if (symbola.equals(symbolb))
          (testfragmenta) should equal(testfragmentb)
        else
          (testfragmenta) should not equal (testfragmentb)
        (testfragmenta) should equal(testfragmenta2)
      }
    }
    forAll((CLGenerators.clstringinamegen, "namea"), (CLGenerators.clstringinamegen, "nameb")) {
      (namea: CLStringInterpretableName, nameb: CLStringInterpretableName) =>
        {
          if ((namea symbol).equals(nameb symbol))
            (namea) should equal(nameb)
          else {
            (namea) should not equal (nameb)
            //println("Duplicate generators do not produce identical results")
          }
          (namea) should equal(new CLStringInterpretableName(namea symbol))
        }
    }
  }

  "A CLStringInterpretableName's string representation" should
    "be the XML element cl:Name with symbol as content, with appropriate escaping" in {
      (testfragment1 toString) should equal("<cl:Name>test</cl:Name>")
      forAll((CLGenerators.clstringsymbolgen, "symbol")) { (symbol: String) =>
        {
          val testfragment = new CLStringInterpretableName(symbol)
          (testfragment toString) should equal("<cl:Name>" + CL.xmlContentEncode(testfragment symbol) + "</cl:Name>")
        }
      }
    }

}
