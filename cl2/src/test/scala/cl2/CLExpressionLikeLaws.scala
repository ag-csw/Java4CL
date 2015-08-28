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

/**
 * Laws that must be obeyed by any `CL expression-like entity`.
 */
trait CLExpressionLikeLaws extends Laws {

  val emptyComments = new CLCommentSetArray()

  def expressionlikeUsesCLLanguageIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    (clentity language) == CL.LANG
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

trait CLCommentLaws extends CLExpressionLikeLaws {

  def commentNotEqualTermIdentity: Prop = Prop.forAll { ((comment: CLComment), (term: CLTerm)) =>
    !(comment.equals(term))
  }

  def comment: RuleSet = new RuleSet {
    def name = "comment"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(
      ("CL Comments are Disjoint from CL Terms", commentNotEqualTermIdentity))
  }

}

object CLCommentLaws extends CLCommentLaws

trait CLTermLaws extends CLExpressionLikeLaws {

  def termNotEqualCommentIdentity: Prop = Prop.forAll { ((term: CLTerm), (comment: CLComment)) =>
    !(term.equals(comment))
  }

  def term: RuleSet = new RuleSet {
    def name = "term"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(
      ("CL Terms are Disjoint from CL Comments", termNotEqualCommentIdentity))
  }

}

object CLTermLaws extends CLTermLaws

trait CLNameLaws extends CLTermLaws {

  def nameNotEqualFunctionalTermIdentity: Prop = Prop.forAll { ((name: CLName), (fterm: CLFunctionalTerm)) =>
    !(name.equals(fterm))
  }

  def name: RuleSet = new RuleSet {
    def name = "name"
    def bases: Seq[(String, Laws#RuleSet)] = Seq()
    def parents: Seq[RuleSet] = Seq(expressionlike)
    def props = Seq(
      ("CL Names are Disjoint from CL Functional Terms", nameNotEqualFunctionalTermIdentity))
  }

}

object CLNameLaws extends CLNameLaws
