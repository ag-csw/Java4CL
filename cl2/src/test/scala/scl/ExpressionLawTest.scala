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

import scala.language.postfixOps

class ExpressionLawTest extends FunSuiteLike with Discipline {
  implicit override val generatorDrivenConfig =
    PropertyCheckConfig(
      minSuccessful = MIN_SUCCESSFUL,
      maxDiscarded = MAX_DISCARDED,
      minSize = MIN_SIZE,
      maxSize = MAX_SIZE,
      workers = WORKERS)

  checkAll("Expression", ExpressionLaws.expression)

  checkAll("BasicExpression", BasicExpressionLaws.bexpression)

  checkAll("Sentence", SentenceLaws.sentence)

  checkAll("AtomicSentence", AtomicSentenceLaws.atom)

  checkAll("BooleanSentence", BooleanSentenceLaws.bool)

  checkAll("Biconditional", BiconditionalLaws.bicond)

  checkAll("Conjunction", ConjunctionLaws.and)

  checkAll("Disjunction", DisjunctionLaws.or)

  checkAll("QuantifiedSentence", QuantifiedSentenceLaws.quant)

  checkAll("Existential", ExistentialLaws.exists)
}
