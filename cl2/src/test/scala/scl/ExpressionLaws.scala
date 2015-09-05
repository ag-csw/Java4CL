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
import XMLHelper._

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

  def basicExpressionEqualityDependsOnCommentsIdentity: Prop = Prop.forAll { ((bexpression1: BasicExpression), (bexpression2: BasicExpression)) =>
    (((bexpression1 comments) equals (bexpression2 comments)) || !(bexpression1 equals bexpression2))
  }

  def basicExpressionIsEqualToItsCopyIdentity: Prop = Prop.forAll { (bexpression1: BasicExpression) =>
    val bexpression2 = bexpression1 match {
      case a: AtomicSentence => a.copy(comments = a.comments, operator = a.operator, args = a.args)
      case b: Biconditional => b.copy(comments = b.comments, sentences = b.sentences)
      case c: Conjunction => c.copy(comments = c.comments, conjuncts = c.conjuncts)
      case d: Disjunction => d.copy(comments = d.comments, disjuncts = d.disjuncts)
      case e: Equation => e.copy(comments = e.comments, terms = e.terms)
      case n: Implication => n.copy(comments = n.comments, antecedent = n.antecedent, consequent = n.consequent)
      case n: Negation => n.copy(comments = n.comments, body = n.body)
      case u: Universal => u.copy(comments = u.comments, bindings = u.bindings, body = u.body)
      case x: Existential => x.copy(comments = x.comments, bindings = x.bindings, body = x.body)
      case s: InDiscourseStatement => s.copy(comments = s.comments, terms = s.terms)
      case s: OutDiscourseStatement => s.copy(comments = s.comments, terms = s.terms)
      case s: Titling => s.copy(comments = s.comments, title = s.title, body = s.body)
      case x: Schema => x.copy(comments = x.comments, seqbindings = x.seqbindings, body = x.body)
      case t: TextConstruction => t.copy(comments = t.comments, expressions = t.expressions)
      case s: DomainRestriction => s.copy(comments = s.comments, domain = s.domain, body = s.body)
      case s: Importation => s.copy(comments = s.comments, title = s.title)
    }
    ((bexpression1 equals bexpression2) && (bexpression2 equals bexpression1))
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

  def basicExpressionCommentIsMatchableIdentity: Prop = Prop.forAll { (e: BasicExpressionLike) =>
    !Prop.throws(classOf[Exception]) {
      e match {
        case BasicExpression(comments) => (comments size)
        case _ => None
      }
    }
  }

  def bexpression: RuleSet = new RuleSet {
    def name = "basic expression"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq()
    def props = Seq(
      ("A SCL.BasicExpression is basic (has no structure)", basicExpressionIsBasicIdentity),
      ("SCL.BasicExpression Equality Depends on Comments", basicExpressionEqualityDependsOnCommentsIdentity),
      ("A SCL.BasicExpression Equals its Copy", basicExpressionIsEqualToItsCopyIdentity),
      ("A SCL.BasicExpression Comment set is Pattern-Matchable", basicExpressionCommentIsMatchableIdentity),
      // ("SCL.BasicExpressions to String gives Well-formed XML", basicEexpressionToStringIsWellFormedXMLIdentity),
      ("SCL.BasicExpressions are Disjoint from SCL.Terms", basicExpressionNotEqualTOSMIdentity))
  }

}

object BasicExpressionLaws extends BasicExpressionLaws

trait TextLaws extends ExpressionLaws {

  def textCommentIsMatchableIdentity: Prop = Prop.forAll { (e: BasicExpression) =>
    !Prop.throws(classOf[Exception]) {
      e match {
        case Text(comments) => (comments size)
        case _ => None
      }
    }
  }

  def text: RuleSet = new RuleSet {
    def name = "text"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expression)
    def props = Seq(
      ("A SCL.Text Comment set is Pattern-Matchable", textCommentIsMatchableIdentity))
  }

}

object TextLaws extends TextLaws

trait SentenceLaws extends ExpressionLaws {

  def sentenceIsCommentableIdentity: Prop = Prop.forAll { (s: Sentence) =>
    !Prop.throws(classOf[Exception]) {
      (s comments)
    }
  }

  def sentenceCommentIsMatchableIdentity: Prop = Prop.forAll { (e: BasicExpression) =>
    !Prop.throws(classOf[Exception]) {
      e match {
        case Sentence(comments) => true
        case _ => false
      }
    }
  }

