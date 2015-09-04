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

package scl

import cl2.functionconversions._
import org.scalatest.{ FunSuite, Matchers }
import cl2.xcl2.WellFormedXMLValidator._
import Generators._
import scala.language.postfixOps
import com.typesafe.scalalogging.LazyLogging

/**
 * Laws that must be obeyed by any `SCL.expression`.
 */
trait ExpressionLaws extends Laws with Matchers with LazyLogging {

  // TODO lift to ExpressionLike
  def expressionUsesLanguageIdentity: Prop = Prop.forAll { (expression: Expression) =>
    {
      (expression language) == (SCL LANG)
    }
  }

  // TODO lift to api4kbj.KnowledgeExpressionLike
  def expressionHasExpressionAbstractionLevelIdentity: Prop = Prop.forAll { (expression: Expression) =>
    (expression level) == EXPRESSION
  }

  // TODO lift to api4kbj.Immutable
  def expressionEqualsItselfIdentity: Prop = Prop.forAll { (expression: Expression) =>
    (expression.equals(expression))
  }

  // TODO lift to api4kbj.Immutable
  def expressionNotEqualNullIdentity: Prop = Prop.forAll { (expression: Expression) =>
    !(expression equals null)
  }

  def expression: RuleSet = new RuleSet {
    def name = "expression"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq()
    def props = Seq(
      ("A SCL.Expression uses the Common Logic language", expressionUsesLanguageIdentity),
      ("A SCL.Expression has the EXPRESSION abstraction level", expressionHasExpressionAbstractionLevelIdentity),
      ("A SCL.Expression equals itself", expressionEqualsItselfIdentity),
      ("A SCL.Expression is not equal null", expressionNotEqualNullIdentity))
  }

}

object ExpressionLaws extends ExpressionLaws

trait BasicExpressionLaws extends ExpressionLaws {

  // TODO lift to api4kbj.Basic
  def basicExpressionIsBasicIdentity: Prop = Prop.forAll { (bexpression: BasicExpression) =>
    (bexpression isBasic)
  }

  def basicExpressionNotEqualTOSMIdentity: Prop = Prop.forAll { ((bexpression: BasicExpression), (tosm: TermOrSequenceMarker)) =>
    !(bexpression equals tosm)
  }
  /*
  def basicEexpressionToStringIsWellFormedXMLIdentity: Prop = Prop.forAll { (bexpression: BasicExpression) =>
    {
      val xmlDeclaration = "<?xml version=\"1.1\"?>"
      val xmlBody = ((bexpression toXCL) replaceFirst (">", " xmlns:cl=\"" + SCL.URI_XCL2 + "\">"))
      val testxml = xmlDeclaration + xmlBody
      validate(testxml)
      true
    }

  }
*/
  def bexpression: RuleSet = new RuleSet {
    def name = "basic expression"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq()
    def props = Seq(
      ("A SCL.BasicExpression is basic (has no structure)", basicExpressionIsBasicIdentity),
      // ("SCL.BasicExpressions to String gives Well-formed XML", basicEexpressionToStringIsWellFormedXMLIdentity),
      ("SCL.BasicExpressions are Disjoint from SCL.Terms", basicExpressionNotEqualTOSMIdentity))
  }

}

object BasicExpressionLaws extends BasicExpressionLaws

trait TextLaws extends ExpressionLaws {

  def text: RuleSet = new RuleSet {
    def name = "text"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expression)
    def props = Seq()
  }

}

object TextLaws extends TextLaws

trait SentenceLaws extends ExpressionLaws {

  def sentence: RuleSet = new RuleSet {
    def name = "sentence"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expression)
    def props = Seq()
  }

}

object SentenceLaws extends SentenceLaws

object AtomicSentenceLaws extends SentenceLaws {

  def atomArgumentShouldNotBeNull: Prop = Prop.forAll { ((comments: Set[_ <: Comment]), (operator: Term), (terms: List[TermOrSequenceMarker])) =>
    {
      Prop.throws(classOf[NullPointerException]) {
        val atom = new AtomicSentence(null, operator, terms)
      }
      Prop.throws(classOf[NullPointerException]) {
        val atom = new AtomicSentence(comments, null, terms)
      }
      Prop.throws(classOf[NullPointerException]) {
        val atom = new AtomicSentence(comments, operator, null)
      }
      !Prop.throws(classOf[NullPointerException]) {
        val atom = new AtomicSentence(comments, operator, terms)
      }
    }
  }

  def atom: RuleSet = new RuleSet {
    def name = "atom"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq(
      ("Null Constructor Argument Exception", atomArgumentShouldNotBeNull))
  }
}

trait BooleanSentenceLaws extends SentenceLaws {

  def bool: RuleSet = new RuleSet {
    def name = "bool"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq()
  }

}

object BooleanSentenceLaws extends BooleanSentenceLaws

object BiconditionalLaws extends BooleanSentenceLaws {

  def bicond: RuleSet = new RuleSet {
    def name = "bicond"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq()
  }
}

object ConjunctionLaws extends BooleanSentenceLaws {

  def and: RuleSet = new RuleSet {
    def name = "and"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq()
  }
}

object DisjunctionLaws extends BooleanSentenceLaws {

  def or: RuleSet = new RuleSet {
    def name = "or"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq()
  }
}

trait QuantifiedSentenceLaws extends BooleanSentenceLaws {

  def quant: RuleSet = new RuleSet {
    def name = "quant"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq()
  }
}

object QuantifiedSentenceLaws extends QuantifiedSentenceLaws

object ExistentialLaws extends QuantifiedSentenceLaws {

  def exists: RuleSet = new RuleSet {
    def name = "exists"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(quant)
    def props = Seq()
  }
}
