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

/**
 * @author taraathan
 */
package object cl2 {
  val MIN_SUCCESSFUL = 200
  val MAX_DISCARDED = 1000
  val MIN_SIZE = 10
  val MAX_SIZE = 20
  val WORKERS = 1

  val EXPRESSION = api4kbj.KnowledgeSourceLevel.EXPRESSION
  val Prop = org.scalacheck.Prop

  type CLBindingSequence = cl2a.CLComment
  type CLBooleanSentence = cl2a.CLComment
  type CLComment = cl2a.CLComment
  type CLCommentSet = cl2a.CLCommentSet
  type CLDiscourseStatement = cl2a.CLDiscourseStatement
  type CLExpression = cl2a.CLExpression
  type CLExpressionLike = cl2a.CLExpressionLike
  type CLInterpretableName = cl2a.CLInterpretableName
  type CLInterpretedName = cl2a.CLInterpretedName
  type CLName = cl2a.CLName
  type CLQuantifiedSentence = cl2a.CLQuantifiedSentence
  type CLSentence = cl2a.CLSentence
  type CLSentenceSequence = cl2a.CLSentenceSequence
  type CLSequenceMarker = cl2a.CLSequenceMarker
  type CLSequenceMarkerSequence = cl2a.CLSequenceMarkerSequence
  type CLSimpleSentence = cl2a.CLSimpleSentence
  type CLStatement = cl2a.CLText
  type CLTerm = cl2a.CLTerm
  type CLTermOrSequenceMarker = cl2a.CLTermOrSequenceMarker
  type CLTermSequence = cl2a.CLTermSequence
  type CLText = cl2a.CLText

  type CLBindingSequenceArray = cl2array.CLBindingSequenceArray
  type CLCommentSetArray = cl2array.CLCommentSetArray
  type CLExpressionSequenceArray = cl2array.CLExpressionSequenceArray
  type CLSentenceSequenceArray = cl2array.CLSentenceSequenceArray
  type CLTermSequenceArray = cl2array.CLTermSequenceArray

  type CLCommentSetJC = cl2jc.CLCommentSetJC
  type CLEmptyCommentSetJC = cl2jc.CLEmptyCommentSetJC

  type Discipline = org.typelevel.discipline.scalatest.Discipline
  type Laws = org.typelevel.discipline.Laws
  type FunSuiteLike = org.scalatest.FunSuiteLike
  type Prop = org.scalacheck.Prop

}
