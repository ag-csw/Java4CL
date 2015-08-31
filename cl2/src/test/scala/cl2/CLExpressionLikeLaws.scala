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
    clentity.equals(clentity)
  }

  // TODO lift to api4kbj.Immutable
  def expressionlikeEqualsCopyIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    clentity.equals(clentity.copy())
  }

  // TODO lift to api4kbj.Immutable
  def expressionlikeNotEqualNullIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    !(clentity.equals(null))
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

  def commentDisjointTermOrSequenceMarkerIdentity: Prop = Prop.forAll { ((comment: CLComment), (tosm: CLTermOrSequenceMarker)) =>
    !(comment.equals(tosm)) && !(tosm.equals(comment))
  }

  def comment: RuleSet = new RuleSet {
    def name = "comment"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(
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

// TODO CLSequenceMarkerSet

// TODO CLSentenceSet
// TODO CLExpressionSet
