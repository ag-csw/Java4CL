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

  // Debugging flags
  val DEBUGEMPTYSTRING = false
  val DEBUGEMPTYCOLLECTION = false
  val DEBUGDEPTH = false

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

  val commentemptygen: Gen[Comment] = new StringComment("")

  val commentArbGen: Gen[Comment] = if (DEBUGEMPTYSTRING) commentemptygen else commentgen

  implicit val arbComment: Arbitrary[Comment] = Arbitrary(commentArbGen)

  // CommentSet
  /**
   * generator of SCL comment sets (list-based instances), which may be
   * used in generators of "commentable" SCL terms and expressions
   */
  val commentlistsetgen: Gen[CommentSet] =
    for { a <- Gen listOf (commentArbGen) }
      yield a.toSet[Comment]

  // TODO include other specific kinds of Sets??
  val commentsetgen: Gen[CommentSet] = Gen.frequency(
    (1, commentlistsetgen))

  val commentemptysetgen: Gen[CommentSet] = Set.empty[Comment]

  val commentSetArbGen: Gen[CommentSet] = if (DEBUGEMPTYCOLLECTION) commentemptysetgen else commentsetgen

  implicit val arbCommentSet: Arbitrary[CommentSet] = Arbitrary(commentSetArbGen)

  // InterpretableName
  /**
   * generator of SCL interpretable name with a
   * String symbol, as defined for stringsymbolgen
   */
  val stringinamegen: Gen[StringInterpretableName] = for { s <- stringsymbolgen }
    yield new StringInterpretableName(s)

  // TODO also need interpretable names with non-string symbols
  val inamegen: Gen[InterpretableName] = stringinamegen

  val inameemptygen: Gen[InterpretableName] = new StringInterpretableName("")

  val inameArbGen: Gen[InterpretableName] = if (DEBUGEMPTYSTRING) inameemptygen else inamegen

  implicit val arbInterpretableName: Arbitrary[InterpretableName] = Arbitrary(inameArbGen)

  // InterpretedName
  /**
   * generator for a Cl interpreted name with fixed interpretation
   * as a string of Unicode characters and datatype xsd:string
   */
  val stringdatagen: Gen[StringIriInterpretedName] = for { s <- stringsymbolgen }
    yield new StringIriInterpretedName(s)

  // TODO also need other interpreted names from other datatypes
  val datagen: Gen[InterpretedName] = stringdatagen

  val dataemptygen: Gen[InterpretedName] = new StringIriInterpretedName("")

  val dataArbGen: Gen[InterpretedName] = if (DEBUGEMPTYSTRING) dataemptygen else datagen

  implicit val arbData: Arbitrary[InterpretedName] = Arbitrary(dataArbGen)

  // Name
  /**
   * generator for SCL names, with equal probability
   * of interpretable and interpreted names
   */
  val nameArbgen: Gen[Name] = Gen.frequency(
    (1, inameArbGen),
    (1, dataArbGen))

  implicit val arbName: Arbitrary[Name] = Arbitrary(nameArbgen)

  // SequenceMarker
  /**
   * generator for SCL sequence markers with string symbols
   */
  val stringsequencemarkergen =
    for { s <- stringsymbolgen }
      yield new StringSequenceMarker(s)

  // TODO also need non-string sequence markers
  val sequencemarkergen: Gen[SequenceMarker] = stringsequencemarkergen

  val sequencemarkeremptygen: Gen[SequenceMarker] = new StringSequenceMarker("")

  val sequencemarkerArbGen: Gen[SequenceMarker] = if (DEBUGEMPTYSTRING) sequencemarkeremptygen else sequencemarkergen

  implicit val arbSequenceMarker: Arbitrary[SequenceMarker] = Arbitrary(sequencemarkerArbGen)

  // FunctionalTerm
  val maxDepth = 4;
  val depth = if (DEBUGDEPTH) 1 else maxDepth;
  /**
   * generator for zero-depth functional terms,
   * having no nested functional terms
   */
  val functionalterm0gen: Gen[FunctionalTerm] =
    for {
      comments <- commentSetArbGen
      operator <- nameArbgen
      args <- namesequenceArbgen
    } yield new FunctionalTerm(comments, operator, args)

  /**
   * generator for recursively defined functional terms,
   * of depth d
   */
  def functionaltermgen(d: Int): Gen[FunctionalTerm] =
    if (d <= 0) { functionalterm0gen }
    else {
      for {
        comments <- commentSetArbGen
        operator <- termArbGen(d)
        args <- termsequenceArbgen(d)
      } yield new FunctionalTerm(comments, operator, args)
    }

  def functionalTermArbGen(d: Int): Gen[FunctionalTerm] = functionaltermgen(d)

  implicit val arbFunctionalTerm: Arbitrary[FunctionalTerm] = Arbitrary(functionalTermArbGen(depth))

  // Term
  def termArbGen(d: Int): Gen[Term] =
    if (d <= 0) { nameArbgen } else {
      Gen.frequency(
        (MAX_SIZE, nameArbgen),
        (1, functionalTermArbGen(d - 1)))
    }

  implicit val arbTerm: Arbitrary[Term] = Arbitrary(termArbGen(depth))

  // TermOrSequenceMarker
  val nameorsequencemarkergen: Gen[NameOrSequenceMarker] = Gen.frequency(
    (1, nameArbgen),
    (1, sequencemarkerArbGen))

  def termorsequencemarkerArbGen(d: Int): Gen[TermOrSequenceMarker] = Gen.frequency(
    (1, termArbGen(d)),
    (1, sequencemarkerArbGen))

  implicit val arbTermOrSequenceMarker: Arbitrary[TermOrSequenceMarker] = Arbitrary(termorsequencemarkerArbGen(depth))

  // TermSequence
  /**
   * generator for terms sequences containing no functional term
   */
  val namesequencelistgen: Gen[List[NameOrSequenceMarker]] = Gen listOf (nameorsequencemarkergen)

  val namesequenceemptylistgen: Gen[List[NameOrSequenceMarker]] = Nil

  // TODO need other types of sequences
  val namesequenceArbgen = if (DEBUGEMPTYCOLLECTION) namesequenceemptylistgen else namesequencelistgen

  def termsequencelistgen(d: Int): Gen[TermSequence] = Gen listOf (termorsequencemarkerArbGen(d))

  // TODO need other types of sequences
  def termsequencegen(d: Int): Gen[TermSequence] = termsequencelistgen(d)

  def termsequenceArbgen(d: Int): Gen[TermSequence] = if (DEBUGEMPTYCOLLECTION) namesequenceemptylistgen else termsequencegen(d)

  implicit val arbTermSequence: Arbitrary[TermSequence] = Arbitrary(termsequenceArbgen(depth))

  // AtomicSentence
  def atomgen(d: Int): Gen[AtomicSentence] =
    for {
      comments <- commentSetArbGen
      operator <- termArbGen(d)
      args <- termsequenceArbgen(d)
    } yield new AtomicSentence(comments, operator, args)

  implicit val arbAtomicSentence: Arbitrary[AtomicSentence] = Arbitrary(atomgen(depth))

  // Equation
  def equalsgen(d: Int): Gen[Equation] =
    for {
      comments <- commentSetArbGen
      left <- termArbGen(d)
      right <- termArbGen(d)
    } yield new Equation(comments, Set(left, right))

  implicit val arbEquation: Arbitrary[Equation] = Arbitrary(equalsgen(depth))

  // SimpleSentence
  def simplesentencegen(d: Int): Gen[SimpleSentence] = Gen.frequency(
    (1, atomgen(d)),
    (1, equalsgen(d)))

  implicit val arbSimpleSentence: Arbitrary[SimpleSentence] = Arbitrary(simplesentencegen(depth))

  // Biconditional
  /**
   * generator for zero-depth biconditionals,
   * containing only simple sentences
   */
  def bicond0gen: Gen[Biconditional] =
    for {
      comments <- commentSetArbGen
      left <- simplesentencegen(0)
      right <- simplesentencegen(0)
    } yield new Biconditional(comments, Set(left, right))

  /**
   * generator for recursively defined biconditional sentences,
   * of depth d
   */
  def bicondgen(d: Int): Gen[Biconditional] =
    if (d <= 0) { bicond0gen }
    else {
      for {
        comments <- commentSetArbGen
        left <- sentencegen(d - 1)
        right <- sentencegen(d - 1)
      } yield new Biconditional(comments, Set(left, right))
    }

  val bicondArbgen: Gen[Biconditional] = bicondgen(depth)

  implicit val arbBiconditional: Arbitrary[Biconditional] = Arbitrary(bicondArbgen)

  // Conjunction
  def and0gen: Gen[Conjunction] =
    for {
      comments <- commentSetArbGen
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
        comments <- commentSetArbGen
        sentences <- sentencesetgen(d - 1)
      } yield new Conjunction(comments, sentences)
    }

  implicit val arbConjunction: Arbitrary[Conjunction] = Arbitrary(andgen(depth))

  // Disjunction
  def or0gen: Gen[Disjunction] =
    for {
      comments <- commentSetArbGen
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
        comments <- commentSetArbGen
        sentences <- sentencesetgen(d - 1)
      } yield new Disjunction(comments, sentences)
    }

  implicit val arbDisjunction: Arbitrary[Disjunction] = Arbitrary(orgen(depth))

  // Negation
  def not0gen: Gen[Negation] =
    for {
      comments <- commentSetArbGen
      body <- simplesentencegen(0)
    } yield new Negation(comments, body)

  /**
   * generator for recursively defined Negation sentences,
   * of depth d
   */
  def notgen(d: Int): Gen[Negation] =
    if (d <= 0) { not0gen }
    else {
      for {
        comments <- commentSetArbGen
        body <- sentencegen(d - 1)
      } yield new Negation(comments, body)
    }

  implicit val arbNegation: Arbitrary[Negation] = Arbitrary(notgen(depth))

  // Implication
  def if0gen: Gen[Implication] =
    for {
      comments <- commentSetArbGen
      antecedent <- simplesentencegen(0)
      consequent <- simplesentencegen(0)
    } yield new Implication(comments, antecedent, consequent)

  /**
   * generator for recursively defined Implication sentences,
   * of depth d
   */
  def ifgen(d: Int): Gen[Implication] =
    if (d <= 0) { if0gen }
    else {
      for {
        comments <- commentSetArbGen
        antecedent <- sentencegen(d - 1)
        consequent <- sentencegen(d - 1)
      } yield new Implication(comments, antecedent, consequent)
    }

  implicit val arbImplication: Arbitrary[Implication] = Arbitrary(ifgen(depth))

  // Existential
  def exists0gen: Gen[Existential] =
    for {
      comments <- commentSetArbGen
      bindings <- bindingsetgen
      body <- simplesentencegen(0)
    } yield new Existential(comments, bindings, body)

  /**
   * generator for recursively defined existential sentences,
   * of depth d
   */
  def existsgen(d: Int): Gen[Existential] =
    if (d <= 0) { exists0gen }
    else {
      for {
        comments <- commentSetArbGen
        bindings <- bindingsetgen
        body <- sentencegen(d - 1)
      } yield new Existential(comments, bindings, body)
    }

  implicit val arbExistential: Arbitrary[Existential] = Arbitrary(existsgen(depth))

  // Universal
  def forall0gen: Gen[Universal] =
    for {
      comments <- commentSetArbGen
      bindings <- bindingsetgen
      body <- simplesentencegen(0)
    } yield new Universal(comments, bindings, body)

  /**
   * generator for recursively defined existential sentences,
   * of depth d
   */
  def forallgen(d: Int): Gen[Universal] =
    if (d <= 0) { forall0gen }
    else {
      for {
        comments <- commentSetArbGen
        bindings <- bindingsetgen
        body <- sentencegen(d - 1)
      } yield new Universal(comments, bindings, body)
    }

  implicit val arbUniversal: Arbitrary[Universal] = Arbitrary(forallgen(depth))

  // QuantifiedSentence
  // TODO add universal
  def quantifiedsentencegen(d: Int): Gen[QuantifiedSentence] = Gen.frequency(
    (1, existsgen(d)),
    (1, forallgen(d)))

  implicit val arbQuantifiedSentence: Arbitrary[QuantifiedSentence] = Arbitrary(quantifiedsentencegen(depth))

  // BooleanSentence
  // TODO add some DEBUG conditional to control complex sentences
  def booleansentencegen(d: Int): Gen[BooleanSentence] = Gen.frequency(
    (MAX_SIZE / 2, bicondgen(d)),
    (MAX_SIZE / 2, ifgen(d)),
    (MAX_SIZE, notgen(d)),
    (1, andgen(d)),
    (1, orgen(d)))

  implicit val arbBooleanSentence: Arbitrary[BooleanSentence] = Arbitrary(booleansentencegen(depth))

  // Sentence

  def sentencegen(d: Int): Gen[Sentence] =
    if (d <= 0) { simplesentencegen(0) } else {
      Gen.frequency(
        (1, simplesentencegen(d)),
        (1, booleansentencegen(d - 1)),
        (1, quantifiedsentencegen(d - 1)))
    }

  implicit val arbSentence: Arbitrary[Sentence] = Arbitrary(sentencegen(depth))

  // SentenceSet
  def atomsetlistgen(d: Int): Gen[SentenceSet] =
    for { a <- Gen listOf (atomgen(d)) }
      yield a.toSet[Sentence]

  // TODO need other types of sequences
  def atomsetgen(d: Int): Gen[SentenceSet] = atomsetlistgen(d)

  def sentencesetlistgen(d: Int): Gen[SentenceSet] =
    for { a <- Gen listOf (sentencegen(d)) }
      yield a.toSet[Sentence]

  // TODO need other types of sets
  def sentencesetgen(d: Int): Gen[SentenceSet] = sentencesetlistgen(d)

  implicit val arbSentenceSet: Arbitrary[SentenceSet] = Arbitrary(sentencesetgen(depth))

  // Titling
  def titling0gen: Gen[Titling] =
    for {
      comments <- commentSetArbGen
      title <- nameArbgen
      body <- text0gen
    } yield new Titling(comments, title, body)

  def titlinggen(d: Int): Gen[Titling] =
    if (d <= 0) { titling0gen }
    else {
      for {
        comments <- commentSetArbGen
        title <- nameArbgen
        body <- textgen(d)
      } yield new Titling(comments, title, body)
    }

  implicit val arbTitling: Arbitrary[Titling] = Arbitrary(titlinggen(depth))

  // InDiscourseStatement
  def indisgen(d: Int): Gen[InDiscourseStatement] =
    for {
      comments <- commentSetArbGen
      tosms <- tosmsetgen(d)
    } yield new InDiscourseStatement(comments, tosms)

  implicit val arbInDiscourseStatement: Arbitrary[InDiscourseStatement] = Arbitrary(indisgen(depth))

  // OutDiscourseStatement
  def outdisgen(d: Int): Gen[OutDiscourseStatement] =
    for {
      comments <- commentSetArbGen
      tosms <- tosmsetgen(d)
    } yield new OutDiscourseStatement(comments, tosms)

  implicit val arbOutDiscourseStatement: Arbitrary[OutDiscourseStatement] = Arbitrary(outdisgen(depth))

  // DiscourseStatement

  def discoursegen(d: Int): Gen[DiscourseStatement] = Gen.frequency(
    (1, indisgen(d)),
    (1, outdisgen(d)))

  implicit val arbDiscourseStatement: Arbitrary[DiscourseStatement] = Arbitrary(discoursegen(depth))

  // Schema
  def forallm0gen: Gen[Schema] =
    for {
      comments <- commentSetArbGen
      seqbindings <- seqbindingsetgen
      body <- simplesentencegen(0)
    } yield new Schema(comments, seqbindings, body)

  /**
   * generator for recursively defined Schema statements,
   * of depth d
   */
  def forallmgen(d: Int): Gen[Schema] =
    if (d <= 0) { forallm0gen }
    else {
      for {
        comments <- commentSetArbGen
        seqbindings <- seqbindingsetgen
        body <- Gen.oneOf(sentencegen(d - 1), forallmgen(d - 1))
      } yield new Schema(comments, seqbindings, body)
    }

  implicit val arbSchema: Arbitrary[Schema] = Arbitrary(forallmgen(depth))

  // Statement

  def statementgen(d: Int): Gen[Statement] = Gen.frequency(
    (MAX_SIZE, discoursegen(d)),
    (1, titlinggen(d)),
    (1, forallmgen(d)))

  implicit val arbStatement: Arbitrary[Statement] = Arbitrary(statementgen(depth))

  // TextConstruction

  /**
   * generator for recursively defined text constructions,
   * of depth d
   */
  def constructgen(d: Int): Gen[TextConstruction] =
    for {
      comments <- commentSetArbGen
      expressions <- bexpressionsetgen(d)
    } yield new TextConstruction(comments, expressions)

  implicit val arbTextConstruction: Arbitrary[TextConstruction] = Arbitrary(constructgen(depth))

  // DomainRestriction
  def restrict0gen: Gen[DomainRestriction] =
    for {
      comments <- commentSetArbGen
      domain <- termArbGen(0)
      body <- textgen(0)
    } yield new DomainRestriction(comments, domain, body)

  /**
   * generator for recursively defined domain restrictions,
   * of depth d
   */
  def restrictgen(d: Int): Gen[DomainRestriction] =
    if (d <= 0) { restrict0gen }
    else {
      for {
        comments <- commentSetArbGen
        domain <- termArbGen(0)
        body <- textgen(d - 1)
      } yield new DomainRestriction(comments, domain, body)
    }

  implicit val arbDomainRestriction: Arbitrary[DomainRestriction] = Arbitrary(restrictgen(depth))

  // Importation
  def importgen: Gen[Importation] =
    for {
      comments <- commentSetArbGen
      title <- nameArbgen
    } yield new Importation(comments, title)

  implicit val arbImportation: Arbitrary[Importation] = Arbitrary(importgen)

  // Text
  def text0gen: Gen[Text] = Gen.frequency(
    (MAX_SIZE, importgen),
    (1, constructgen(0)))

  def textgen(d: Int): Gen[Text] =
    if (d <= 0) { text0gen }
    else {
      Gen.frequency(
        (MAX_SIZE, importgen),
        (1, restrictgen(d - 1)),
        (1, constructgen(d)))
    }

  implicit val arbText: Arbitrary[Text] = Arbitrary(textgen(depth))

  // BasicExpression
  def bexpression0gen: Gen[BasicExpression] = sentencegen(0)

  def bexpressiongen(d: Int): Gen[BasicExpression] =
    if (d == 0) bexpression0gen else
      Gen.frequency(
        (MAX_SIZE, sentencegen(d)),
        (MAX_SIZE, statementgen(d - 1)),
        (1, textgen(d - 1)))

  implicit val arbBasicExpression: Arbitrary[BasicExpression] = Arbitrary(bexpressiongen(depth))

  // BasicExpressionSet
  def bexpressionsetlistgen(d: Int): Gen[BasicExpressionSet] =
    for { a <- Gen listOf (bexpressiongen(d)) }
      yield a.toSet[BasicExpression]

  // TODO need other types of sets
  def bexpressionsetgen(d: Int): Gen[BasicExpressionSet] = Gen.frequency(
    (1, bexpressionsetlistgen(d)))

  implicit val arbBasicExpressionSet: Arbitrary[BasicExpressionSet] = Arbitrary(bexpressionsetgen(depth))

  // Expression
  // TODO add structured expressions
  def expressiongen(d: Int): Gen[Expression] = bexpressiongen(d)

  implicit val arbExpression: Arbitrary[Expression] = Arbitrary(expressiongen(depth))

  // ExpressionSet
  def expressionsetlistgen(d: Int): Gen[Set[Expression]] =
    for { a <- Gen listOf (expressiongen(d)) }
      yield a.toSet[Expression]

  // TODO need other types of sets
  def expressionsetgen(d: Int): Gen[Set[Expression]] = Gen.frequency(
    (1, expressionsetlistgen(d)))

  implicit val arbExpressionSet: Arbitrary[Set[Expression]] = Arbitrary(expressionsetgen(depth))

  // BindingSet
  def bindingsetlistgen: Gen[BindingSet] =
    for { a <- Gen listOf (inameArbGen) }
      yield a.toSet[InterpretableName]

  // TODO need other types of sets
  def bindingsetgen: Gen[BindingSet] = Gen.frequency(
    (1, bindingsetlistgen))

  implicit val arbbindingset: Arbitrary[BindingSet] = Arbitrary(bindingsetgen)

  // SeqBindingSet
  def seqbindingsetlistgen: Gen[SeqBindingSet] =
    for { a <- Gen listOf (sequencemarkerArbGen) }
      yield a.toSet[SequenceMarker]

  // TODO need other types of sets
  def seqbindingsetgen: Gen[SeqBindingSet] = Gen.frequency(
    (1, seqbindingsetlistgen))

  implicit val arbseqbindingset: Arbitrary[SeqBindingSet] = Arbitrary(seqbindingsetgen)

  // TOSMSet
  def tosmsetlistgen(d: Int): Gen[TOSMSet] =
    for { a <- Gen listOf (termorsequencemarkerArbGen(d)) }
      yield a.toSet[TermOrSequenceMarker]

  // TODO need other types of sets
  def tosmsetgen(d: Int): Gen[TOSMSet] = Gen.frequency(
    (1, tosmsetlistgen(d)))

  implicit val arbtosmset: Arbitrary[TOSMSet] = Arbitrary(tosmsetgen(depth))

  // Fragment
  def fragmentgen(d: Int): Gen[Fragment] = Gen.frequency(
    (MAX_SIZE, termArbGen(d)),
    (MAX_SIZE, sequencemarkerArbGen),
    (MAX_SIZE, commentArbGen))

  // BasicExpressionLike
  def basicexpressionlikegen(d: Int): Gen[BasicExpressionLike] = Gen.oneOf(
    fragmentgen(d), bexpressiongen(d))

  implicit val arbBasicExpressionLike: Arbitrary[BasicExpressionLike] = Arbitrary(basicexpressionlikegen(depth))

  // ExpressionLike
  // TODO is there any other kind of ExpressionLike other than basic?s
  def expressionlikegen(d: Int): Gen[ExpressionLike] = basicexpressionlikegen(d)

  implicit val arbExpressionLike: Arbitrary[ExpressionLike] = Arbitrary(expressionlikegen(depth))

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
