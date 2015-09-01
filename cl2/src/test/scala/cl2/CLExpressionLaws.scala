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

import cl2.functionconversions._
import org.scalatest.{ FunSuite, Matchers }
import cl2.xcl2.WellFormedXMLValidator._
import CLGenerators._
import scala.language.postfixOps
import com.typesafe.scalalogging.LazyLogging

/**
 * Laws that must be obeyed by any `CL expression`.
 */
trait CLExpressionLaws extends Laws with Matchers with LazyLogging {

  val emptyComments = new CLCommentSetArray()

  def expressionUsesCLLanguageIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    {
      (expression language) == (CL LANG)
    }
  }

  // TODO lift to api4kbj.Basic
  def expressionIsBasicIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    (expression isBasic)
  }

  // TODO lift to api4kbj.KnowledgeExpressionLike
  def expressionHasExpressionAbstractionLevelIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    (expression level) == EXPRESSION
  }

  // TODO lift to api4kbj.Immutable
  def expressionEqualsItselfIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    (expression.equals(expression))
  }

  // TODO lift to api4kbj.Immutable
  def expressionEqualsCopyIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    val expression2 = (expression copy)
    (expression equals expression2) &&
      ((expression hashCode) equals (expression2 hashCode))
  }

  // TODO lift to api4kbj.Immutable
  def expressionNotEqualNullIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    !(expression equals null)
  }

  // TODO lift to CLCommentable
  def expressionEqualsCopyWithEmptyCommentsInsertedIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    {
      val expression2 = expression.insertComments(emptyComments)
      (expression equals expression2) &&
        ((expression hashCode) equals (expression2 hashCode))
    }
  }

  def expressionNotEqualTOSMIdentity: Prop = Prop.forAll { ((expression: CLExpression), (tosm: CLTermOrSequenceMarker)) =>
    !(expression equals tosm)
  }

  def expressionToStringIsWellFormedXMLIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    {
      val xmlDeclaration = "<?xml version=\"1.1\"?>"
      val xmlBody = ((expression toString) replaceFirst (">", " xmlns:cl=\"" + CL.URI_XCL2 + "\">"))
      val testxml = xmlDeclaration + xmlBody
      validate(testxml)
      true
    }

  }

  def expression: RuleSet = new RuleSet {
    def name = "expression"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq()
    def props = Seq(
      ("A CLExpression is basic (has no structure)", expressionIsBasicIdentity),
      ("A CLExpression uses the Common Logic language", expressionUsesCLLanguageIdentity),
      ("A CLExpression has the EXPRESSION abstraction level", expressionHasExpressionAbstractionLevelIdentity),
      ("A CL Expression equals itself", expressionEqualsItselfIdentity),
      ("A CL Expression equals copy", expressionEqualsCopyIdentity),
      ("A CL Expression is not equal null", expressionNotEqualNullIdentity),
      ("A CL Expression equals copy with empty comments inserted", expressionEqualsCopyWithEmptyCommentsInsertedIdentity),
      ("CL Expressions are Disjoint from CL Terms", expressionNotEqualTOSMIdentity),
      ("CL Expressions to String gives Well-formed XML", expressionToStringIsWellFormedXMLIdentity))
  }

}

object CLExpressionLaws extends CLExpressionLaws

trait CLTextLaws extends CLExpressionLaws {

  def textNotEqualSentenceIdentity: Prop = Prop.forAll { ((text: CLText), (sentence: CLSentence)) =>
    !(text equals sentence)
  }

  def text: RuleSet = new RuleSet {
    def name = "text"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expression)
    def props = Seq(
      ("CL Texts  are Disjoint from CL Sentences", textNotEqualSentenceIdentity))
  }

}

object CLTextLaws extends CLTextLaws

trait CLSentenceLaws extends CLExpressionLaws {

