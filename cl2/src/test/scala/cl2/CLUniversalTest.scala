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
import java.util.Arrays;
import scala.language.postfixOps
import org.scalatest._

@Ignore
class CLUniversalTest extends FlatSpec with Matchers with PropertyChecks {
  implicit override val generatorDrivenConfig =
    PropertyCheckConfig(
      minSuccessful = MIN_SUCCESSFUL,
      maxDiscarded = MAX_DISCARDED,
      minSize = MIN_SIZE,
      maxSize = MAX_SIZE,
      workers = WORKERS)
  val comments = new CLCommentSetArray()
  val varx = new CLStringInterpretableName("x")
  val operator = new CLStringInterpretableName("allEqual")
  val termsequence = new CLTermSequenceArray(varx, varx)
  val body = new CLAtomicSentence(comments, operator, termsequence)
  val bindings = new CLBindingSetArray(varx)
  val testexpression = new CLUniversal(comments, bindings, body)

  "The binding sequence of a CLUniversal" should "be equal to the bindings parameter passed to the constructor" in {
    (testexpression bindings) should be(bindings)
  }

  "The comments of a CLUniversal" should "be equal to the comments parameter passed to the constructor" in {
    forAll("comment-symbols", "operator-string", minSuccessful(MIN_SUCCESSFUL / 2)) { (commentSymbols: List[String], operatorString: String) =>
      val commentsarray: Array[CLComment] = commentSymbols.map(s => new CLStringComment(s).asInstanceOf[CLComment]).toArray[CLComment]
      val comments = new CLCommentSetArray(commentsarray: _*)
      val comments0 = new CLCommentSetArray()
      val operator = new CLStringInterpretableName(operatorString)
      val termsequence = new CLTermSequenceArray(varx, varx)
      val body = new CLAtomicSentence(comments0, operator, termsequence)
      val bindings = new CLBindingSetArray(varx)
      val testexpression = new CLUniversal(comments, bindings, body)
      val testcomments = (testexpression comments)
      (testcomments) should be(comments)
    }
  }

  "The body of a CLUniversal" should "be equal to the body parameter passed to the constructor" in {
    forAll("comment-symbols", "operator-string", minSuccessful(MIN_SUCCESSFUL / 2)) { (commentSymbols: List[String], operatorString: String) =>
      val commentsarray: Array[CLComment] = commentSymbols.map(s => new CLStringComment(s).asInstanceOf[CLComment]).toArray[CLComment]
      val comments = new CLCommentSetArray(commentsarray: _*)
      val comments0 = new CLCommentSetArray()
      val operator = new CLStringInterpretableName(operatorString)
      val termsequence = new CLTermSequenceArray(varx, varx)
      val body = new CLAtomicSentence(comments0, operator, termsequence)
      val bindings = new CLBindingSetArray(varx)
      val testexpression = new CLUniversal(comments, bindings, body)
      val testbody = (testexpression body)
      (testbody) should be(body)
    }
  }
}
