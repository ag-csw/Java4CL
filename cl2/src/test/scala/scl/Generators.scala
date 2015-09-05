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

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import com.typesafe.scalalogging._
import scala.language.postfixOps

object Generators extends LazyLogging {

  val CODE_POINT_NULL = 0x0;
  val CODE_POINT_TAB = 0x9;
  val CODE_POINT_LF = 0xA;
  val CODE_POINT_CF = 0xD;
  val CODE_POINT_MIN_C1 = 0x20;
  val MIN_SURROGATE = Character.MIN_SURROGATE
  val MAX_SURROGATE = Character.MAX_SURROGATE
  val MAX_XML_BMP = Character.MIN_SUPPLEMENTARY_CODE_POINT - 3
  val MIN_SUPPLEMENTARY_CODE_POINT = Character.MIN_SUPPLEMENTARY_CODE_POINT
  val MAX_XML_SUPPLEMENTARY_CODE_POINT = 0x10FFFF

  /**
   * generator of Strings of length 1 or 2 corresponding to a
   * single Unicode character
   * matching the XML 1.1, Char production
   * Note that some of these characters must be escaped before they can be used in XML.
   */
  val xmlcharactergen: Gen[String] =
    for {
      i <- Gen frequency (
        (MIN_SURROGATE - 1 - CODE_POINT_NULL, Gen.choose(CODE_POINT_NULL + 1, MIN_SURROGATE - 1)),
        (MAX_XML_BMP - MAX_SURROGATE, Gen.choose(MAX_SURROGATE + 1, MAX_XML_BMP)),
        (MAX_XML_SUPPLEMENTARY_CODE_POINT - MIN_SUPPLEMENTARY_CODE_POINT + 1,
          Gen.choose(MIN_SUPPLEMENTARY_CODE_POINT, MAX_XML_SUPPLEMENTARY_CODE_POINT)))
    } yield String copyValueOf (Character toChars i)

  /**
   * generator of arbitrary length Strings of Unicode characters
   * matching the XML 1.0, version 5 Char production
   */
  val stringsymbolgen: Gen[String] =
    for { a <- Gen listOf (xmlcharactergen) }
      yield (a mkString)

  // Coment
  /**
   * generator of SCL comments whose comment data (symbol)
   * is String symbol according to stringsymbolgen
   */
  val stringcommentgen: Gen[StringComment] =
    for { s <- stringsymbolgen }
      yield new StringComment(s)

  // TODO also need other kinds of comments
  val commentgen: Gen[Comment] = stringcommentgen

  implicit val arbComment = Arbitrary(commentgen)

  // CommentSet
  /**
   * generator of SCL comment sets (list-based instances), which may be
   * used in generators of "commentable" SCL terms and expressions
   */
  val commentlistsetgen: Gen[Set[_ <: Comment]] =
    for { a <- Gen listOf (commentgen) }
      yield a.toSet[Comment]

  // TODO include other specific kinds of Sets??
  val commentsetgen: Gen[Set[_ <: Comment]] = Gen.frequency(
    (1, commentlistsetgen))

  implicit val arbCommentSet = Arbitrary(commentsetgen)

  // InterpretableName
  /**
   * generator of SCL interpretable name with a
   * String symbol, as defined for stringsymbolgen
   */
  val stringinamegen: Gen[StringInterpretableName] = for { s <- stringsymbolgen }
    yield new StringInterpretableName(s)

  // TODO also need interpretable names with non-string symbols
  val inamegen: Gen[InterpretableName] = stringinamegen

  implicit val arbInterpretableName = Arbitrary(inamegen)

  // InterpretedName
  /**
   * generator for a Cl interpreted name with fixed interpretation
   * as a string of Unicode characters and datatype xsd:string
   */
  val stringdatagen: Gen[StringIriInterpretedName] = for { s <- stringsymbolgen }
    yield new StringIriInterpretedName(s)

  // TODO also need other interpreted names from other datatypes
  val datagen: Gen[InterpretedName] = stringdatagen

  implicit val arbData = Arbitrary(datagen)

  // Name
  /**
   * generator for SCL names, with equal probability
   * of interpretable and interpreted names
   */
  val namegen: Gen[Name] = Gen.frequency(
    (1, inamegen),
    (1, datagen))

  implicit val arbName = Arbitrary(namegen)

  // SequenceMarker
  /**
   * generator for SCL sequence markers with string symbols
   */
  val stringsequencemarkergen =
    for { s <- stringsymbolgen }
      yield new StringSequenceMarker(s)

  // TODO also need non-string sequence markers
  val sequencemarkergen: Gen[SequenceMarker] = stringsequencemarkergen

  implicit val arbSequenceMarker = Arbitrary(sequencemarkergen)

