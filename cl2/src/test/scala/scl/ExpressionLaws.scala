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

import api4kbj.KRRLanguage

import cl2.functionconversions._
import org.scalatest.{ FunSuite, Matchers }
import cl2.xcl2.WellFormedXMLValidator._
import Generators._
import scala.language.postfixOps
import com.typesafe.scalalogging.LazyLogging
import scala.util.{ Try, Success, Failure }
import cats._, cats.std.all._, cats.syntax.eq._
import cats.laws._
import ExpressionEq._
import SXCLManifester._

/**
 * Laws that must be obeyed by any `SCL.expression`.
 */
trait ExpressionLaws[T <: Expression] extends Laws with Matchers with LazyLogging {

  // TODO lift to ExpressionLike
  def expressionUsesLanguageIdentity(expression: T): IsEq[KRRLanguage] =
    (expression language) <-> (SCL LANG)

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
      ("A SCL.Expression has the EXPRESSION abstraction level", expressionHasExpressionAbstractionLevelIdentity),
      ("A SCL.Expression equals itself", expressionEqualsItselfIdentity),
      ("A SCL.Expression is not equal null", expressionNotEqualNullIdentity))
  }

}

object ExpressionLaws {
  def apply[T <: Expression]: ExpressionLaws[T] =
    new ExpressionLaws[T] {}
}

trait BasicExpressionLaws extends ExpressionLaws[BasicExpression] {

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
      case a: AtomicSentence        => a.copy(comments = a.comments, operator = a.operator, args = a.args)
      case b: Biconditional         => b.copy(comments = b.comments, sentences = b.sentences)
      case c: Conjunction           => c.copy(comments = c.comments, conjuncts = c.conjuncts)
      case d: Disjunction           => d.copy(comments = d.comments, disjuncts = d.disjuncts)
      case e: Equation              => e.copy(comments = e.comments, terms = e.terms)
      case n: Implication           => n.copy(comments = n.comments, antecedent = n.antecedent, consequent = n.consequent)
      case n: Negation              => n.copy(comments = n.comments, body = n.body)
      case u: Universal             => u.copy(comments = u.comments, bindings = u.bindings, body = u.body)
      case x: Existential           => x.copy(comments = x.comments, bindings = x.bindings, body = x.body)
      case s: InDiscourseStatement  => s.copy(comments = s.comments, terms = s.terms)
      case s: OutDiscourseStatement => s.copy(comments = s.comments, terms = s.terms)
      case s: Titling               => s.copy(comments = s.comments, title = s.title, body = s.body)
      case x: Schema                => x.copy(comments = x.comments, seqbindings = x.seqbindings, body = x.body)
      case t: TextConstruction      => t.copy(comments = t.comments, expressions = t.expressions)
      case s: DomainRestriction     => s.copy(comments = s.comments, domain = s.domain, body = s.body)
      case s: Importation           => s.copy(comments = s.comments, title = s.title)
    }
    ((bexpression1 equals bexpression2)
      && (bexpression2 equals bexpression1)
      && (bexpression1 === bexpression2))

  }

  def basicEexpressionToStringIsWellFormedXMLIdentity: Prop = Prop.forAll { (bexpression: BasicExpression) =>
    {
      val xmlDeclaration = "<?xml version=\"1.1\"?>"
      val testxml = xmlDeclaration + ((bexpression toXML) toString)
      validate(testxml)
      true
    }

  }

  def basicXMLIdentity: Prop = Prop.forAll { (basic: BasicExpression) =>
    !Prop.throws(classOf[Exception]) {
      try {
        logger.debug(basic toString)
        logger.debug("Starting Conversion to XML")
        val xmlBody = (basic toXML)
        logger.debug("Starting Conversion to String")
        val xmlStringBody = (xmlBody toString)
        logger.debug(xmlStringBody)
        val xmlDeclaration = "<?xml version=\"1.1\"?>"
        val testxml = xmlDeclaration + xmlStringBody
        validate(testxml)
      } catch {
        case e: Exception => {
          logger.debug("FAILURE:  basicXMLIdentity " + (e getCause))
          throw new Exception()
        }
      }
    }
  }

  def basicExpressionCommentIsMatchableIdentity: Prop = Prop.forAll { (e: BasicExpression) =>
    !Prop.throws(classOf[Exception]) {
      e match {
        case BasicExpression(comments) => (comments size)
        case _                         => None
      }
    }
  }

  def basicExpressionisShowableIdentity: Prop = Prop.forAll { (e: BasicExpression) =>
    !Prop.throws(classOf[Exception]) {
      true
      //      e.show
    }
  }

  def basicExpressionXMLRoundTripIdentity(implicit ev: SXCLParser[BasicExpression]): Prop = Prop.forAll { (basic: BasicExpression) =>
    {
      logger.debug("BasicExpression RoundTrip Identity Input : " + (basic toString))
      logger.debug("BasicExpression RoundTrip Identity XML   : " + (basic toXML))
      val otherbasic: BasicExpression = (ev.fromXML(basic toXML)).get
      logger.debug("BasicExpression RoundTrip Identity Output: " + (otherbasic toString))
      logger.debug("BasicExpression RoundTrip Identity XML   : " + (otherbasic toXML))
      (otherbasic equals basic)
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
      ("SCL.BasicExpressions Can Be Expressed in XCL2", basicXMLIdentity),
      ("SCL.BasicExpressions Can Be Expressed in Well-Formed XCL2", basicEexpressionToStringIsWellFormedXMLIdentity),
      ("SCL.BasicExpressions Can Be Round-Tripped through XCL2", basicExpressionXMLRoundTripIdentity),
      ("SCL.BasicExpressions are Disjoint from SCL.Terms", basicExpressionNotEqualTOSMIdentity))
  }

}

