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

import org.scalacheck.Gen
import org.scalacheck.Arbitrary
import scala.language.postfixOps

object CLGenerators {

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
  val clstringsymbolgen: Gen[String] =
    for { a <- Gen listOf (xmlcharactergen) }
      yield (a mkString)

  // CLComent
  /**
   * generator of CL comments whose comment data (symbol)
   * is String symbol according to clstringsymbolgen
   */
  val clstringcommentgen: Gen[CLStringComment] =
    for { s <- clstringsymbolgen }
      yield new CLStringComment(s)

  // TODO also need other kinds of comments
  val clcommentgen: Gen[CLComment] = clstringcommentgen

  implicit val arbCLComment = Arbitrary(clcommentgen)

  // CLCommentSet
  /**
   * generator of CL comment sets (array-based instances), which may be
   * used in generators of "commentable" CL terms and expressions
   */
  val clcommentsetarraygen: Gen[CLCommentSet] =
    for { a <- Gen listOf (clcommentgen) }
      yield new CLCommentSetArray(a.toArray[CLComment]: _*)

  val clcommentsetjcgen: Gen[CLCommentSet] =
    for { a <- Gen listOf (clcommentgen) }
      yield new CLCommentSetJC(a.toArray[CLComment]: _*)

  val clcommentsetgen: Gen[CLCommentSet] = Gen.frequency(
    (1, clcommentsetarraygen),
    (1, clcommentsetjcgen))

  implicit val arbCLCommentSet = Arbitrary(clcommentsetgen)

  // CLInterpretableName
  /**
   * generator of CL interpretable name with a
   * String symbol, as defined for clstringsymbolgen
   */
  val clstringinamegen: Gen[CLStringInterpretableName] = for { s <- clstringsymbolgen }
    yield new CLStringInterpretableName(s)

  // TODO also need interpretable names with non-string symbols
  val clinamegen: Gen[CLInterpretableName] = clstringinamegen

  implicit val arbCLInterpretableName = Arbitrary(clinamegen)

  // CLInterpretedName
  /**
   * generator for a Cl interpreted name with fixed interpretation
   * as a string of Unicode characters and datatype xsd:string
   */
  val clstringdatagen: Gen[CLStringIriInterpretedName] = for { s <- clstringsymbolgen }
    yield CLStringIriInterpretedName.createCLStringIriInterpretedNameFromString(s)

  // TODO also need other interpreted names from other datatypes
  val cldatagen: Gen[CLInterpretedName] = clstringdatagen

  implicit val arbCLData = Arbitrary(cldatagen)

  // CLName
  /**
   * generator for CL names, with equal probability
   * of interpretable and interpreted names
   */
  val clnamegen: Gen[CLName] = Gen.frequency(
    (1, clinamegen),
    (1, cldatagen))

  implicit val arbCLName = Arbitrary(clnamegen)

  // CLSequenceMarker
  /**
   * generator for CL sequence markers with string symbols
   */
  val clstringsequencemarkergen =
    for { s <- clstringsymbolgen }
      yield new CLStringSequenceMarker(s)

  // TODO also need non-string sequence markers
  val clsequencemarkergen: Gen[CLSequenceMarker] = clstringsequencemarkergen

  implicit val arbCLSequenceMarker = Arbitrary(clsequencemarkergen)

  // CLFunctionalTerm
  val depth = 4;
  /**
   * generator for zero-depth functional terms,
   * having no nested functional terms
   */
  val clfunctionalterm0gen: Gen[CLFunctionalTerm] =
    for {
      comments <- clcommentsetgen
      operator <- clnamegen
      args <- clnamesequencegen
    } yield new CLFunctionalTerm(comments, operator, args)

  /**
   * generator for functional terms with depth 1,
   * possibly having nested zero-depth functional terms
   */
  val clfunctionalterm1gen: Gen[CLFunctionalTerm] =
    for {
      comments <- clcommentsetgen
      operator <- clterm0gen
      args <- clterm0sequencegen
    } yield new CLFunctionalTerm(comments, operator, args)

  /**
   * generator for recursively defined functional terms,
   * of depth d
   */
  def clfunctionaltermgen(d: Int): Gen[CLFunctionalTerm] =
    if (d <= 0) { clfunctionalterm0gen }
    else {
      for {
        comments <- clcommentsetgen
        operator <- cltermgen(d - 1)
        args <- cltermsequencegen(d - 1)
      } yield new CLFunctionalTerm(comments, operator, args)
    }