  // FunctionalTerm
  val depth = 4;
  /**
   * generator for zero-depth functional terms,
   * having no nested functional terms
   */
  val functionalterm0gen: Gen[FunctionalTerm] =
    for {
      comments <- commentsetgen
      operator <- namegen
      args <- namesequencegen
    } yield new FunctionalTerm(comments, operator, args)

  /**
   * generator for functional terms with depth 1,
   * possibly having nested zero-depth functional terms
   */
  val functionalterm1gen: Gen[FunctionalTerm] =
    for {
      comments <- commentsetgen
      operator <- term0gen
      args <- term0sequencegen
    } yield new FunctionalTerm(comments, operator, args)

  /**
   * generator for recursively defined functional terms,
   * of depth d
   */
  def functionaltermgen(d: Int): Gen[FunctionalTerm] =
    if (d <= 0) { functionalterm0gen }
    else {
      for {
        comments <- commentsetgen
        operator <- termgen(d - 1)
        args <- termsequencegen(d - 1)
      } yield new FunctionalTerm(comments, operator, args)
    }

  implicit val arbFunctionalTerm = Arbitrary(functionaltermgen(depth))

  // Term
  val term0gen: Gen[Term] = Gen.frequency(
    (MAX_SIZE, namegen),
    (1, functionalterm0gen))

  val term1gen: Gen[Term] = Gen.frequency(
    (MAX_SIZE, namegen),
    (1, functionalterm1gen))

  def termgen(d: Int): Gen[Term] = Gen.frequency(
    (MAX_SIZE, namegen),
    (1, functionaltermgen(d)))

  implicit val arbTerm = Arbitrary(termgen(depth))

  // TermOrSequenceMarker
  val nameorsequencemarkergen: Gen[TermOrSequenceMarker] = Gen.frequency(
    (1, namegen),
    (1, sequencemarkergen))

  val term0orsequencemarkergen: Gen[TermOrSequenceMarker] = Gen.frequency(
    (1, term0gen),
    (1, sequencemarkergen))

  val term1orsequencemarkergen: Gen[TermOrSequenceMarker] = Gen.frequency(
    (1, term1gen),
    (1, sequencemarkergen))

  def termorsequencemarkergen(d: Int): Gen[TermOrSequenceMarker] = Gen.frequency(
    (1, termgen(d)),
    (1, sequencemarkergen))

  implicit val arbTermOrSequenceMarker = Arbitrary(term1orsequencemarkergen)

  // TermSequence
  /**
   * generator for terms sequences containing no functional term
   */
  val namesequencelistgen: Gen[List[TermOrSequenceMarker]] = Gen listOf (nameorsequencemarkergen)

  // TODO need other types of sequences
  val namesequencegen = namesequencelistgen

  /**
   * generator for terms sequences containing functional term of depth at most zero
   */
  val term0sequencelistgen: Gen[List[TermOrSequenceMarker]] = Gen listOf (term0orsequencemarkergen)

  // TODO need other types of sequences
  val term0sequencegen = term0sequencelistgen

  def termsequencelistgen(d: Int): Gen[List[TermOrSequenceMarker]] = Gen listOf (termorsequencemarkergen(d))

  // TODO need other types of sequences
  def termsequencegen(d: Int): Gen[List[TermOrSequenceMarker]] = termsequencelistgen(d)

  implicit val arbTermSequence = Arbitrary(termsequencegen(depth))

  // AtomicSentence
  def atomgen(d: Int): Gen[AtomicSentence] =
    for {
      comments <- commentsetgen
      operator <- termgen(d)
      args <- termsequencegen(d)
    } yield new AtomicSentence(comments, operator, args)

  implicit val arbAtomicSentence = Arbitrary(atomgen(depth))

  // Biconditional
  def bicond0gen: Gen[Biconditional] =
    for {
      comments <- commentsetgen
      left <- atomgen(0)
      right <- atomgen(0)
    } yield new Biconditional(comments, Set(left, right))

  /**
   * generator for recursively defined biconditional sentences,
   * of depth d
   */
  def bicondgen(d: Int): Gen[Biconditional] =
    if (d <= 0) { bicond0gen }
    else {
      for {
        comments <- commentsetgen
        left <- sentencegen(d - 1)
        right <- sentencegen(d - 1)
      } yield new Biconditional(comments, Set(left, right))
    }

  implicit val arbBiconditional = Arbitrary(bicondgen(depth))

  // Conjunction
  def and0gen: Gen[Conjunction] =
    for {
      comments <- commentsetgen
      sentences <- atomsetgen(0)
    } yield new Conjunction(comments, sentences)

  /**
   * generator for recursively defined conjunction sentences,
   * of depth d
   */
  def andgen(d: Int): Gen[Conjunction] =
    if (d <= 0) { and0gen }
    else {
      for {
        comments <- commentsetgen
        sentences <- sentencesetgen(d - 1)
      } yield new Conjunction(comments, sentences)
    }

