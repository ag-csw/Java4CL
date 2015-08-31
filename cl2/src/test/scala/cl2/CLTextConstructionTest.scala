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

class CLTextConstructionTest extends FlatSpec with Matchers with PropertyChecks {
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
  val atom = new CLAtomicSentence(comments, operator, termsequence)
  val expressions = new CLExpressionSetArray(atom)
  val testexpression = new CLTextConstruction(comments, expressions)

  "The expression sequence of a CLTextConstruction" should "be equal to the parameter passed to the constructor" in {
    (testexpression expressions) should be(expressions)
  }

  "The comments of a CLTextConstruction" should "be equal to the parameter passed to the constructor" in {
    forAll("comment-symbols", "operator-string", minSuccessful(MIN_SUCCESSFUL / 2)) { (commentSymbols: List[String], operatorString: String) =>
      val commentsarray: Array[CLComment] = commentSymbols.map(s => new CLStringComment(s).asInstanceOf[CLComment]).toArray[CLComment]
      val comments = new CLCommentSetArray(commentsarray: _*)
      val testexpression = new CLTextConstruction(comments, expressions)
      val testcomments = (testexpression comments)
      (testcomments) should be(comments)
    }
  }

}
