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

import collection.JavaConversions._
import org.scalatest.prop.PropertyChecks
import org.scalatest.{ FlatSpec, Matchers }
import scala.language.postfixOps

class CLInDiscourseStatementTest extends FlatSpec with Matchers with PropertyChecks {
  implicit override val generatorDrivenConfig =
    PropertyCheckConfig(
      minSuccessful = MIN_SUCCESSFUL,
      maxDiscarded = MAX_DISCARDED,
      minSize = MIN_SIZE,
      maxSize = MAX_SIZE,
      workers = WORKERS)
  "A CLInDiscourseStatement" should "be basic" in {
    val comments = new CLCommentSetArray()

    val termsequence = new CLTermSequenceArray()
    val testexpression = new CLInDiscourseStatement(comments, termsequence)
    (testexpression.isBasic()) should be(true)
  }

  "A CLInDiscourseStatement" should "use language CL" in {
    val lang = CL.LANG
    val comments = new CLCommentSetArray()

    val termsequence = new CLTermSequenceArray()
    val testexpression = new CLInDiscourseStatement(comments, termsequence)
    (testexpression.language()) should be(lang)
  }

  "A CLInDiscourseStatement" should "have knowledge source level EXPRESSION" in {
    val comments = new CLCommentSetArray()

    val termsequence = new CLTermSequenceArray()
    val testexpression = new CLInDiscourseStatement(comments, termsequence)
    (testexpression.level()) should be(EXPRESSION)
  }

  "The term sequence of a CLInDiscourseStatement" should "be equal to the parameter passed to the term constructor" in {
    forAll("arg-string") { (argString: String) =>
      val comments = new CLCommentSetArray()

      val term1 = new CLStringInterpretableName(argString)
      val termsequence = new CLTermSequenceArray(term1)
      val testexpression = new CLInDiscourseStatement(comments, termsequence)
      val statementargs = testexpression.args()
      (statementargs) should be(termsequence)
      val statementargsscala: Iterable[CLTermOrSequenceMarker] = statementargs.args()
      val statementnameterm1 = statementargsscala.head match {
        case statementargsname: CLName => statementargsname
        case _ => throw new ClassCastException
      }
      (statementnameterm1.symbol()) should be(argString)
    }
  }

  "The term sequence of a CLInDiscourseStatement constructed from a length two term sequence" should "have length two" in {
    forAll("arg-string1", "arg-string2") { (argString1: String, argString2: String) =>
      val comments = new CLCommentSetArray()

      val term1 = new CLStringInterpretableName(argString1)
      val term2 = new CLStringInterpretableName(argString2)
      val termsequence = new CLTermSequenceArray(term1, term2)
      val testexpression = new CLInDiscourseStatement(comments, termsequence)
      val statementargs = testexpression.args()
      (statementargs.length()) should be(termsequence.length())
    }
  }
  "The term sequence of a CLInDiscourseStatement" should "have the same length as the one passed to the term constructor" in {
    forAll("symbols") { (symbols: List[String]) =>
      val comments = new CLCommentSetArray()

      val terms: List[CLTermOrSequenceMarker] = symbols.map(s => new CLStringInterpretableName(s))
      var termsequence: CLTermSequenceArray = new CLTermSequenceArray()
      for { term <- terms } {
        termsequence = termsequence.concat(new CLTermSequenceArray(term))
      }
      val testexpression = new CLInDiscourseStatement(comments, termsequence)
      ((testexpression args) length) should be(termsequence length)
    }
  }
}
