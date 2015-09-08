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

import org.scalatest.{ FunSuite, Matchers }
import Generators._
import scala.language.postfixOps
import com.typesafe.scalalogging.LazyLogging

/**
 * Laws that must be obeyed by any `SCL expression-like entity`.
 */
trait ExpressionLikeLaws extends Laws with Matchers with LazyLogging {

  def expressionlikeUsesLanguageIdentity: Prop = Prop.forAll { (clentity: ExpressionLike) =>
    {
      (clentity language) == SCL.LANG
    }
  }

  // TODO lift to api4kbj.KnowledgeExpressionLike
  def expressionlikeHasExpressionAbstractionLevelIdentity: Prop = Prop.forAll { (clentity: ExpressionLike) =>
    (clentity level) == EXPRESSION
  }

  // TODO lift to api4kbj.Immutable
  def expressionlikeEqualsItselfIdentity: Prop = Prop.forAll { (clentity: ExpressionLike) =>
    (clentity equals clentity)
  }

  // TODO lift to api4kbj.Immutable
  def expressionlikeNotEqualNullIdentity: Prop = Prop.forAll { (clentity: ExpressionLike) =>
    !(clentity equals null)
  }

  def expressionlike: RuleSet = new RuleSet {
    def name = "expressionlike"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq()
    def props = Seq(
      ("SCL ExpressionLike uses the Common Logic language", expressionlikeUsesLanguageIdentity),
      ("SCL ExpressionLike has the EXPRESSION abstraction level", expressionlikeHasExpressionAbstractionLevelIdentity),
      ("SCL ExpressionLike equals itself", expressionlikeEqualsItselfIdentity),
      ("SCL ExpressionLike is not equal null", expressionlikeNotEqualNullIdentity))
  }

}

object ExpressionLikeLaws extends ExpressionLikeLaws

/**
 * Laws that must be obeyed by any `SCL expression-like entity`.
 */
trait BasicExpressionLikeLaws extends ExpressionLikeLaws {

  // TODO lift to api4kbj.Basic
  def basicExpressionlikeIsBasicIdentity: Prop = Prop.forAll { (clentity: BasicExpressionLike) =>
    (clentity isBasic)
  }

  def bexpressionlike: RuleSet = new RuleSet {
    def name = "expressionlike"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq()
    def props = Seq(
      ("SCL ExpressionLike is basic (has no structure)", basicExpressionlikeIsBasicIdentity),
      ("SCL ExpressionLike uses the Common Logic language", expressionlikeUsesLanguageIdentity),
      ("SCL ExpressionLike has the EXPRESSION abstraction level", expressionlikeHasExpressionAbstractionLevelIdentity),
      ("SCL ExpressionLike equals itself", expressionlikeEqualsItselfIdentity),
      ("SCL ExpressionLike is not equal null", expressionlikeNotEqualNullIdentity))
  }

}

object BasicExpressionLikeLaws extends BasicExpressionLikeLaws

trait CommentLaws extends ExpressionLikeLaws {

  def commentEqualityByMembersIdentity: Prop = Prop.forAll { ((comment: Comment), (comment2: Comment)) =>
    (comment equals comment2) || !((comment data) equals (comment2 data))
  }

  def commentDisjointTermOrSequenceMarkerIdentity: Prop = Prop.forAll { ((comment: Comment), (tosm: TermOrSequenceMarker)) =>
    !(comment equals tosm) && !(tosm equals comment)
  }

  def comment: RuleSet = new RuleSet {
    def name = "comment"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(
      ("SCL Comment Equality is Based on its Members", commentEqualityByMembersIdentity),
      ("SCL Comments are Disjoint from SCL Terms and Sequence Markers", commentDisjointTermOrSequenceMarkerIdentity))
  }

}

object CommentLaws extends CommentLaws

trait TermOrSequenceMarkerLaws extends ExpressionLikeLaws {

  def tosmNotEqualSentenceIdentity: Prop = Prop.forAll {
    ((tosm: TermOrSequenceMarker), (expression: Expression)) =>
      !(tosm.equals(expression)) && !(expression.equals(tosm))
  }

  def tosm: RuleSet = new RuleSet {
    def name = "tosm"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(
      ("SCL Terms and Sequence Markers are Disjoint from SCL Expressions", tosmNotEqualSentenceIdentity))
  }
}
object TermOrSequenceMarkerLaws extends TermOrSequenceMarkerLaws

trait TermLaws extends TermOrSequenceMarkerLaws {

  def termDisjointSequenceMarkerIdentity: Prop = Prop.forAll { ((term: Term), (marker: SequenceMarker)) =>
    !(term.equals(marker))
  }

  def term: RuleSet = new RuleSet {
    def name = "term"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(
      ("SCL Terms are Disjoint from SCL Comments", termDisjointSequenceMarkerIdentity))
  }

}

object TermLaws extends TermLaws

trait NameLaws extends TermLaws {

  def nameDisjointFunctionalTermIdentity: Prop = Prop.forAll { ((name: Name), (fterm: FunctionalTerm)) =>
    !(name.equals(fterm))
  }

  def name: RuleSet = new RuleSet {
    def name = "name"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(
      ("SCL Names are Disjoint from SCL Functional Terms", nameDisjointFunctionalTermIdentity))
  }

}

object NameLaws extends NameLaws

// TODO BindingSet

trait SequenceMarkerLaws extends TermOrSequenceMarkerLaws {

  def markerDisjointNameIdentity: Prop = Prop.forAll { ((marker: SequenceMarker), (name: Name)) =>
    !(marker.equals(name)) &&
      !(name.equals(marker))
  }

  def marker: RuleSet = new RuleSet {
    def name = "marker"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(
      ("SCL Sequence Markers are Disjoint from SCL Names", markerDisjointNameIdentity))
  }

}

object SequenceMarkerLaws extends SequenceMarkerLaws

object FunctionalTermLaws extends TermLaws {

  def expressionLikeArgumentShouldNotBeNull: Prop = Prop.forAll { ((comments: CommentSet), (operator: Term), (terms: TermSequence)) =>
    {
      Prop.throws(classOf[NullPointerException]) {
        val term = new FunctionalTerm(null, operator, terms)
      }
      Prop.throws(classOf[NullPointerException]) {
        val term = new FunctionalTerm(comments, null, terms)
      }
      Prop.throws(classOf[NullPointerException]) {
        val term = new FunctionalTerm(comments, operator, null)
      }
      !Prop.throws(classOf[NullPointerException]) {
        val term = new FunctionalTerm(comments, operator, terms)
      }
    }
  }

  def functionalTermEqualityByMembersIdentity: Prop = Prop.forAll { ((term: FunctionalTerm), (term2: FunctionalTerm)) =>
    (term equals term2) || !((term comments) equals (term2 comments)) || !((term operator) equals (term2 operator)) || !((term args) equals (term2 args))
  }

  def fterm: RuleSet = new RuleSet {
    def name = "fterm"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(term)
    def props = Seq(
      ("SCL Functional Term Equality is Based on its Members", functionalTermEqualityByMembersIdentity),
      ("Null Constructor Argument Exception", expressionLikeArgumentShouldNotBeNull))
  }
}

// TODO SequenceMarkerSet

// TODO SentenceSet
// TODO ExpressionSet
