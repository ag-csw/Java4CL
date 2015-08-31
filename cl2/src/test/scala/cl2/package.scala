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

  val MIN_SUCCESSFUL = 100
  val MAX_DISCARDED = 500
  val MIN_SIZE = 5
  val MAX_SIZE = 10
  val WORKERS = 1

  val EXPRESSION = api4kbj.KnowledgeSourceLevel.EXPRESSION
  val Prop = org.scalacheck.Prop

  type CLBindingSet = cl2a.CLBindingSet
  type CLBooleanSentence = cl2a.CLBooleanSentence
  type CLComment = cl2a.CLComment
  type CLCommentSet = cl2a.CLCommentSet
  type CLDiscourseStatement = cl2a.CLDiscourseStatement
  type CLExpression = cl2a.CLExpression
  type CLExpressionLike = cl2a.CLExpressionLike
  type CLExpressionSet = cl2a.CLExpressionSet
  type CLInterpretableName = cl2a.CLInterpretableName
  type CLInterpretedName = cl2a.CLInterpretedName
  type CLName = cl2a.CLName
  type CLQuantifiedSentence = cl2a.CLQuantifiedSentence
  type CLSentence = cl2a.CLSentence
  type CLSentenceSet = cl2a.CLSentenceSet
  type CLSequenceMarker = cl2a.CLSequenceMarker
  type CLSequenceMarkerSet = cl2a.CLSequenceMarkerSet
  type CLSimpleSentence = cl2a.CLSimpleSentence
  type CLStatement = cl2a.CLText
  type CLTerm = cl2a.CLTerm
  type CLTermOrSequenceMarker = cl2a.CLTermOrSequenceMarker
  type CLTermSequence = cl2a.CLTermSequence
  type CLText = cl2a.CLText

  type CLBindingSetArray = cl2array.CLBindingSetArray
  type CLCommentSetArray = cl2array.CLCommentSetArray
  type CLExpressionSetArray = cl2array.CLExpressionSetArray
  type CLSentenceSetArray = cl2array.CLSentenceSetArray
  type CLTermSequenceArray = cl2array.CLTermSequenceArray

  type CLCommentSetJC = cl2jc.CLCommentSetJC
  type CLEmptyCommentSetJC = cl2jc.CLEmptyCommentSetJC

  type Discipline = org.typelevel.discipline.scalatest.Discipline
  type Laws = org.typelevel.discipline.Laws
  type FunSuiteLike = org.scalatest.FunSuiteLike
  type Prop = org.scalacheck.Prop

}
