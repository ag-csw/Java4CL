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

class CLStringSequenceMarkerTest extends FlatSpec with Matchers with PropertyChecks {
  implicit override val generatorDrivenConfig =
    PropertyCheckConfig(
      minSuccessful = MIN_SUCCESSFUL,
      maxDiscarded = MAX_DISCARDED,
      minSize = MIN_SIZE,
      maxSize = MAX_SIZE,
      workers = WORKERS)

  val symbol = "test"
  val symbol2 = "test"
  val symbol3 = "other"
  val testfragment1 = new CLStringSequenceMarker(symbol)
  val testfragment2 = new CLStringSequenceMarker(symbol2)
  val testfragment3 = new CLStringSequenceMarker(symbol3)

  "A CLStringSequenceMarker constructor call with null argument" should "throw a NullPointerException" in {
    intercept[NullPointerException] {
      val testfragment4 = new CLStringSequenceMarker(null)
    }
  }

  "A CLStringSequenceMarker constructor call with argument containg banned characters" should
    "throw an IllegalArgumentException 2" in {
      forAll(CLTables.badcodepoints) { (i: Int) =>
        an[IllegalArgumentException] should be thrownBy
          new CLStringSequenceMarker(new String(Character.toChars(i)))
      }
    }

  "A CLStringSequenceMarker is basic" should "be true" in {
    (testfragment1 isBasic) should be(true)
  }

  "A CLStringSequenceMarker's language" should "be CL" in {
    (testfragment1 language) should be(CL.LANG)
  }

  "A CLStringSequenceMarker's knowledge source level" should "be EXPRESSION" in {
    (testfragment1 level) should be(EXPRESSION)
  }

  "The symbol of a CLStringSequenceMarker" should "be equal to the parameter passed to the constructor" in {
    (testfragment1 symbol) should be(symbol)
    forAll((CLGenerators.clstringsymbolgen, "symbol")) { (symbol: String) =>
      {
        val testfragment = new CLStringSequenceMarker(symbol)
        (testfragment symbol) should be(symbol)
      }
    }
  }

  "The hashcode of CLStringSequenceMarkers" should "depend only on its symbol" in {
    (testfragment1.hashCode().equals(testfragment2.hashCode())) should be(true)
    forAll((CLGenerators.clstringsymbolgen, "symbola"), (CLGenerators.clstringsymbolgen, "symbolb")) {
      (symbola: String, symbolb: String) =>
        {
          val testfragmenta = new CLStringSequenceMarker(symbola)
          val testfragmentb = new CLStringSequenceMarker(symbolb)
          val testfragmenta2 = new CLStringSequenceMarker(symbola)
          if (symbola.equals(symbolb)) {
            (testfragmenta hashCode) should equal(testfragmentb hashCode)
          }
          (testfragmenta hashCode) should equal(testfragmenta2 hashCode)
        }
    }
  }

  "The hashcode of CLStringSequenceMarkers" should "depend only on its symbol 2" in {
    forAll((CLGenerators.clstringsequencemarkergen, "markera"), (CLGenerators.clstringsequencemarkergen, "markerb")) {
      (markera: CLStringSequenceMarker, markerb: CLStringSequenceMarker) =>
        {
          if (markera.equals(markerb)) {
            (markera hashCode) should equal(markerb hashCode)
          }
        }
    }
    (testfragment1 hashCode) should equal(testfragment2 hashCode)
  }

  "Equality of CLStringSequenceMarkers" should "depend only on equality of their symbols" in {
    (testfragment1.equals(testfragment2)) should be(true)
    (testfragment1) should not equal (testfragment3)
    (testfragment1) should not equal (null)
    forAll((CLGenerators.clstringsymbolgen, "symbola"), (CLGenerators.clstringsymbolgen, "symbolb")) { (symbola: String, symbolb: String) =>
      {
        val testfragmenta = new CLStringSequenceMarker(symbola)
        val testfragmentb = new CLStringSequenceMarker(symbolb)
        val testfragmenta2 = new CLStringSequenceMarker(symbola)
        if (symbola.equals(symbolb)) {
          (testfragmenta) should equal(testfragmentb)
        } else {
          (testfragmenta) should not equal (testfragmentb)
        }
        (testfragmenta) should equal(testfragmenta2)
      }
    }
  }

  "Equality of CLStringSequenceMarkers" should "depend only on their symbols 2" in {
    (testfragment1.hashCode().equals(testfragment2.hashCode())) should be(true)
    forAll((CLGenerators.clstringsequencemarkergen, "markera"), (CLGenerators.clstringsequencemarkergen, "markerb")) {
      (markera: CLStringSequenceMarker, markerb: CLStringSequenceMarker) =>
        {
          if ((markera symbol).equals(markerb symbol)) {
            (markera) should equal(markerb)
          } else {
            (markera) should not equal (markerb)
            // println("Duplicate generators do not produce identical results")
          }
          (markera) should equal(new CLStringSequenceMarker(markera symbol))
        }
    }
  }

  "A CLStringSequenceMarker's string representation" should
    "be the XML element cl:Marker with symbol as content, with appropriate escaping" in {
      (testfragment1 toString) should equal("<cl:Marker>test</cl:Marker>")
      forAll((CLGenerators.clstringsymbolgen, "symbol")) { (symbol: String) =>
        {
          val testfragment = new CLStringSequenceMarker(symbol)
          (testfragment1 toString) should equal("<cl:Marker>" + CL.xmlContentEncode(testfragment1 symbol) + "</cl:Marker>")
        }
      }
    }

}