  implicit val arbConjunction = Arbitrary(andgen(depth))

  // Disjunction
  def or0gen: Gen[Disjunction] =
    for {
      comments <- commentsetgen
      sentences <- atomsetgen(0)
    } yield new Disjunction(comments, sentences)

  /**
   * generator for recursively defined disjunction sentences,
   * of depth d
   */
  def orgen(d: Int): Gen[Disjunction] =
    if (d <= 0) { or0gen }
    else {
      for {
        comments <- commentsetgen
        sentences <- sentencesetgen(d - 1)
      } yield new Disjunction(comments, sentences)
    }

  implicit val arbDisjunction = Arbitrary(orgen(depth))

  // Negation
  def not0gen: Gen[Negation] =
    for {
      comments <- commentsetgen
      body <- atomgen(0)
    } yield new Negation(comments, body)

  /**
   * generator for recursively defined Negation sentences,
   * of depth d
   */
  def notgen(d: Int): Gen[Negation] =
    if (d <= 0) { not0gen }
    else {
      for {
        comments <- commentsetgen
        body <- sentencegen(d - 1)
      } yield new Negation(comments, body)
    }

  implicit val arbNegation = Arbitrary(notgen(depth))

  // Implication
  def if0gen: Gen[Implication] =
    for {
      comments <- commentsetgen
      antecedent <- atomgen(0)
      consequent <- atomgen(0)
    } yield new Implication(comments, antecedent, consequent)

  /**
   * generator for recursively defined Implication sentences,
   * of depth d
   */
  def ifgen(d: Int): Gen[Implication] =
    if (d <= 0) { if0gen }
    else {
      for {
        comments <- commentsetgen
        antecedent <- sentencegen(d - 1)
        consequent <- sentencegen(d - 1)
      } yield new Implication(comments, antecedent, consequent)
    }

  implicit val arbImplication = Arbitrary(ifgen(depth))

  // Existential
  def exists0gen: Gen[Existential] =
    for {
      comments <- commentsetgen
      bindings <- bindingsetgen
      body <- atomgen(0)
    } yield new Existential(comments, bindings, body)

  /**
   * generator for recursively defined existential sentences,
   * of depth d
   */
  def existsgen(d: Int): Gen[Existential] =
    if (d <= 0) { exists0gen }
    else {
      for {
        comments <- commentsetgen
        bindings <- bindingsetgen
        body <- sentencegen(d - 1)
      } yield new Existential(comments, bindings, body)
    }

  implicit val arbExistential = Arbitrary(existsgen(depth))

  // SimpleSentence
  // TODO add equations
  def simplesentencegen(d: Int): Gen[SimpleSentence] = Gen.frequency(
    (1, atomgen(d)))

  implicit val arbSimpleSentence = Arbitrary(simplesentencegen(depth))

  // QuantifiedSentence
  // TODO add universal
  def quantifiedsentencegen(d: Int): Gen[QuantifiedSentence] = Gen.frequency(
    (1, existsgen(d)))

  implicit val arbQuantifiedSentence = Arbitrary(quantifiedsentencegen(depth))

  // BooleanSentence
  // TODO add more types of Sentence (implies and equivalence and negation)
  def booleansentencegen(d: Int): Gen[BooleanSentence] = Gen.frequency(
    (MAX_SIZE / 2, bicondgen(d)),
    (MAX_SIZE / 2, ifgen(d)),
    (MAX_SIZE, notgen(d)),
    (MAX_SIZE, quantifiedsentencegen(d)),
    (1, andgen(d)),
    (1, orgen(d)))

  implicit val arbBooleanSentence = Arbitrary(booleansentencegen(depth))

  // Sentence
  def sentencegen(d: Int): Gen[Sentence] = Gen.frequency(
    (MAX_SIZE, simplesentencegen(d)),
    (1, booleansentencegen(d)))

  implicit val arbSentence = Arbitrary(sentencegen(depth))

  // SentenceSet
  def atomsetlistgen(d: Int): Gen[Set[_ <: Sentence]] =
    for { a <- Gen listOf (atomgen(d)) }
      yield a.toSet[Sentence]

  // TODO need other types of sequences
  def atomsetgen(d: Int): Gen[Set[_ <: Sentence]] = atomsetlistgen(d)

  def sentencesetlistgen(d: Int): Gen[Set[_ <: Sentence]] =
    for { a <- Gen listOf (sentencegen(d)) }
      yield a.toSet[Sentence]

  // TODO need other types of sets
  def sentencesetgen(d: Int): Gen[Set[_ <: Sentence]] = sentencesetlistgen(d)

  implicit val arbSentenceSet = Arbitrary(sentencesetgen(depth))

