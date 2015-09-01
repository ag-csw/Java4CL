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

import cl2.functionconversions.toJavaFunction
import CLGenerators._
import scala.language.postfixOps
import com.typesafe.scalalogging.LazyLogging

/**
 * Laws that must be obeyed by any `CL expression-like entity`.
 */
trait CLExpressionLikeLaws extends Laws with LazyLogging {

  val emptyComments = new CLCommentSetArray()

  def expressionlikeUsesCLLanguageIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    {
      (clentity language) == CL.LANG
    }
  }

  // TODO lift to api4kbj.Basic
  def expressionlikeIsBasicIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    (clentity isBasic)
  }

  // TODO lift to api4kbj.KnowledgeExpressionLike
  def expressionlikeHasExpressionAbstractionLevelIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    (clentity level) == EXPRESSION
  }

  // TODO lift to api4kbj.Immutable
  def expressionlikeEqualsItselfIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    (clentity equals clentity)
  }

  // TODO lift to api4kbj.Immutable
  def expressionlikeEqualsCopyIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    {
      val clentity2 = (clentity copy)
      (clentity equals clentity2) && ((clentity hashCode) equals (clentity2 hashCode))
    }
  }

  // TODO lift to api4kbj.Immutable
  def expressionlikeNotEqualNullIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    !(clentity equals null)
  }

  def expressionlike: RuleSet = new RuleSet {
    def name = "expressionlike"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq()
    def props = Seq(
      ("A CLExpressionLike is basic (has no structure)", expressionlikeIsBasicIdentity),
      ("A CLExpressionLike uses the Common Logic language", expressionlikeUsesCLLanguageIdentity),
      ("A CLExpressionLike has the EXPRESSION abstraction level", expressionlikeHasExpressionAbstractionLevelIdentity),
      ("A CLExpressionLike equals itself", expressionlikeEqualsItselfIdentity),
      ("A CLExpressionLike equals copy", expressionlikeEqualsCopyIdentity),
      ("A CLExpressionLike is not equal null", expressionlikeNotEqualNullIdentity))
  }

}

object CLExpressionLikeLaws extends CLExpressionLikeLaws

trait CLCommentLaws extends CLExpressionLikeLaws {

  def commentEqualityByMembersIdentity: Prop = Prop.forAll { ((comment: CLComment), (comment2: CLComment)) =>
    (comment equals comment2) || !((comment data) equals (comment2 data))
  }

  def commentDisjointTermOrSequenceMarkerIdentity: Prop = Prop.forAll { ((comment: CLComment), (tosm: CLTermOrSequenceMarker)) =>
    !(comment equals tosm) && !(tosm equals comment)
  }

  def comment: RuleSet = new RuleSet {
    def name = "comment"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(
      ("CL Comment Equality is Based on its Members", commentEqualityByMembersIdentity),
      ("CL Comments are Disjoint from CL Terms and Sequence Markers", commentDisjointTermOrSequenceMarkerIdentity))
  }

}

object CLCommentLaws extends CLCommentLaws

trait CLCommentSetLaws extends CLExpressionLikeLaws {

  def expressionLikeArgumentShouldNotBeNull: Prop = Prop.forAll { (comment: CLComment) =>
    {
      Prop.throws(classOf[NullPointerException]) {
        val set = new CLCommentSetArray(null, comment)
      }
      Prop.throws(classOf[NullPointerException]) {
        val set = new CLCommentSetArray(comment, null)
      }
    }
  }

  def comment: RuleSet = new RuleSet {
    def name = "comment set"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(("Null Constructor Argument Exception", expressionLikeArgumentShouldNotBeNull))
  }

}

object CLCommentSetLaws extends CLCommentSetLaws

trait CLTermOrSequenceMarkerLaws extends CLExpressionLikeLaws {

  def tosmNotEqualSentenceIdentity: Prop = Prop.forAll {
    ((tosm: CLTermOrSequenceMarker), (expression: CLExpression)) =>
      !(tosm.equals(expression)) && !(expression.equals(tosm))
  }

  def tosm: RuleSet = new RuleSet {
    def name = "tosm"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(
      ("CL Terms and Sequence Markers are Disjoint from CL Expressions", tosmNotEqualSentenceIdentity))
  }
}
object CLTermOrSequenceMarkerLaws extends CLTermOrSequenceMarkerLaws

// CLTermSequence
trait CLTermSequenceLaws extends CLExpressionLikeLaws {

  def expressionLikeArgumentShouldNotBeNull: Prop = Prop.forAll { (tosm: CLTermOrSequenceMarker) =>
    {
      Prop.throws(classOf[NullPointerException]) {
        val set = new CLTermSequenceArray(null, tosm)
      }
      Prop.throws(classOf[NullPointerException]) {
        val set = new CLTermSequenceArray(tosm, null)
      }
    }
  }