  def sentence: RuleSet = new RuleSet {
    def name = "sentence"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expression)
    def props = Seq(
      ("A SCL.Sentence is Commentable.", sentenceIsCommentableIdentity),
      ("A SCL.Sentence Comment set is pattern-matchable.", sentenceCommentIsMatchableIdentity))
  }

}

object SentenceLaws extends SentenceLaws

trait SimpleSentenceLaws extends SentenceLaws {

  def simpleSentenceCommentIsMatchableIdentity: Prop = Prop.forAll { (e: Sentence) =>
    !Prop.throws(classOf[Exception]) {
      e match {
        case SimpleSentence(comments) => (comments size)
        case _ => None
      }
    }
  }

  def simple: RuleSet = new RuleSet {
    def name = "simple"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq(
      ("A SCL.SimpleSentence Comment set is pattern-matchable.", simpleSentenceCommentIsMatchableIdentity))

  }

}

object SimpleSentenceLaws extends SimpleSentenceLaws

object AtomicSentenceLaws extends SimpleSentenceLaws {

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

  def atomXMLIdentity: Prop = Prop.forAll { (atom: AtomicSentence) =>
    !Prop.throws(classOf[Exception]) {
      val x = (atom toXML)
      println((x toString))
    }
  }

  def atom: RuleSet = new RuleSet {
    def name = "atom"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(simple)
    def props = Seq(
      ("Null Constructor Argument Exception", atomArgumentShouldNotBeNull),
      ("Atomic Sentences Can Be Expressed in XCL2", atomXMLIdentity))
  }
}

object EquationLaws extends SimpleSentenceLaws {

  def equalUnorientedIdentity: Prop = Prop.forAll {
    (
    (comments: Set[_ <: Comment]),
    (left: Term),
    (right: Term)) =>
      (new Equation(comments, left, right) equals new Equation(comments, right, left))

  }

  def equals: RuleSet = new RuleSet {
    def name = "equals"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq(
      ("A SCL.Equation is unoriented with respect to its term arguments", equalUnorientedIdentity))
  }
}

trait BooleanSentenceLaws extends SentenceLaws {

  def booleanSentenceCommentIsMatchableIdentity: Prop = Prop.forAll { (e: Sentence) =>
    !Prop.throws(classOf[Exception]) {
      e match {
        case BooleanSentence(comments) => (comments size)
        case _ =>
      }
    }
  }

  def bool: RuleSet = new RuleSet {
    def name = "bool"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq(
      ("A SCL.BooleanSentence Comment set is pattern-matchable.", booleanSentenceCommentIsMatchableIdentity))
  }

}

object BooleanSentenceLaws extends BooleanSentenceLaws

object BiconditionalLaws extends BooleanSentenceLaws {

  def bicondUnorientedIdentity: Prop = Prop.forAll {
    (
    (comments: Set[_ <: Comment]),
    (left: Sentence),
    (right: Sentence)) =>
      (new Biconditional(comments, left, right) equals new Biconditional(comments, right, left))
  }

  def bicond: RuleSet = new RuleSet {
    def name = "bicond"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq(
      ("A SCL.Biconditional is unoriented with respect to its sentence arguments", bicondUnorientedIdentity))
  }
}

object ImplicationLaws extends BooleanSentenceLaws {

  def implies: RuleSet = new RuleSet {
    def name = "implies"
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

object NegationLaws extends BooleanSentenceLaws {

  def neg: RuleSet = new RuleSet {
    def name = "neg"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq()
  }
}

trait QuantifiedSentenceLaws extends BooleanSentenceLaws {

  def quantifiedSentenceCommentIsMatchableIdentity: Prop = Prop.forAll { (e: BooleanSentence) =>
    !Prop.throws(classOf[Exception]) {
      e match {
        case QuantifiedSentence(comments, _, _) => (comments size)
        case _ =>
      }
    }
  }

  def quant: RuleSet = new RuleSet {
    def name = "quant"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq(
      ("A SCL.QuantifiedSentence Comment set is pattern-matchable.", quantifiedSentenceCommentIsMatchableIdentity))
  }
}

object QuantifiedSentenceLaws extends QuantifiedSentenceLaws

object UniversalLaws extends QuantifiedSentenceLaws {

  def forall: RuleSet = new RuleSet {
    def name = "forall"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(quant)
    def props = Seq()
  }
}

object ExistentialLaws extends QuantifiedSentenceLaws {

  def eggsist: RuleSet = new RuleSet {
    def name = "eggsist"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(quant)
    def props = Seq()
  }
}