  def sentenceNotEqualTextIdentity: Prop = Prop.forAll { ((sent: CLSentence), (text: CLText)) =>
    !(sent equals text)
  }

  def sentence: RuleSet = new RuleSet {
    def name = "sentence"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expression)
    def props = Seq(
      ("CL Sentences are Disjoint from CL Texts", sentenceNotEqualTextIdentity))
  }

}

object CLSentenceLaws extends CLSentenceLaws

object CLAtomicSentenceLaws extends CLSentenceLaws {

  def expressionArgumentShouldNotBeNull: Prop = Prop.forAll { ((comments: CLCommentSet), (operator: CLTerm), (terms: CLTermSequence)) =>
    {
      Prop.throws(classOf[NullPointerException]) {
        val atom = new CLAtomicSentence(null, operator, terms)
      }
      Prop.throws(classOf[NullPointerException]) {
        val atom = new CLAtomicSentence(comments, null, terms)
      }
      Prop.throws(classOf[NullPointerException]) {
        val atom = new CLAtomicSentence(comments, operator, null)
      }
      !Prop.throws(classOf[NullPointerException]) {
        val atom = new CLAtomicSentence(comments, operator, terms)
      }
    }
  }

  def atomIdentityIdentity: Prop = Prop.forAll { (atom: CLAtomicSentence) =>
    {
      // val f1:Function[CLCommentSet, CLCommentSet] = {s:CLCommentSet => s}
      // val f2:Function[CLTerm, CLTerm] = {s:CLTerm => s}
      // val f3:Function[CLTermSequence, CLTermSequence] = {s:CLTermSequence => s}
      val atom2 = atom.copy(
        { s: CLCommentSet => s }, { s: CLTerm => s }, { s: CLTermSequence => s })

      (atom equals atom2) &&
        ((atom hashCode) equals (atom2 hashCode))
    }
  }

  def atomComposeIdentity(
    atom: CLAtomicSentence,
    f1: Function[CLCommentSet, CLCommentSet],
    f2: Function[CLTerm, CLTerm],
    f3: Function[CLTermSequence, CLTermSequence],
    g1: Function[CLCommentSet, CLCommentSet],
    g2: Function[CLTerm, CLTerm],
    g3: Function[CLTermSequence, CLTermSequence]): Prop = Prop.forAll { (atom: CLAtomicSentence) =>
    {
      val atom1 = atom.copy(f1, f2, f3).copy(g1, g2, g3)

      val atom2 = atom.copy(
        (f1 compose g1),
        (f2 compose g2),
        (f3 compose g3))

      (atom1 equals atom2)
    }
  }

  def atom: RuleSet = new RuleSet {
    def name = "atom"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq(
      ("Null Constructor Argument Exception", expressionArgumentShouldNotBeNull),
      ("Identity Copy", atomIdentityIdentity))
  }
}

trait CLBooleanSentenceLaws extends CLSentenceLaws {

  def booleanSentenceNotEqualAtomIdentity: Prop = Prop.forAll { ((sent: CLBooleanSentence), (atom: CLAtomicSentence)) =>
    !(sent equals atom) && !(atom equals sent)
  }

  def bool: RuleSet = new RuleSet {
    def name = "bool"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq(
      ("Boolean Sentence Disjoint with Atom", booleanSentenceNotEqualAtomIdentity))
  }

}

object CLBooleanSentenceLaws extends CLBooleanSentenceLaws

object CLBiconditionalLaws extends CLBooleanSentenceLaws {

  def biconditionalIdentityIdentity: Prop = Prop.forAll { (bicond: CLBiconditional) =>
    {
      val bicond2 = bicond.copy(
        { s: CLCommentSet => s }, { s: CLSentenceSet => s })

      (bicond equals bicond2) &&
        ((bicond hashCode) equals (bicond2 hashCode))
    }
  }

  def bicond: RuleSet = new RuleSet {
    def name = "bicond"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq(
      ("Identity Copy", biconditionalIdentityIdentity))
  }
}

