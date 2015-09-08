package scl

import com.typesafe.scalalogging._
import org.apache.commons.codec.binary.Base64
import org.apache.commons.lang3.{ SerializationUtils, StringEscapeUtils }
import scala.language.{ postfixOps, existentials }
import scala.util.{ Try, Success, Failure }
import scala.xml.{ Elem, Node, NodeSeq }
import SCL._

/**
 * @author taraathan
 */
object SXCLManifester {

  implicit class StringCommentXMLHelper(x: StringComment) {
    def toXML: Elem =
      <cl:Comment xmlns:cl={ SCL.URI_XCL2 }>{ StringEscapeUtils escapeXml11((x data) toString) }</cl:Comment>
  }

  implicit class CommentXMLHelper(x: Comment) {
    def label: String = "Comment"
    def toXML: Elem = x match {
      case a: StringComment =>
        (a toXML)
      case _ =>
        <cl:Comment xmlns:cl={ SCL.URI_XCL2 }>{ Base64.encodeBase64String(SerializationUtils.serialize(x data)) }</cl:Comment>
    }
  }

  implicit class StringInterpretableNameXMLHelper(x: StringInterpretableName) {
    def toXML: Elem =
      <cl:Name xmlns:cl={ SCL.URI_XCL2 }>{ StringEscapeUtils.escapeXml11((x symbol) toString) }</cl:Name>
  }

  implicit class InterpretableNameXMLHelper(x: InterpretableName) {
    def toXML: Elem = x match {
      case a: StringInterpretableName =>
        (a toXML)
      case _ =>
        <cl:Name xmlns:cl={ SCL.URI_XCL2 }>{ Base64.encodeBase64String(SerializationUtils.serialize(x symbol)) }</cl:Name>
    }
  }

  implicit class StringIriInterpretedNameXMLHelper(x: StringIriInterpretedName) {
    def toXML: Elem =
      <cl:Data xmlns:cl={ SCL.URI_XCL2 } datatype={ StringEscapeUtils escapeXml11((x datatype) toString) }>{ StringEscapeUtils escapeXml11((x symbol) toString) }</cl:Data>
  }

  // TODO add datatype
  implicit class InterpretedNameXMLHelper(x: InterpretedName) {
    def toXML: Elem = x match {
      case a: StringIriInterpretedName => (a toXML)
      case _ =>
        <cl:Data xmlns:cl={ SCL.URI_XCL2 }>{ Base64.encodeBase64String(SerializationUtils.serialize(x symbol)) }</cl:Data>
    }
  }

