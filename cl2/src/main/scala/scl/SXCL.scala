package scl

import scala.language.postfixOps
import com.typesafe.scalalogging._
import api4kbj.KnowledgeExpressionLike
import api4kbj.BasicKnowledgeExpressionLike
import api4kba.AbstractKRRLanguage
import api4kba.AbstractKRRLogic
import language.existentials
import org.apache.commons.lang3.StringEscapeUtils
import XMLHelper._
import scala.xml.{ Elem, Node, NodeSeq }
import org.apache.commons.codec.binary.Base64
import org.apache.commons.lang3.SerializationUtils
import scala.util.{ Try, Success, Failure }

/**
 * @author taraathan
 */
object XMLHelper {
  implicit class StringCommentXMLHelper(x: StringComment) {
    def toXML: Elem =
      <cl:Comment xmlns:cl={ SCL.URI_XCL2 }>{ StringEscapeUtils escapeXml11 ((x data) toString) }</cl:Comment>
  }
  implicit class CommentXMLHelper(x: Comment) {
    def toXML: Elem = x match {
      case a: StringComment => (a toXML)
      case _ => <cl:Comment xmlns:cl={ SCL.URI_XCL2 }>{
        Base64.encodeBase64String(
          SerializationUtils.serialize(x data))
      }</cl:Comment>
    }
  }
  implicit class StringInterpretableNameXMLHelper(x: StringInterpretableName) {
    def toXML: Elem =
      <cl:Name xmlns:cl={ SCL.URI_XCL2 }>{ StringEscapeUtils.escapeXml11((x symbol) toString) }</cl:Name>
  }
  implicit class InterpretableNameXMLHelper(x: InterpretableName) {
    def toXML: Elem = x match {
      case a: StringInterpretableName => (a toXML)
      case _ => <cl:Name xmlns:cl={ SCL.URI_XCL2 }>{
        Base64.encodeBase64String(
          SerializationUtils.serialize(x symbol))
      }</cl:Name>
    }
  }
  implicit class StringIriInterpretedNameXMLHelper(x: StringIriInterpretedName) {
    def toXML: Elem =
      <cl:Data xmlns:cl={ SCL.URI_XCL2 } datatype={ StringEscapeUtils escapeXml11 ((x datatype) toString) }>{ StringEscapeUtils escapeXml11 ((x symbol) toString) }</cl:Data>
  }
  // TODO add datatype
  implicit class InterpretedNameXMLHelper(x: InterpretedName) {
    def toXML: Elem = x match {
      case a: StringIriInterpretedName => (a toXML)
      case _ => <cl:Data xmlns:cl={ SCL.URI_XCL2 }>{
        Base64.encodeBase64String(
          SerializationUtils.serialize(x symbol))
      }</cl:Data>
    }
  }
  implicit class FunctionalTermXMLHelper(x: FunctionalTerm) {
    def toXML: Elem = <cl:Apply xmlns:cl={ SCL.URI_XCL2 }>{ ((x comments) toXML) }{ ((x operator) toXML) }{ ((x args) toXML) }</cl:Apply>
  }
  implicit class TermXMLHelper(x: Term) {
    def toXML: Elem = x match {
      case a: InterpretableName => (a toXML)
      case a: InterpretedName => (a toXML)
      case a: FunctionalTerm => (a toXML)
    }
  }
  implicit class StringSequenceMarkerXMLHelper(x: StringSequenceMarker) {
    def toXML: Elem =
      <cl:Marker xmlns:cl={ SCL.URI_XCL2 }>{ StringEscapeUtils.escapeXml11((x symbol) toString) }</cl:Marker>
  }
  implicit class SequenceMarkerXMLHelper(x: SequenceMarker) {
    def toXML: Elem = x match {
      case a: StringSequenceMarker => (a toXML)
      case _ => <cl:Marker xmlns:cl={ SCL.URI_XCL2 }>{
        Base64.encodeBase64String(
          SerializationUtils.serialize(x symbol))
      }</cl:Marker>
    }
  }
  implicit class TermOrSequenceMarkerXMLHelper(x: TermOrSequenceMarker) {
    def toXML: Elem = x match {
      case a: Term => (a toXML)
      case a: SequenceMarker => (a toXML)
    }
  }
  implicit class CommentSetXMLHelper(x: Set[_ <: Comment]) {
    def toXML: NodeSeq = {
      val y = (x.toSeq)
      for (comment <- y) yield (comment toXML)
    }
  }
  implicit class TermSetXMLHelper(x: Set[_ <: Term]) {
    def toXML: NodeSeq = {
      val y = (x.toSeq)
      for (term <- y) yield (term toXML)
    }
  }
  implicit class BindingSetXMLHelper(x: Set[_ <: InterpretableName]) {
    def toXML: NodeSeq = {
      val y = (x.toSeq)
      for (name <- y) yield (name toXML)
    }
  }
  implicit class SeqBindingSetXMLHelper(x: Set[_ <: SequenceMarker]) {
    def toXML: NodeSeq = {
      val y = (x.toSeq)
      for (seq <- y) yield (seq toXML)
    }
  }
  implicit class TermSequenceXMLHelper(x: List[TermOrSequenceMarker]) {
    def toXML: NodeSeq = {
      for (tosm <- x) yield (tosm toXML)
    }
  }
  implicit class TermOrSequenceMarkerSetXMLHelper(x: Set[_ <: TermOrSequenceMarker]) {
    def toXML: NodeSeq = {
      val y = (x.toSeq)
      for (tosm <- y) yield (tosm toXML)
    }
  }
  implicit class AtomicSentenceXMLHelper(x: AtomicSentence) {
    def toXML: Elem = <cl:Atom xmlns:cl={ SCL.URI_XCL2 }>
                        { ((x comments) toXML) }
                        { ((x operator) toXML) }
                        { ((x args) toXML) }
                      </cl:Atom>
  }
  implicit class EquationXMLHelper(x: Equation) {
    def toXML: Elem = {
      <cl:Equal xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x terms) toXML) }
        { if (((x terms) size) == 1) ((x terms) toXML) }
      </cl:Equal>
    }
  }
  implicit class BiconditionalXMLHelper(x: Biconditional) {
    def toXML: Elem = {
      <cl:Biconditional xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x sentences) toXML) }
        { if (((x sentences) size) == 1) ((x sentences) toXML) }
      </cl:Biconditional>
    }
  }
  implicit class ConjunctionXMLHelper(x: Conjunction) {
    def toXML: Elem = {
      <cl:Conjunction xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x conjuncts) toXML) }
      </cl:Conjunction>
    }
  }
  implicit class DisjunctionXMLHelper(x: Disjunction) {
    def toXML: Elem = {
      <cl:Disjunction xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x disjuncts) toXML) }
      </cl:Disjunction>
    }
  }
  implicit class NegationXMLHelper(x: Negation) {
    def toXML: Elem = {
      <cl:Negation xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x body) toXML) }
      </cl:Negation>
    }
  }
  implicit class ImplicationXMLHelper(x: Implication) {
    def toXML: Elem = {
      <cl:Implication xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x antecedent) toXML) }
        { ((x consequent) toXML) }
      </cl:Implication>
    }
  }
  implicit class ExistentialXMLHelper(x: Existential) {
    def toXML: Elem = {
      <cl:Existential xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x bindings) toXML) }
        { ((x body) toXML) }
      </cl:Existential>
    }
  }
  implicit class UniversalXMLHelper(x: Universal) {
    def toXML: Elem = {
      <cl:Universal xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x bindings) toXML) }
        { ((x body) toXML) }
      </cl:Universal>
    }
  }
  implicit class SentenceXMLHelper(x: Sentence) {
    def toXML: Elem = x match {
      case a: AtomicSentence => (a toXML)
      case a: Equation => (a toXML)
      case a: Negation => (a toXML)
      case a: Conjunction => (a toXML)
      case a: Disjunction => (a toXML)
      case a: Biconditional => (a toXML)
      case a: Implication => (a toXML)
      case a: Existential => (a toXML)
      case a: Universal => (a toXML)
    }
  }
  implicit class SentenceSetXMLHelper(x: Set[_ <: Sentence]) {
    def toXML: NodeSeq = {
      val y = (x.toSeq)
      for (sentence <- y) yield (sentence toXML)
    }
  }
  implicit class TitlingXMLHelper(x: Titling) {
    def toXML: Elem = {
      <cl:Titling xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x title) toXML) }
        { ((x body) toXML) }
      </cl:Titling>
    }
  }
  implicit class SchemaXMLHelper(x: Schema) {
    def toXML: Elem = {
      <cl:Schema xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x seqbindings) toXML) }
        { ((x body) toXML) }
      </cl:Schema>
    }
  }
  implicit class InDiscourseStatementXMLHelper(x: InDiscourseStatement) {
    def toXML: Elem = {
      <cl:InDiscourseStatement xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x terms) toXML) }
      </cl:InDiscourseStatement>
    }
  }
  implicit class OutDiscourseStatementXMLHelper(x: OutDiscourseStatement) {
    def toXML: Elem = {
      <cl:OutDiscourseStatement xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x terms) toXML) }
      </cl:OutDiscourseStatement>
    }
  }
  implicit class StatementXMLHelper(x: Statement) {
    def toXML: Elem = x match {
      case a: Titling => (a toXML)
      case a: Schema => (a toXML)
      case a: InDiscourseStatement => (a toXML)
      case a: OutDiscourseStatement => (a toXML)
    }
  }
  implicit class TextConstructionXMLHelper(x: TextConstruction) {
    def toXML: Elem = {
      <cl:TextConstruction xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x expressions) toXML) }
      </cl:TextConstruction>
    }
  }
  implicit class DomainRestrictionXMLHelper(x: DomainRestriction) {
    def toXML: Elem = {
      <cl:DomainRestriction xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x domain) toXML) }
        { ((x body) toXML) }
      </cl:DomainRestriction>
    }
  }
  implicit class ImportationXMLHelper(x: Importation) {
    def toXML: Elem = {
      <cl:Importation xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x title) toXML) }
      </cl:Importation>
    }
  }
  implicit class TextXMLHelper(x: Text) {
    def toXML: Elem = x match {
      case a: TextConstruction => (a toXML)
      case a: DomainRestriction => (a toXML)
      case a: Importation => (a toXML)
    }
  }
  implicit class BasicExpressionXMLHelper(x: BasicExpression) {
    def toXML: Elem = x match {
      case a: Text => (a toXML)
      case a: Statement => (a toXML)
      case a: Sentence => (a toXML)
    }
  }
  implicit class BasicExpressionSetXMLHelper(x: Set[_ <: BasicExpression]) {
    def toXML: NodeSeq = {
      val y = (x.toSeq)
      for (basic <- y) yield (basic toXML)
    }
  }
}
object Parser {
  trait XCLParsable[T] {
    def fromXML(x: Elem): Try[T]
  }
  object XCLParsable {
    implicit object XCLParsableComment
        extends XCLParsable[Comment] {
      // TODO parse data datatype
      def fromXML(x: Elem): Try[Comment] =
        Try(
          x match {
            case e: Elem if (isXCLComment(e)) => new StringComment(StringEscapeUtils unescapeXml (x text))
          })
    }
    implicit object XCLParsableInterpretableName
        extends XCLParsable[InterpretableName] {
      // TODO parse symbol datatype
      def fromXML(x: Elem): Try[InterpretableName] =
        Try(
          x match {
            case e: Elem if (isXCLInterpretableName(e)) => new StringInterpretableName(StringEscapeUtils unescapeXml (x text))
          })
    }
    implicit object XCLParsableInterpretedName
        extends XCLParsable[InterpretedName] {
      // TODO parse datatype
      def fromXML(x: Elem): Try[InterpretedName] =
        Try(
          x match {
            case e: Elem if (isXCLInterpretedName(e)) => new StringIriInterpretedName(StringEscapeUtils unescapeXml (x text))
          })
    }
    implicit object XCLParsableSequenceMarker
        extends XCLParsable[SequenceMarker] {
      // TODO parse symbol datatype
      def fromXML(x: Elem): Try[SequenceMarker] =
        Try(
          x match {
            case e: Elem if (isXCLSequenceMarker(e)) => new StringSequenceMarker(StringEscapeUtils unescapeXml (x text))
          })
    }
    implicit object XCLParsableFunctionalTerm
        extends XCLParsable[FunctionalTerm] {
      def fromXML(x: Elem): Try[FunctionalTerm] = Try({
        val comments: Set[Comment] = commentSetParse(x)
        val argsAll: List[TermOrSequenceMarker] = tosmSeqParse(x)
        val operator: Term = argsAll.head match {
          case t: Term => t
        }
        val args: List[TermOrSequenceMarker] = argsAll.tail
        FunctionalTerm(comments, operator, args)
      })
    }
    def isXCLComment(x: Node): Boolean = ((x label) equals "Comment")
    def isXCLInterpretableName(x: Node): Boolean = ((x label) equals "Name")
    def isXCLInterpretedName(x: Node): Boolean = ((x label) equals "Data")
    def isXCLFunctionalTerm(x: Node): Boolean = ((x label) equals "Apply")
    def isXCLSequenceMarker(x: Node): Boolean = ((x label) equals "Marker")
    def isXCLName(x: Node): Boolean = isXCLInterpretableName(x) || isXCLInterpretedName(x)
    def isXCLNOSM(x: Node): Boolean = isXCLName(x) || isXCLSequenceMarker(x)
    def isXCLTerm(x: Node): Boolean = isXCLName(x) || isXCLFunctionalTerm(x)
    def isXCLTOSM(x: Node): Boolean = isXCLTerm(x) || isXCLSequenceMarker(x)
    implicit object XCLParsableTerm
        extends XCLParsable[Term] {
      // TODO stub
      def fromXML(x: Elem): Try[Term] =
        Try(
          x match {
            case e: Elem if (isXCLInterpretableName(e)) => XCLParsableInterpretableName.fromXML(e) get
            case e: Elem if (isXCLInterpretedName(e)) => XCLParsableInterpretedName.fromXML(e) get
            case e: Elem if (isXCLFunctionalTerm(e)) => XCLParsableFunctionalTerm.fromXML(e) get
          })
    }
    implicit object XCLParsableTOSM
        extends XCLParsable[TermOrSequenceMarker] {
      // TODO stub
      def fromXML(x: Elem): Try[TermOrSequenceMarker] = Try(x match {
        case e: Elem if (isXCLTerm(e)) => XCLParsableTerm.fromXML(e) get
        case e: Elem if (isXCLSequenceMarker(e)) => XCLParsableSequenceMarker.fromXML(e) get
      })
    }
    implicit object XCLParsableAtomicSentence
        extends XCLParsable[AtomicSentence] {
      def fromXML(x: Elem): Try[AtomicSentence] = Try({
        val comments: Set[Comment] = commentSetParse(x)
        val argsAll: List[TermOrSequenceMarker] = tosmSeqParse(x)
        val operator: Term = argsAll.head match {
          case t: Term => t
        }
        val args: List[TermOrSequenceMarker] = argsAll.tail
        AtomicSentence(comments, operator, args)
      })
    }