object BasicExpressionLaws extends BasicExpressionLaws

trait TextLaws extends ExpressionLaws[Text] {

  def textCommentIsMatchableIdentity: Prop = Prop.forAll { (e: BasicExpression) =>
    !Prop.throws(classOf[Exception]) {
      e match {
        case Text(comments) => (comments size)
        case _              => None
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

object TextConstructionLaws extends TextLaws {

  def construct: RuleSet = new RuleSet {
    def name = "construct"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expression)
    def props = Seq()
  }

}

trait StatementLaws extends ExpressionLaws[Statement] {

  def statementIsCommentableIdentity: Prop = Prop.forAll { (s: Statement) =>
    !Prop.throws(classOf[Exception]) {
      (s comments)
    }
  }

  def statementCommentIsMatchableIdentity: Prop = Prop.forAll { (e: BasicExpression) =>
    !Prop.throws(classOf[Exception]) {
      e match {
        case Statement(comments) => true
        case _                   => false
      }
    }
  }

  def statement: RuleSet = new RuleSet {
    def name = "statement"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expression)
    def props = Seq(
      ("A SCL.Statement is Commentable.", statementIsCommentableIdentity),
      ("A SCL.Statement Comment set is pattern-matchable.", statementCommentIsMatchableIdentity))
  }

}

object StatementLaws extends StatementLaws

trait SentenceLaws extends ExpressionLaws[Sentence] {

  def sentenceIsCommentableIdentity: Prop = Prop.forAll { (s: Sentence) =>
    !Prop.throws(classOf[Exception]) {
      (s comments)
    }
  }

  def sentenceCommentIsMatchableIdentity: Prop = Prop.forAll { (e: BasicExpression) =>
    !Prop.throws(classOf[Exception]) {
      e match {
        case Sentence(comments) => true
        case _                  => false
      }
    }
  }

  def sentenceXMLRoundTripIdentity(implicit ev: SXCLParser[Sentence]): Prop =
    Prop.forAll { (sent: Sentence) =>
      {
        logger.debug("Sentence RoundTrip Identity Input : " + (sent toString))
        logger.debug("Sentence RoundTrip Identity XML   : " + (sent toXML))
        val othersent: Sentence = (ev.fromXML(sent toXML)).get
        logger.debug("Sentence RoundTrip Identity Output: " + (othersent toString))
        logger.debug("Sentence RoundTrip Identity XML   : " + (othersent toXML))
        (othersent equals sent)
      }
    }

  // TODO move to ManifestationLaws
  def sentenceXMLParserCheckIdentity(implicit ev: SXCLParser[Sentence]): Prop =
    Prop.forAll { (e: Statement) => (ev.fromXML(e toXML)).isFailure
    }

  def sentence: RuleSet = new RuleSet {
    def name = "sentence"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expression)
    def props = Seq(
      ("A SCL.Sentence is Commentable.", sentenceIsCommentableIdentity),
      ("The SCL.Sentence Parser fails when passed a non-Sentence XML Element.",
        sentenceXMLParserCheckIdentity),
      ("A SCL.Sentence Comment set is pattern-matchable.",
        sentenceCommentIsMatchableIdentity))
  }

}

object SentenceLaws extends SentenceLaws

trait SimpleSentenceLaws extends SentenceLaws {