  implicit class FunctionalTermXMLHelper(x: FunctionalTerm) {
    def toXML: Elem =
      <cl:Apply xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x operator) toXML) }
        { ((x args) toXML) }
      </cl:Apply>
  }

  implicit class TermXMLHelper(x: Term) {
    def toXML: Elem = x match {
      case a: InterpretableName => (a toXML)
      case a: InterpretedName   => (a toXML)
      case a: FunctionalTerm    => (a toXML)
    }
  }

  implicit class StringSequenceMarkerXMLHelper(x: StringSequenceMarker) {
    def toXML: Elem =
      <cl:Marker xmlns:cl={ SCL.URI_XCL2 }>{ StringEscapeUtils.escapeXml11((x symbol) toString) }</cl:Marker>
  }

  implicit class SequenceMarkerXMLHelper(x: SequenceMarker) {
    def toXML: Elem = x match {
      case a: StringSequenceMarker =>
        (a toXML)
      case _ =>
        <cl:Marker xmlns:cl={ SCL.URI_XCL2 }>
        { Base64.encodeBase64String(SerializationUtils.serialize(x symbol)) }
        </cl:Marker>
    }
  }

  implicit class TermOrSequenceMarkerXMLHelper(x: TermOrSequenceMarker) {
    def toXML: Elem = x match {
      case a: Term           => (a toXML)
      case a: SequenceMarker => (a toXML)
    }
  }

  implicit class CommentSetXMLHelper(x: CommentSet) {
    def toXML: NodeSeq = {
      val y = (x.toSeq)
      for (comment <- y) yield (comment toXML)
    }
  }

  implicit class TermSetXMLHelper(x: TermSet) {
    def toXML: NodeSeq = {
      val y = (x.toSeq)
      for (term <- y) yield (term toXML)
    }
  }

  implicit class BindingSetXMLHelper(x: BindingSet) {
    def toXML: NodeSeq = {
      val y = (x.toSeq)
      for (name <- y) yield (name toXML)
    }
  }

  implicit class SeqBindingSetXMLHelper(x: SeqBindingSet) {
    def toXML: NodeSeq = {
      val y = (x.toSeq)
      for (seq <- y) yield (seq toXML)
    }
  }

  implicit class TermSequenceXMLHelper(x: TermSequence) {
    def toXML: NodeSeq = {
      for (tosm <- x) yield (tosm toXML)
    }
  }

  implicit class TermOrSequenceMarkerSetXMLHelper(x: TOSMSet) {
    def toXML: NodeSeq = {
      val y = (x.toSeq)
      for (tosm <- y) yield (tosm toXML)
    }
  }

  implicit class AtomicSentenceXMLHelper(x: AtomicSentence) {
    def toXML: Elem =
      <cl:Atom xmlns:cl={ SCL.URI_XCL2 }>
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
        { if (((x terms) size) == 1)((x terms) toXML) }
      </cl:Equal>
    }
  }

  implicit class BiconditionalXMLHelper(x: Biconditional) {
    def toXML: Elem = {
      <cl:Biconditional xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x sentences) toXML) }
        { if (((x sentences) size) == 1)((x sentences) toXML) }
      </cl:Biconditional>
    }
  }

  implicit class ConjunctionXMLHelper(x: Conjunction) {
    def toXML: Elem = {
      <cl:And xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x conjuncts) toXML) }
      </cl:And>
    }
  }

  implicit class DisjunctionXMLHelper(x: Disjunction) {
    def toXML: Elem = {
      <cl:Or xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x disjuncts) toXML) }
      </cl:Or>
    }
  }

  implicit class NegationXMLHelper(x: Negation) {
    def toXML: Elem = {
      <cl:Not xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x body) toXML) }
      </cl:Not>
    }
  }

  implicit class ImplicationXMLHelper(x: Implication) {
    def toXML: Elem = {
      <cl:Implies xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x antecedent) toXML) }
        { ((x consequent) toXML) }
      </cl:Implies>
    }
  }

  implicit class ExistentialXMLHelper(x: Existential) {
    def toXML: Elem = {
      <cl:Exists xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x bindings) toXML) }
        { ((x body) toXML) }
      </cl:Exists>
    }
  }

  implicit class UniversalXMLHelper(x: Universal) {
    def toXML: Elem = {
      <cl:Forall xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x bindings) toXML) }
        { ((x body) toXML) }
      </cl:Forall>
    }
  }

  implicit class SimpleSentenceXMLHelper(x: SimpleSentence) {
    def toXML: Elem = x match {
      case a: AtomicSentence => (a toXML)
      case a: Equation       => (a toXML)
    }
  }

  implicit class BooleanSentenceXMLHelper(x: BooleanSentence) {
    def toXML: Elem = x match {
      case a: Negation      => (a toXML)
      case a: Conjunction   => (a toXML)
      case a: Disjunction   => (a toXML)
      case a: Biconditional => (a toXML)
      case a: Implication   => (a toXML)
    }
  }

  implicit class QuantifiedSentenceXMLHelper(x: QuantifiedSentence) {
    def toXML: Elem = x match {
      case a: Existential => (a toXML)
      case a: Universal   => (a toXML)
    }
  }

  implicit class SentenceXMLHelper(x: Sentence) {
    def toXML: Elem = x match {
      case a: SimpleSentence     => (a toXML)
      case a: BooleanSentence    => (a toXML)
      case a: QuantifiedSentence => (a toXML)
    }
  }

  implicit class SentenceSetXMLHelper(x: SentenceSet) {
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
      <cl:In xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x terms) toXML) }
      </cl:In>
    }
  }

  implicit class OutDiscourseStatementXMLHelper(x: OutDiscourseStatement) {
    def toXML: Elem = {
      <cl:Out xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x terms) toXML) }
      </cl:Out>
    }
  }

  implicit class DiscourseStatementXMLHelper(x: DiscourseStatement) {
    def toXML: Elem = x match {
      case a: InDiscourseStatement  => (a toXML)
      case a: OutDiscourseStatement => (a toXML)
    }
  }

  implicit class StatementXMLHelper(x: Statement) {
    def toXML: Elem = x match {
      case a: Titling            => (a toXML)
      case a: Schema             => (a toXML)
      case a: DiscourseStatement => (a toXML)
    }
  }

  implicit class TextConstructionXMLHelper(x: TextConstruction) {
    def toXML: Elem = {
      <cl:Construct xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x expressions) toXML) }
      </cl:Construct>
    }
  }

  implicit class DomainRestrictionXMLHelper(x: DomainRestriction) {
    def toXML: Elem = {
      <cl:Restrict xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x domain) toXML) }
        { ((x body) toXML) }
      </cl:Restrict>
    }
  }

  implicit class ImportationXMLHelper(x: Importation) {
    def toXML: Elem = {
      <cl:Import xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x title) toXML) }
      </cl:Import>
    }
  }

  implicit class TextXMLHelper(x: Text) {
    def toXML: Elem = x match {
      case a: TextConstruction  => (a toXML)
      case a: DomainRestriction => (a toXML)
      case a: Importation       => (a toXML)
    }
  }

  implicit class BasicExpressionXMLHelper(x: BasicExpression) {
    def toXML: Elem = x match {
      case a: Text      => (a toXML)
      case a: Statement => (a toXML)
      case a: Sentence  => (a toXML)
    }
  }

  implicit class BasicExpressionSetXMLHelper(x: BasicExpressionSet) {
    def toXML: NodeSeq = {
      val y = (x.toSeq)
      for (basic <- y) yield (basic toXML)
    }
  }
}