object CLConjunctionLaws extends CLBooleanSentenceLaws {

  def conjunctionIdentityIdentity: Prop = Prop.forAll { (and: CLConjunction) =>
    val and2 = and.copy(
      { s: CLCommentSet => s }, { s: CLSentenceSet => s })

    (and equals and2) &&
      ((and hashCode) equals (and2 hashCode))
  }

  def conjunctionNotEqualBiconditionalIdentity: Prop = Prop.forAll { ((and: CLConjunction), (bicond: CLBiconditional)) =>
    !(and equals bicond)
  }

  def and: RuleSet = new RuleSet {
    def name = "and"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq(
      ("Conjunction Disjoint with Biconditional", conjunctionNotEqualBiconditionalIdentity),
      ("Identity Copy", conjunctionIdentityIdentity))
  }
}

object CLDisjunctionLaws extends CLBooleanSentenceLaws {

  def disjunctionNotEqualBiconditionalIdentity: Prop = Prop.forAll { ((or: CLDisjunction), (bicond: CLBiconditional)) =>
    !(or equals bicond)
  }

  def disjunctionNotEqualConjunctionIdentity: Prop = Prop.forAll { ((or: CLDisjunction), (and: CLConjunction)) =>
    !(or equals and)
  }

  def disjunctionIdentityIdentity: Prop = Prop.forAll { (or: CLDisjunction) =>
    val or2 = or.copy(
      { s: CLCommentSet => s }, { s: CLSentenceSet => s })

    (or equals or2) &&
      ((or hashCode) equals (or2 hashCode))
  }

  def or: RuleSet = new RuleSet {
    def name = "or"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq(
      ("Disjunction Disjoint with Biconditional", disjunctionNotEqualBiconditionalIdentity),
      ("Disjunction Disjoint with Conjunction", disjunctionNotEqualConjunctionIdentity),
      ("Identity Copy", disjunctionIdentityIdentity))
  }
}

trait CLQuantifiedSentenceLaws extends CLBooleanSentenceLaws {

  def quantifiedNotEqualBiconditionalIdentity: Prop = Prop.forAll {
    ((quant: CLQuantifiedSentence), (bicond: CLBiconditional)) =>
      !(quant equals bicond) && !(bicond equals quant)
  }

  def quantifiedNotEqualConjunctionIdentity: Prop = Prop.forAll {
    ((quant: CLQuantifiedSentence), (and: CLConjunction)) =>
      !(quant equals and) && !(and equals quant)
  }

  def quantifiedNotEqualDisjunctionIdentity: Prop = Prop.forAll {
    ((quant: CLQuantifiedSentence), (or: CLDisjunction)) =>
      !(quant equals or) && !(or equals quant)
  }

  def quantIdentityIdentity: Prop = Prop.forAll { (quant: CLQuantifiedSentence) =>
    {
      val quant2 = quant.copy(
        { s: CLCommentSet => s }, { s: CLBindingSet => s }, { s: CLSentence => s })

      (quant equals quant2) &&
        ((quant hashCode) equals (quant2 hashCode))
    }
  }

  def quant: RuleSet = new RuleSet {
    def name = "quant"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(sentence)
    def props = Seq(
      ("Quantified Disjoint with Biconditional", quantifiedNotEqualBiconditionalIdentity),
      ("Quantified Disjoint with Conjunction", quantifiedNotEqualConjunctionIdentity),
      ("Quantified Disjoint with Disjunction", quantifiedNotEqualDisjunctionIdentity),
      ("Identity Copy", quantIdentityIdentity))
  }
}

object CLQuantifiedSentenceLaws extends CLQuantifiedSentenceLaws

object CLExistentialLaws extends CLQuantifiedSentenceLaws {

  def exists: RuleSet = new RuleSet {
    def name = "exists"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(quant)
    def props = Seq()
  }
}