  // TextConstruction
  def construct0gen: Gen[TextConstruction] =
    for {
      comments <- commentsetgen
      sentences <- sentencesetgen(0)
    } yield new TextConstruction(comments, sentences)

  /**
   * generator for recursively defined text constructions,
   * of depth d
   */
  def constructgen(d: Int): Gen[TextConstruction] =
    if (d <= 0) { construct0gen }
    else {
      for {
        comments <- commentsetgen
        expressions <- bexpressionsetgen(d - 1)
      } yield new TextConstruction(comments, expressions)
    }

  implicit val arbTextConstruction = Arbitrary(constructgen(depth))

  // Text

  // TODO add other kinds of texts: imports, domain restrictions
  def textgen(d: Int): Gen[Text] = constructgen(d)

  implicit val arbText = Arbitrary(textgen(depth))

  // BasicExpression
  // TODO add statements also
  def bexpressiongen(d: Int): Gen[BasicExpression] = Gen.frequency(
    (MAX_SIZE * MAX_SIZE, sentencegen(d)),
    (1, textgen(d)))

  implicit val arbBasicExpression = Arbitrary(bexpressiongen(depth))

  // BasicExpressionSet
  def bexpressionsetlistgen(d: Int): Gen[Set[_ <: BasicExpression]] =
    for { a <- Gen listOf (bexpressiongen(d)) }
      yield a.toSet[BasicExpression]

  // TODO need other types of sets
  def bexpressionsetgen(d: Int): Gen[Set[_ <: BasicExpression]] = Gen.frequency(
    (1, bexpressionsetlistgen(d)))

  implicit val arbExpressionSet = Arbitrary(bexpressionsetgen(depth))

  // Expression
  // TODO add structured expressions
  def expressiongen(d: Int): Gen[Expression] = Gen.frequency(
    (MAX_SIZE * MAX_SIZE, sentencegen(d)),
    (1, textgen(d)))

  implicit val arbExpression = Arbitrary(expressiongen(depth))

  // ExpressionSet
  def expressionsetlistgen(d: Int): Gen[Set[_ <: Expression]] =
    for { a <- Gen listOf (expressiongen(d)) }
      yield a.toSet[Expression]

  // TODO need other types of sets
  def expressionsetgen(d: Int): Gen[Set[_ <: Expression]] = Gen.frequency(
    (1, expressionsetlistgen(d)))

  implicit val arbBasicExpressionSet = Arbitrary(bexpressionsetgen(depth))

  // Set[InterpretableName]
  def bindingsetlistgen: Gen[Set[_ <: InterpretableName]] =
    for { a <- Gen listOf (inamegen) }
      yield a.toSet[InterpretableName]

  // TODO need other types of sets
  def bindingsetgen: Gen[Set[_ <: InterpretableName]] = Gen.frequency(
    (1, bindingsetlistgen))

  implicit val arbbindingset = Arbitrary(bindingsetgen)

  // BasicExpressionLike
  def basicexpressionlikegen(d: Int): Gen[BasicExpressionLike] = Gen.frequency(
    (MAX_SIZE, termgen(d)),
    (MAX_SIZE, sequencemarkergen),
    (MAX_SIZE, commentgen))

  implicit val arbBasicExpressionLike = Arbitrary(basicexpressionlikegen(depth))

  // ExpressionLike
  // TODO is there any other kind of ExpressionLike other than basic?s
  def expressionlikegen(d: Int): Gen[ExpressionLike] = basicexpressionlikegen(d)

  implicit val arbExpressionLike = Arbitrary(expressionlikegen(depth))

  // TODO make a valid IRI generator by assembling from segments
  // IRI            = scheme ":" ihier-part [ "?" iquery ]
  //                         [ "#" ifragment ]
  // val irischemegen:Gen[String] =

  // iprivate       = %xE000-F8FF / %xF0000-FFFFD / %x100000-10FFFD
  val MAX_PRIVATE_BMP = 0xF8FF
  val MIN_SPUAA = 0xF0000
  val MAX_SPUAA = 0xFFFFD
  val MIN_SPUAB = 0x100000
  val MAX_SPUAB = Character.MAX_CODE_POINT - 2

  val iriiprivategen: Gen[String] =
    for {
      i <- Gen frequency (
        (MAX_PRIVATE_BMP - MAX_SURROGATE, Gen.choose(MAX_SURROGATE + 1, MAX_PRIVATE_BMP)),
        (MAX_SPUAA - MIN_SPUAA + 1, Gen.choose(MIN_SPUAA, MAX_SPUAA)),
        (MAX_SPUAB - MIN_SPUAB + 1,
          Gen.choose(MIN_SPUAB, MAX_SPUAB)))
    } yield String copyValueOf (Character toChars i)

  // TODO use the IRI generator to make a data generator with arbitrary IRI datatype

  // TODO make generators for other kinds of symbols - Image, objects as XML serializations (JAXB?)

}
