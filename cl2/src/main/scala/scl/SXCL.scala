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
import scala.xml.Elem
import scala.xml.NodeSeq
import org.apache.commons.codec.binary.Base64
import org.apache.commons.lang3.SerializationUtils

/**
 * @author taraathan
 */
object XMLHelper {
  implicit class StringCommentXMLHelper(x: StringComment) {
    def toXML: Elem = <cl:Comment xmlns:cl={ SCL.URI_XCL2 }>{ StringEscapeUtils escapeXml11 ((x data) toString) }</cl:Comment>
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
    def toXML: Elem = <cl:Name xmlns:cl={ SCL.URI_XCL2 }>{ StringEscapeUtils.escapeXml11((x symbol) toString) }</cl:Name>
  }
  implicit class StringIriInterpretedNameXMLHelper(x: StringIriInterpretedName) {
    def toXML: Elem = <cl:Data xmlns:cl={ SCL.URI_XCL2 } datatype={ StringEscapeUtils escapeXml11 ((x datatype) toString) }>
                        { StringEscapeUtils escapeXml11 ((x symbol) toString) }
                      </cl:Data>
  }
  implicit class FunctionalTermXMLHelper(x: FunctionalTerm) {
    def toXML: Elem = <cl:Apply xmlns:cl={ SCL.URI_XCL2 }>
                        { ((x comments) toXML) }
                        { ((x operator) toXML) }
                        { ((x args) toXML) }
                      </cl:Apply>
  }
  implicit class TermXMLHelper(x: Term) {
    def toXML: Elem = x match {
      case a: StringInterpretableName => (a toXML)
      case a: StringIriInterpretedName => (a toXML)
      case a: FunctionalTerm => (a toXML)
    }
  }
  implicit class StringSequenceMarkerXMLHelper(x: StringSequenceMarker) {
    def toXML: Elem = <cl:Marker xmlns:cl={ SCL.URI_XCL2 }>{ StringEscapeUtils.escapeXml11((x symbol) toString) }</cl:Marker>
  }
  implicit class TermOrSequenceMarkerXMLHelper(x: TermOrSequenceMarker) {
    def toXML: Elem = x match {
      case a: StringInterpretableName => (a toXML)
      case a: StringIriInterpretedName => (a toXML)
      case a: StringSequenceMarker => (a toXML)
      case a: FunctionalTerm => (a toXML)
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
  implicit class TermSequenceXMLHelper(x: List[TermOrSequenceMarker]) {
    def toXML: NodeSeq = {
      val y = (x.toSeq)
      for (tosm <- x) yield (tosm toXML)
    }
  }
  implicit class AtomicSentenceXMLHelper(x: AtomicSentence) {
    def toXML: Elem = <cl:Atom xmlns:cl={ SCL.URI_XCL2 }>
                        { ((x comments) toXML) }
                        { ((x operator) toXML) }
                        { ((x args) toXML) }
                      </cl:Atom>
  }
  implicit class EautionXMLHelper(x: Equation) {
    def toXML: Elem = {
      <cl:Equal xmlns:cl={ SCL.URI_XCL2 }>
        { ((x comments) toXML) }
        { ((x terms) toXML) }
        { if (((x terms) size) == 1) ((x terms) toXML) }
      </cl:Equal>
    }
  }

}
