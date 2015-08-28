package cl2

import org.scalacheck.Gen
import org.scalatest._
import org.scalatest.matchers._
import prop.PropertyChecks
import scala.collection.immutable.List
import org.scalacheck.Arbitrary

import collection.JavaConversions._
import java.util.Arrays;

import api4kbj.KnowledgeSourceLevel._
import cl2a._
import cl2array._
import cl2jc._
import scala.language.postfixOps

object CLGenerators {
  
  
  /**
   * generator of Strings of length 1 or 2 corresponding to a
   * single Unicode character
   * matching the XML 1.0, version 5 Char production 
   */
   val xmlcharactergen: Gen[String] = 
     for {i <- Gen frequency (
         (3, Gen.oneOf(Seq(0x9, 0xA, 0xD))),    
         (0xD7FF - 0x20 + 1, Gen.choose(0x20, 0xD7FF)),    
         (0xFFFD - 0xE000 + 1, Gen.choose(0xE000, 0xFFFD)),    
         (0x10FFF - 0x10000 + 1, Gen.choose(0x10000, 0x10FFF))    
       ) }
     yield String copyValueOf (Character toChars i)
   
  /**
   * generator of arbitrary length Strings of Unicode characters
   * matching the XML 1.0, version 5 Char production 
   */
  val clstringsymbolgen: Gen[String] = 
    for {a <- Gen listOf (xmlcharactergen) }
    yield (a mkString)

  //CLComent
  /**
   * generator of CL comments whose comment data (symbol)
   * is String symbol according to clstringsymbolgen
   */
  val clstringcommentgen: Gen[CLStringComment] =
    for {s <- clstringsymbolgen}
    yield new CLStringComment(s)

  //TODO also need other kinds of comments
  val clcommentgen: Gen[CLComment] = clstringcommentgen

  implicit val arbCLComment = Arbitrary(clcommentgen)

 //CLCommentSet
  /**
   * generator of CL comment sets (array-based instances), which may be
   * used in generators of "commentable" CL terms and expressions    
   */
  val clcommentsetarraygen: Gen[CLCommentSet] =
    for {a <- Gen listOf (clcommentgen)}
    yield new CLCommentSetArray(a.toArray[CLComment]: _*)

  val clcommentsetjcgen: Gen[CLCommentSet] =
    for {a <- Gen listOf (clcommentgen)}
    yield new CLCommentSetJC(a.toArray[CLComment]: _*)

  val clcommentsetgen: Gen[CLCommentSet] = Gen.frequency(
    (1, clcommentsetarraygen),
    (1, clcommentsetjcgen))
  
  implicit val arbCLCommentSet = Arbitrary(clcommentsetgen)

  //CLInterpretableName
  /**
   * generator of CL interpretable name with a
   * String symbol, as defined for clstringsymbolgen
   */
  val clstringinamegen: Gen[CLStringInterpretableName] = for {s <- clstringsymbolgen}
    yield new CLStringInterpretableName(s)

  //TODO also need interpretable names with non-string symbols
  val clinamegen: Gen[CLInterpretableName] = clstringinamegen

  //CLInterpretedName
  /**
   * generator for a Cl interpreted name with fixed interpretation
   * as a string of Unicode characters and datatype xsd:string  
   */
  val clstringdatagen: Gen[CLStringIriInterpretedName] = for {s <- clstringsymbolgen}
    yield CLStringIriInterpretedName.createCLStringIriInterpretedNameFromString(s)

  //TODO also need other interpreted names from other datatypes
  val cldatagen: Gen[CLInterpretedName] = clstringdatagen

  //CLName
  /**
   * generator for CL names, with equal probability
   * of interpretable and interpreted names
   */
  val clnamegen: Gen[CLName] = Gen.frequency(
    (1, clinamegen),
    (1, cldatagen))

  //CLSequenceMarker
  /**
   * generator for CL sequence markers with string symbols  
   */
  val clstringsequencemarkergen =
    for { s <- clstringsymbolgen }
      yield new CLStringSequenceMarker(s)

  //TODO also need non-string sequence markers
  val clsequencemarkergen: Gen[CLSequenceMarker] = clstringsequencemarkergen
      
 //CLFunctionalTerm
  val depth = 3;
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
      if (d<=0) clfunctionalterm0gen
      else
        for {
          comments <- clcommentsetgen
          operator <- cltermgen(d-1)
          args <- cltermsequencegen(d-1)
        } yield new CLFunctionalTerm(comments, operator, args)