    implicit object XCLParsableEquation
        extends XCLParsable[Equation] {
      def fromXML(x: Elem): Try[Equation] = Try({
        val comments: Set[Comment] = commentSetParse(x)
        val args: Set[Term] = termSetParse(x)
        Equation(comments, args)
      })
    }

    val _tryCommentParse: PartialFunction[Node, Try[Comment]] = { case x: Elem => (XCLParsableComment fromXML x) }
    val _commentParse: PartialFunction[Node, Comment] = _tryCommentParse andThenPartial {
      case s: Success[Comment] => s get
    }
    def commentSetParse(x: Node): Set[Comment] = (x child) collect _commentParse toSet

    val _tryTosmParse: PartialFunction[Node, Try[TermOrSequenceMarker]] = { case x: Elem => (XCLParsableTOSM fromXML x) }
    val _tosmParse: PartialFunction[Node, TermOrSequenceMarker] = _tryTosmParse andThenPartial {
      case s: Success[TermOrSequenceMarker] => s get
    }
    def tosmSeqParse(x: Node): List[TermOrSequenceMarker] = (x child) collect _tosmParse toList

    val _tryTermParse: PartialFunction[Node, Try[Term]] = { case x: Elem => (XCLParsableTerm fromXML x) }
    val _termParse: PartialFunction[Node, Term] = _tryTermParse andThenPartial {
      case s: Success[Term] => s get
    }
    def termSetParse(x: Node): Set[Term] = (x child) collect _termParse toSet

  }

  implicit class ComposePartial[A, B](pf: PartialFunction[A, B]) {
    def andThenPartial[C](that: PartialFunction[B, C]): PartialFunction[A, C] =
      Function.unlift(pf.lift(_) flatMap that.lift)
  }
}
