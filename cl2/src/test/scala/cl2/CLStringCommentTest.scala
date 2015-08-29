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

class CLStringCommentTest extends FlatSpec with Matchers with PropertyChecks {
  implicit override val generatorDrivenConfig =
    PropertyCheckConfig(
      minSuccessful = MIN_SUCCESSFUL,
      maxDiscarded = MAX_DISCARDED,
      minSize = MIN_SIZE,
      maxSize = MAX_SIZE,
      workers = WORKERS)
  val data = "test"
  val data2 = "test"
  val data3 = "other"
  val testfragment1 = new CLStringComment(data)
  val testfragment2 = new CLStringComment(data2)
  val testfragment3 = new CLStringComment(data3)

  "A CLStringComment constructor call with null argument" should "throw a NullPointerException" in {
    intercept[NullPointerException] {
      val testfragment4 = new CLStringComment(null)
    }
  }

  "A CLStringComment constructor call with argument containg banned characters" should
    "throw an IllegalArgumentException" in {
      forAll(CLTables.badcodepoints) { (i: Int) =>
        an[IllegalArgumentException] should be thrownBy
          new CLStringComment(new String(Character.toChars(i)))
      }
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
          if (dataa.equals(datab)) {
            (testfragmenta) should equal(testfragmentb)
          } else {
            (testfragmenta) should not equal (testfragmentb)
          }
        }
      }
  }

  "A CLStringComment's string representation" should
    "be the XML element cl:Comment with data as content, with appropriate escaping" in {
      (testfragment1 toString) should equal("<cl:Comment>test</cl:Comment>")
      forAll((CLGenerators.clstringsymbolgen, "data")) { (data: String) =>
        {
          val testfragment = new CLStringComment(data)
          (testfragment1 toString) should equal("<cl:Comment>" + CL.xmlContentEncode(testfragment1 data) + "</cl:Comment>")
        }
      }
    }

}
