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

/**
 * @author taraathan
 */
object SCL {
  val COMPLETE_CL_LOGIC = AbstractKRRLogic.logic("Common Logic 2")
  val LANG = AbstractKRRLanguage.language(
    "Common Logic 2", classOf[BasicExpressionLike], COMPLETE_CL_LOGIC)
  val URI_XCL2 = "http://purl.org/xcl/2.0/"
}
sealed trait ExpressionLike extends KnowledgeExpressionLike with LazyLogging {
  def language() = SCL.LANG
}
sealed trait BasicExpressionLike extends BasicKnowledgeExpressionLike with ExpressionLike
sealed trait Fragment extends BasicExpressionLike
sealed trait TermOrSequenceMarker extends Fragment
sealed trait Term extends TermOrSequenceMarker
sealed trait NameOrSequenceMarker extends TermOrSequenceMarker {
  def symbol: java.io.Serializable
}
object NameOrSequenceMarker {
  def unapply(e: NameOrSequenceMarker): Option[(_ <: java.io.Serializable)] =
    Option(e) map { e =>
      (e.symbol)
    }
}
sealed trait Name extends NameOrSequenceMarker with Term {
  def symbol: java.io.Serializable
}
object Name {
  def unapply(e: Name): Option[(_ <: java.io.Serializable)] =
    Option(e) map { e =>
      (e.symbol)
    }
}
trait Commentable {
  def comments: Set[_ <: Comment]
}
object Commentable {
  def unapply(e: Commentable): Option[(Set[_ <: Comment])] =
    Option(e) map { e =>
      (e.comments)
    }
}

abstract case class Comment(data: java.io.Serializable) extends Fragment
class StringComment(data: String) extends Comment(data)
abstract case class InterpretableName(symbol: java.io.Serializable) extends Name
class StringInterpretableName(symbol: String) extends InterpretableName(symbol)
abstract case class InterpretedName(symbol: java.io.Serializable, datatype: java.io.Serializable) extends Name
class StringIriInterpretedName(symbol: String, datatype: String) extends InterpretedName(symbol, datatype) {
  def this(s: String) = this(s, URI_XSD_STRING)
}
abstract case class SequenceMarker(symbol: java.io.Serializable) extends TermOrSequenceMarker
class StringSequenceMarker(symbol: String) extends SequenceMarker(symbol)
case class FunctionalTerm(
  comments: Set[_ <: Comment],
  operator: Term,
  args: List[TermOrSequenceMarker]) extends Term with Commentable

sealed trait Expression extends ExpressionLike
sealed trait BasicExpression extends BasicExpressionLike with Expression with Commentable {
  def comments: Set[_ <: Comment]
}
object BasicExpression {
  def unapply(e: BasicExpression): Option[(Set[_ <: Comment])] =
    Option(e) map { e =>
      (e.comments)
    }
}
sealed trait SentenceOrSchema extends BasicExpression {
  def comments: Set[_ <: Comment]
}
object SentenceOrSchema {
  def unapply(e: SentenceOrSchema): Option[(Set[_ <: Comment])] =
    Option(e) map { e =>
      (e.comments)
    }
}
sealed trait Sentence extends SentenceOrSchema {
  def comments: Set[_ <: Comment]
}
object Sentence {
  def unapply(e: Sentence): Option[(Set[_ <: Comment])] =
    Option(e) map { e =>
      (e.comments)
    }
}
sealed trait SimpleSentence extends Sentence {
  def comments: Set[_ <: Comment]
}
object SimpleSentence {
  def unapply(e: SimpleSentence): Option[(Set[_ <: Comment])] =
    Option(e) map { e =>
      (e.comments)
    }
}
sealed trait BooleanSentence extends Sentence {
  def comments: Set[_ <: Comment]
}
object BooleanSentence {
  def unapply(e: BooleanSentence): Option[(Set[_ <: Comment])] =
    Option(e) map { e =>
      (e.comments)
    }
}
sealed trait QuantifiedSentence extends BooleanSentence {
  def comments: Set[_ <: Comment]
  def bindings: Set[_ <: InterpretableName]
  def body: Sentence
}
object QuantifiedSentence {
  def unapply(e: QuantifiedSentence): Option[(Set[_ <: Comment], Set[_ <: InterpretableName], Sentence)] =
    Option(e) map { e =>
      (e.comments, e.bindings, e.body)
    }
}