  implicit val arbCLFunctionalTerm = Arbitrary(clfunctionaltermgen(depth))

  // CLTerm
  val clterm0gen: Gen[CLTerm] = Gen.frequency(
    (MAX_SIZE, clnamegen),
    (1, clfunctionalterm0gen))

  val clterm1gen: Gen[CLTerm] = Gen.frequency(
    (MAX_SIZE, clnamegen),
    (1, clfunctionalterm1gen))

  def cltermgen(d: Int): Gen[CLTerm] = Gen.frequency(
    (MAX_SIZE, clnamegen),
    (1, clfunctionaltermgen(d)))

  implicit val arbCLTerm = Arbitrary(cltermgen(depth))

  // CLTermOrSequenceMarker
  val clnameorsequencemarkergen: Gen[CLTermOrSequenceMarker] = Gen.frequency(
    (1, clnamegen),
    (1, clsequencemarkergen))

  val clterm0orsequencemarkergen: Gen[CLTermOrSequenceMarker] = Gen.frequency(
    (1, clterm0gen),
    (1, clsequencemarkergen))

  val clterm1orsequencemarkergen: Gen[CLTermOrSequenceMarker] = Gen.frequency(
    (1, clterm1gen),
    (1, clsequencemarkergen))

  def cltermorsequencemarkergen(d: Int): Gen[CLTermOrSequenceMarker] = Gen.frequency(
    (1, cltermgen(d)),
    (1, clsequencemarkergen))

  implicit val arbCLTermOrSequenceMarker = Arbitrary(clterm1orsequencemarkergen)

  // CLTermSequence
  /**
   * generator for terms sequences containing no functional term
   */
  val clnamesequencearraygen: Gen[CLTermSequence] =
    for { a <- Gen listOf (clnameorsequencemarkergen) }
      yield new CLTermSequenceArray(a.toArray[CLTermOrSequenceMarker]: _*)

  // TODO need other types of sequences
  val clnamesequencegen = clnamesequencearraygen

  /**
   * generator for terms sequences containing functional term of depth at most zero
   */
  val clterm0sequencearraygen: Gen[CLTermSequence] =
    for { a <- Gen listOf (clterm0orsequencemarkergen) }
      yield new CLTermSequenceArray(a.toArray[CLTermOrSequenceMarker]: _*)

  // TODO need other types of sequences
  val clterm0sequencegen = clterm0sequencearraygen

  def cltermsequencearraygen(d: Int): Gen[CLTermSequenceArray] =
    for { a <- Gen listOf (cltermorsequencemarkergen(d)) }
      yield new CLTermSequenceArray(a.toArray[CLTermOrSequenceMarker]: _*)

  // TODO need other types of sequences
  def cltermsequencegen(d: Int): Gen[CLTermSequence] = cltermsequencearraygen(d)

  implicit val arbCLTermSequence = Arbitrary(cltermsequencegen(depth))

  // CLAtomicSentence
  def clatomgen(d: Int): Gen[CLAtomicSentence] =
    for {
      comments <- clcommentsetgen
      operator <- cltermgen(d)
      args <- cltermsequencegen(d)
    } yield new CLAtomicSentence(comments, operator, args)

  implicit val arbCLAtomicSentence = Arbitrary(clatomgen(depth))

  // CLBiconditional
  def clbicond0gen: Gen[CLBiconditional] =
    for {
      comments <- clcommentsetgen
      left <- clatomgen(0)
      right <- clatomgen(0)
    } yield new CLBiconditional(comments, left, right)

  /**
   * generator for recursively defined biconditional sentences,
   * of depth d
   */
  def clbicondgen(d: Int): Gen[CLBiconditional] =
    if (d <= 0) { clbicond0gen }
    else {
      for {
        comments <- clcommentsetgen
        left <- clsentencegen(d - 1)
        right <- clsentencegen(d - 1)
      } yield new CLBiconditional(comments, left, right)
    }

  implicit val arbCLBiconditional = Arbitrary(clbicondgen(depth))

  // CLConjunction
  def cland0gen: Gen[CLConjunction] =
    for {
      comments <- clcommentsetgen
      sentences <- clatomsequencegen(0)
    } yield new CLConjunction(comments, sentences)

