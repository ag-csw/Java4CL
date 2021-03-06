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

import com.typesafe.scalalogging._
import cats.laws.IsEq

package object scl {

  val MIN_SUCCESSFUL = 100
  val MAX_DISCARDED = 500
  val MIN_SIZE = 5
  val MAX_SIZE = 10
  val WORKERS = 1

  val EXPRESSION = api4kbj.KnowledgeSourceLevel.EXPRESSION
  val Prop = org.scalacheck.Prop

  val URI_XSD_STRING = "http://www.w3.org/2001/XMLSchema#string"

  type Discipline = org.typelevel.discipline.scalatest.Discipline
  type Laws = org.typelevel.discipline.Laws
  type FunSuiteLike = org.scalatest.FunSuiteLike
  type Prop = org.scalacheck.Prop
  type CommentSet = Set[Comment]
  type TermSequence = List[TermOrSequenceMarker]
  type TOSMSet = Set[TermOrSequenceMarker]
  type NOSMSet = Set[NameOrSequenceMarker]
  type TermSet = Set[Term]
  type SentenceSet = Set[Sentence]
  type BasicExpressionSet = Set[BasicExpression]
  type BindingSet = Set[InterpretableName]
  type SeqBindingSet = Set[SequenceMarker]

  implicit class ComposePartial[A, B](pf: PartialFunction[A, B]) {
    def andThenPartial[C](that: PartialFunction[B, C]): PartialFunction[A, C] =
      Function.unlift(pf.lift(_) flatMap that.lift)
  }

  implicit final class IsEqArrow[A](val lhs: A) extends AnyVal {
    def <->(rhs: A): IsEq[A] = IsEq(lhs, rhs)
  }

}