sealed trait Statement extends BasicExpression {
  def comments: Set[_ <: Comment]
}
object Statement {
  def unapply(e: Statement): Option[(Set[_ <: Comment])] =
    Option(e) map { e =>
      (e.comments)
    }
}
sealed trait DiscourseStatement extends Statement {
  def comments: Set[_ <: Comment]
  def terms: Set[_ <: TermOrSequenceMarker]
}
object DiscourseStatement {
  def unapply(e: DiscourseStatement): Option[(Set[_ <: Comment], Set[_ <: TermOrSequenceMarker])] =
    Option(e) map { e =>
      (e.comments, e.terms)
    }
}
sealed trait Text extends BasicExpression {
  def comments: Set[_ <: Comment]
}
object Text {
  def unapply(e: Text): Option[(Set[_ <: Comment])] =
    Option(e) map { e =>
      (e.comments)
    }
}

case class AtomicSentence(
  comments: Set[_ <: Comment],
  operator: Term,
  args: List[TermOrSequenceMarker]) extends SimpleSentence

case class Equation(comments: Set[_ <: Comment], terms: Set[_ <: Term]) extends SimpleSentence {
  require(!((terms.size < 1) || (terms.size > 2)), "Terms size must be 1 or 2")

  def this(comments: Set[_ <: Comment], left: Term, right: Term) {
    this(comments, Set(left, right))
  }
}

case class Biconditional(comments: Set[_ <: Comment], sentences: Set[_ <: Sentence]) extends BooleanSentence {
  require(!((sentences.size < 1) || (sentences.size > 2)), "Sentences size must be 1 or 2")

  def this(comments: Set[_ <: Comment], left: Sentence, right: Sentence) {
    this(comments, Set(left, right))
  }

}

case class Implication(comments: Set[_ <: Comment], antecedent: Sentence, consequent: Sentence) extends BooleanSentence
case class Conjunction(comments: Set[_ <: Comment], conjuncts: Set[_ <: Sentence]) extends BooleanSentence
case class Disjunction(comments: Set[_ <: Comment], disjuncts: Set[_ <: Sentence]) extends BooleanSentence
case class Negation(comments: Set[_ <: Comment], body: Sentence) extends BooleanSentence
case class Existential(
  comments: Set[_ <: Comment],
  bindings: Set[_ <: InterpretableName],
  body: Sentence) extends QuantifiedSentence
case class Universal(
  comments: Set[_ <: Comment],
  bindings: Set[_ <: InterpretableName],
  body: Sentence) extends QuantifiedSentence

case class InDiscourseStatement(comments: Set[_ <: Comment], terms: Set[_ <: TermOrSequenceMarker]) extends DiscourseStatement
case class OutDiscourseStatement(comments: Set[_ <: Comment], terms: Set[_ <: TermOrSequenceMarker]) extends DiscourseStatement
case class Titling(comments: Set[_ <: Comment], title: Name, body: Text) extends Statement
case class Schema(
  comments: Set[_ <: Comment],
  seqbindings: Set[_ <: SequenceMarker],
  body: SentenceOrSchema) extends Statement with SentenceOrSchema

case class TextConstruction(
  comments: Set[_ <: Comment],
  expressions: Set[_ <: BasicExpression]) extends Text
case class DomainRestriction(
  comments: Set[_ <: Comment],
  domain: Term,
  body: Text) extends Text
case class Importation(comments: Set[_ <: Comment], title: Name) extends Text