  /**
   * generator for recursively defined conjunction sentences,
   * of depth d
   */
  def clandgen(d: Int): Gen[CLConjunction] =
    if (d <= 0) { cland0gen }
    else {
      for {
        comments <- clcommentsetgen
        sentences <- clsentencesequencegen(d - 1)
      } yield new CLConjunction(comments, sentences)
    }

  implicit val arbCLConjunction = Arbitrary(clandgen(depth))

  // CLDisjunction
  def clor0gen: Gen[CLDisjunction] =
    for {
      comments <- clcommentsetgen
      sentences <- clatomsequencegen(0)
    } yield new CLDisjunction(comments, sentences)

  /**
   * generator for recursively defined conjunction sentences,
   * of depth d
   */
  def clorgen(d: Int): Gen[CLDisjunction] =
    if (d <= 0) { clor0gen }
    else {
      for {
        comments <- clcommentsetgen
        sentences <- clsentencesequencegen(d - 1)
      } yield new CLDisjunction(comments, sentences)
    }

  implicit val arbCLDisjunction = Arbitrary(clorgen(depth))

  // TODO add more types of Sentence
  def clsentencegen(d: Int): Gen[CLSentence] = Gen.frequency(
    (MAX_SIZE * MAX_SIZE, clatomgen(d)),
    (MAX_SIZE, clbicondgen(d)),
    (1, clandgen(d)),
    (1, clorgen(d)))

  implicit val arbCLSentence = Arbitrary(clsentencegen(depth))

  // CLSentenceSequence
  def clatomsequencearraygen(d: Int): Gen[CLSentenceSequenceArray] =
    for { a <- Gen listOf (clatomgen(d)) }
      yield new CLSentenceSequenceArray(a.toArray[CLSentence]: _*)

  // TODO need other types of sequences
  def clatomsequencegen(d: Int): Gen[CLSentenceSequence] = clatomsequencearraygen(d)

  def clsentencesequencearraygen(d: Int): Gen[CLSentenceSequenceArray] =
    for { a <- Gen listOf (clsentencegen(d)) }
      yield new CLSentenceSequenceArray(a.toArray[CLSentence]: _*)

  // TODO need other types of sequences
  def clsentencesequencegen(d: Int): Gen[CLSentenceSequence] = clsentencesequencearraygen(d)

  implicit val arbCLSentenceSequence = Arbitrary(clsentencesequencegen(depth))

  // CLTextConstruction
  def clconstruct0gen: Gen[CLTextConstruction] =
    for {
      comments <- clcommentsetgen
      sentences <- clsentencesequencegen(0)
    } yield new CLTextConstruction(comments, sentences)

  /**
   * generator for recursively defined text constructions,
   * of depth d
   */
  def clconstructgen(d: Int): Gen[CLTextConstruction] =
    if (d <= 0) { clconstruct0gen }
    else {
      for {
        comments <- clcommentsetgen
        expressions <- clexpressionsequencegen(d - 1)
      } yield new CLTextConstruction(comments, expressions)
    }

  implicit val arbCLTextConstruction = Arbitrary(clconstructgen(depth))

  // CLText

  // TODO add other kinds of texts: imports, domain restrictions
  def cltextgen(d: Int): Gen[CLText] = clconstructgen(d)

  implicit val arbCLText = Arbitrary(cltextgen(depth))

  // CLExpression
  // TODO add statements also
  def clexpressiongen(d: Int): Gen[CLExpression] = Gen.frequency(
    (MAX_SIZE * MAX_SIZE, clsentencegen(d)),
    (1, cltextgen(d)))

  implicit val arbCLExpression = Arbitrary(clexpressiongen(depth))

  // CLExpressionSequence
  def clexpressionsequencearraygen(d: Int): Gen[CLExpressionSequenceArray] =
    for { a <- Gen listOf (clexpressiongen(d)) }
      yield new CLExpressionSequenceArray(a.toArray[CLExpression]: _*)

  // TODO need other types of sequences
  def clexpressionsequencegen(d: Int): Gen[CLExpressionSequence] = clexpressionsequencearraygen(d)

  implicit val arbCLExpressionSequence = Arbitrary(clexpressionsequencegen(depth))

  // CLExpressionLike
  // TODO add sequences and sets also?
  def clexpressionlikegen(d: Int): Gen[CLExpressionLike] = Gen.frequency(
    (1, cltermgen(d)),
    (1, clsequencemarkergen),
    (1, clcommentgen))

  implicit val arbCLExpressionLike = Arbitrary(clexpressionlikegen(depth))

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