  def terms: RuleSet = new RuleSet {
    def name = "term sequence"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(("Null Constructor Argument Exception", expressionLikeArgumentShouldNotBeNull))
  }

}

// TODO add to laws test
object CLTermSequenceLaws extends CLTermSequenceLaws

trait CLTermLaws extends CLTermOrSequenceMarkerLaws {

  def termDisjointSequenceMarkerIdentity: Prop = Prop.forAll { ((term: CLTerm), (marker: CLSequenceMarker)) =>
    !(term.equals(marker))
  }

  def term: RuleSet = new RuleSet {
    def name = "term"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(
      ("CL Terms are Disjoint from CL Comments", termDisjointSequenceMarkerIdentity))
  }

}

object CLTermLaws extends CLTermLaws

trait CLNameLaws extends CLTermLaws {

  def nameDisjointFunctionalTermIdentity: Prop = Prop.forAll { ((name: CLName), (fterm: CLFunctionalTerm)) =>
    !(name.equals(fterm))
  }

  def name: RuleSet = new RuleSet {
    def name = "name"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(
      ("CL Names are Disjoint from CL Functional Terms", nameDisjointFunctionalTermIdentity))
  }

}

object CLNameLaws extends CLNameLaws

// TODO CLBindingSet

trait CLSequenceMarkerLaws extends CLTermOrSequenceMarkerLaws {

  def markerDisjointNameIdentity: Prop = Prop.forAll { ((marker: CLSequenceMarker), (name: CLName)) =>
    !(marker.equals(name)) &&
      !(name.equals(marker))
  }

  def marker: RuleSet = new RuleSet {
    def name = "marker"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(
      ("CL Sequence Markers are Disjoint from CL Names", markerDisjointNameIdentity))
  }

}

object CLSequenceMarkerLaws extends CLSequenceMarkerLaws

object CLFunctionalTermLaws extends CLTermLaws {

  def expressionLikeArgumentShouldNotBeNull: Prop = Prop.forAll { ((comments: CLCommentSet), (operator: CLTerm), (terms: CLTermSequence)) =>
    {
      Prop.throws(classOf[NullPointerException]) {
        val term = new CLFunctionalTerm(null, operator, terms)
      }
      Prop.throws(classOf[NullPointerException]) {
        val term = new CLFunctionalTerm(comments, null, terms)
      }
      Prop.throws(classOf[NullPointerException]) {
        val term = new CLFunctionalTerm(comments, operator, null)
      }
      !Prop.throws(classOf[NullPointerException]) {
        val term = new CLFunctionalTerm(comments, operator, terms)
      }
    }
  }

  def functionalTermEqualityByMembersIdentity: Prop = Prop.forAll { ((term: CLFunctionalTerm), (term2: CLFunctionalTerm)) =>
    (term equals term2) || !((term comments) equals (term2 comments)) || !((term operator) equals (term2 operator)) || !((term args) equals (term2 args))
  }

  def termIdentityIdentity: Prop = Prop.forAll { (term: CLFunctionalTerm) =>
    {
      val term2 = term.copy(
        { s: CLCommentSet => s }, { s: CLTerm => s }, { s: CLTermSequence => s })

      (term equals term2) &&
        ((term hashCode) equals (term2 hashCode))
    }
  }

  def termComposeIdentity(
    term: CLFunctionalTerm,
    f1: Function[CLCommentSet, CLCommentSet],
    f2: Function[CLTerm, CLTerm],
    f3: Function[CLTermSequence, CLTermSequence],
    g1: Function[CLCommentSet, CLCommentSet],
    g2: Function[CLTerm, CLTerm],
    g3: Function[CLTermSequence, CLTermSequence]): Prop = Prop.forAll { (atom: CLAtomicSentence) =>
    {
      val term1 = term.copy(f1, f2, f3).copy(g1, g2, g3)

      val term2 = term.copy(
        (f1 compose g1),
        (f2 compose g2),
        (f3 compose g3))

      (term1 equals term2)
    }
  }

  def fterm: RuleSet = new RuleSet {
    def name = "fterm"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(term)
    def props = Seq(
      ("CL Functional Term Equality is Based on its Members", functionalTermEqualityByMembersIdentity),
      ("Null Constructor Argument Exception", expressionLikeArgumentShouldNotBeNull),
      ("Identity Copy", termIdentityIdentity))
  }
}

// TODO CLSequenceMarkerSet

// TODO CLSentenceSet
// TODO CLExpressionSet