  def simpleSentenceCommentIsMatchableIdentity: Prop = Prop.forAll { (e: Sentence) =>
    !Prop.throws(classOf[Exception]) {
      e match {
        case SimpleSentence(comments) => (comments size)
        case _                        => None
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

  def atomArgumentShouldNotBeNull: Prop = Prop.forAll { ((comments: CommentSet), (operator: Term), (terms: TermSequence)) =>
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
      logger.debug(x toString)
    }
  }

  def atomXMLRoundTripNumberOfCommentsIdentity(implicit ev: SXCLParser[AtomicSentence]): Prop = Prop.forAll { (atom: AtomicSentence) =>
    {
      val atom2: AtomicSentence = (ev.fromXML(atom toXML)).get
      (((atom2 comments) size) equals ((atom comments) size))
    }
  }

  def atomXMLRoundTripIdentity(implicit ev: SXCLParser[AtomicSentence]): Prop =
    Prop.forAll { (atom: AtomicSentence) =>
      {
        val atom2: AtomicSentence = (ev.fromXML(atom toXML)).get
        (atom2 equals atom)
      }
    }

  /* This test fails randomly. Scalas XML support is bad.
  def emptyNamespaceCheck(implicit ev: SXCLParser[BasicExpressionLike]): Prop =
    Prop.forAll { (atom: AtomicSentence) =>
      {
        val atomXML: scala.xml.Elem = (atom toXML)
        val op: scala.xml.Elem = (ev.xmlOperator(atomXML)).get
        (op.getNamespace(null) == null)
      }
    }
    */

  def atom: RuleSet = new RuleSet {
    def name = "atom"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(simple)
    def props = Seq(
      ("Null Constructor Argument Exception", atomArgumentShouldNotBeNull),
      ("Atomic Sentences Can Be Expressed in XCL2", atomXMLIdentity),
      ("Number of Comments is Preserved when converting to XML and the parsing", atomXMLRoundTripNumberOfCommentsIdentity),
      //("Nested Element has no namespace for the default prefix", emptyNamespaceCheck),
      ("Atomic Sentence is Preserved when converting to XML and the parsing", atomXMLRoundTripIdentity))
  }
}

object EquationLaws extends SimpleSentenceLaws {

  def equalUnorientedIdentity: Prop = Prop.forAll {
    (
    (comments: CommentSet),
    (left: Term),
    (right: Term)) =>
      (new Equation(comments, left, right) equals new Equation(comments, right, left))

  }

  def equalXMLIdentity: Prop = Prop.forAll { (eq: Equation) =>
    !Prop.throws(classOf[Exception]) {
      val x = (eq toXML)
      logger.debug((x toString))
    }
  }

  def eqXMLRoundTripIdentity(implicit ev: SXCLParser[Equation]): Prop = Prop.forAll { (eq: Equation) =>
    {
      val eq2: Equation = (ev.fromXML(eq toXML)).get
      (eq2 equals eq)
    }
  }

  def equals: RuleSet = new RuleSet {
    def name = "equals"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq(
      ("A SCL.Equation is unoriented with respect to its term arguments", equalUnorientedIdentity),
      ("Equation Can Be Expressed in XCL2", equalXMLIdentity),
      ("Equation is Preserved when converting to XML and the parsing", eqXMLRoundTripIdentity))
  }
}

trait BooleanSentenceLaws extends SentenceLaws {

  def booleanSentenceCommentIsMatchableIdentity: Prop = Prop.forAll { (e: Sentence) =>
    !Prop.throws(classOf[Exception]) {
      e match {
        case BooleanSentence(comments) => (comments size)
        case _                         =>
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
    (comments: CommentSet),
    (left: Sentence),
    (right: Sentence)) =>
      (new Biconditional(comments, left, right) equals new Biconditional(comments, right, left))
  }

  def bicondXMLIdentity: Prop = Prop.forAll { (s: Biconditional) =>
    !Prop.throws(classOf[Exception]) {
      val x = (s toXML)
      logger.debug((x toString))
    }
  }

  def bicondXMLRoundTripIdentity(implicit ev: SXCLParser[Biconditional]): Prop = Prop.forAll { (bicond: Biconditional) =>
    {
      logger.debug("Bicond RoundTrip Identity Input : " + (bicond toString))
      logger.debug("Bicond RoundTrip Identity XML   : " + (bicond toXML))
      val bicond2: Biconditional = (ev.fromXML(bicond toXML)).get
      logger.debug("Bicond RoundTrip Identity Output: " + (bicond2 toString))
      logger.debug("Bicond RoundTrip Identity XML   : " + (bicond2 toXML))
      (bicond2 equals bicond)
    }
  }

  def bicond: RuleSet = new RuleSet {
    def name = "bicond"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq(
      ("A SCL.Biconditional is unoriented with respect to its sentence arguments", bicondUnorientedIdentity),
      ("Biconditionals Can Be Expressed in XCL2", bicondXMLIdentity),
      ("Biconditional is Preserved when converting to XML and the parsing", bicondXMLRoundTripIdentity))
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

trait QuantifiedSentenceLaws extends SentenceLaws {

  def quantifiedSentenceCommentIsMatchableIdentity: Prop = Prop.forAll { (e: Sentence) =>
    !Prop.throws(classOf[Exception]) {
      e match {
        case QuantifiedSentence(comments, _, _) => (comments size)
        case _                                  =>
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