   implicit val arbCLFunctionalTerm = Arbitrary(clfunctionaltermgen(depth))

 //CLTerm
  val clterm0gen: Gen[CLTerm] = Gen.frequency(
    (100, clnamegen),
    (1, clfunctionalterm0gen))

  val clterm1gen: Gen[CLTerm] = Gen.frequency(
    (100, clnamegen),
    (1, clfunctionalterm1gen))

  def cltermgen(d: Int): Gen[CLTerm] = Gen.frequency(
      (100, clnamegen),
      (1, clfunctionaltermgen(d)))

  implicit val arbCLTerm = Arbitrary(cltermgen(depth))
    
 //CLTermOrSequenceMarker
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

 //CLTermSequence
  /**
   * generator for terms sequences containing no functional term
   */
  val clnamesequencearraygen: Gen[CLTermSequence] =
    for {a <- Gen listOf (clnameorsequencemarkergen)}
      yield new CLTermSequenceArray(a.toArray[CLTermOrSequenceMarker]: _*)

  //TODO need other types of sequences
  val clnamesequencegen = clnamesequencearraygen
  
  /**
   * generator for terms sequences containing functional term of depth at most zero
   */
  val clterm0sequencearraygen: Gen[CLTermSequence] =
    for {a <- Gen listOf (clterm0orsequencemarkergen)}
      yield new CLTermSequenceArray(a.toArray[CLTermOrSequenceMarker]: _*)

      //TODO need other types of sequences
  val clterm0sequencegen = clterm0sequencearraygen

  def cltermsequencearraygen(d: Int): Gen[CLTermSequenceArray] =
    for {a <- Gen listOf (cltermorsequencemarkergen(d))}
      yield new CLTermSequenceArray(a.toArray[CLTermOrSequenceMarker]: _*)

  //TODO need other types of sequences
  def cltermsequencegen(d:Int):Gen[CLTermSequence] = cltermsequencearraygen(d)

  implicit val arbCLTermSequence = Arbitrary(cltermsequencegen(depth))
      
      
  def clatomgen(d: Int): Gen[CLAtomicSentence] =
    for {
      comments <- clcommentsetgen
      operator <- cltermgen(d)
      args <- cltermsequencegen(d)
    } yield new CLAtomicSentence(comments, operator, args)
    
  implicit val arbCLAtomicSentence = Arbitrary(clatomgen(depth))

  def clbicondgen(d: Int): Gen[CLBiconditional] =
    for {
      comments <- clcommentsetgen
      left <- clsentencegen(d)
      right <- clsentencegen(d)
    } yield new CLBiconditional(comments, left, right)
    
  implicit val arbCLBiconditional = Arbitrary(clbicondgen(depth))
  
  //TODO add more types of Sentence
  def clsentencegen(d: Int): Gen[CLSentence] = clatomgen(d)

  implicit val arbCLSentence = Arbitrary(clsentencegen(depth))
  
  //TODO add statements and texts also
  def clexpressiongen(d: Int): Gen[CLExpression] = clsentencegen(d)

  implicit val arbCLExpression = Arbitrary(clexpressiongen(depth))
  
  def clexpressionlikegen(d: Int): Gen[CLExpressionLike] = Gen.frequency(
    (1, cltermgen(depth)),
    (1, clsequencemarkergen),
    (1, clcommentgen)
    )

  implicit val arbCLExpressionLike = Arbitrary(clexpressionlikegen(depth))
  
  //TODO make a valid IRI generator by assembling from segments
  //IRI            = scheme ":" ihier-part [ "?" iquery ]
   //                         [ "#" ifragment ]
  //val irischemegen:Gen[String] =
    
  // iprivate       = %xE000-F8FF / %xF0000-FFFFD / %x100000-10FFFD
   val iriiprivategen: Gen[String] = 
     for {i <- Gen frequency (
         (0xF8FF - 0xE000 + 1, Gen.choose(0xE000, 0xF8FF)),    
         (0xFFFFD - 0xF0000 + 1, Gen.choose(0xF0000, 0xFFFFD)),    
         (0x10FFFD - 0x100000 + 1, Gen.choose(0x100000, 0x10FFFD))    
       ) }
     yield String copyValueOf (Character toChars i)

  //TODO use the IRI generator to make a data generator with arbitrary IRI datatype

  //TODO make generators for other kinds of symbols - Image, objects as XML serializations (JAXB?)

}
