package scl

import api4kba.AbstractKRRLanguage
import api4kba.AbstractKRRLogic
import api4kbj.KnowledgeExpressionLike
import api4kbj.BasicKnowledgeExpressionLike
import com.typesafe.scalalogging._
import scala.language.{ postfixOps, existentials }
import XMLHelper._

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
  def language(): AbstractKRRLanguage = SCL.LANG
}

sealed trait BasicExpressionLike extends BasicKnowledgeExpressionLike
  with ExpressionLike

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
  def comments: CommentSet
}

object Commentable {
  def unapply(e: Commentable): Option[(CommentSet)] =
    Option(e) map { e =>
      (e.comments)
    }
}

abstract case class Comment(data: java.io.Serializable) extends Fragment

class StringComment(data: String) extends Comment(data)

abstract case class InterpretableName(symbol: java.io.Serializable) extends Name

class StringInterpretableName(symbol: String) extends InterpretableName(symbol)

abstract case class InterpretedName(
  symbol: java.io.Serializable,
  datatype: java.io.Serializable) extends Name

class StringIriInterpretedName(symbol: String, datatype: String)
    extends InterpretedName(symbol, datatype) {
  def this(s: String) = this(s, URI_XSD_STRING)
}

abstract case class SequenceMarker(symbol: java.io.Serializable)
  extends NameOrSequenceMarker

class StringSequenceMarker(symbol: String) extends SequenceMarker(symbol)

case class FunctionalTerm(
  comments: CommentSet,
  operator: Term,
  args: TermSequence) extends Term with Commentable

sealed trait Expression extends ExpressionLike

sealed trait BasicExpression extends BasicExpressionLike
    with Expression with Commentable {
  def comments: CommentSet
}

object BasicExpression {
  def unapply(e: BasicExpression): Option[(CommentSet)] =
    Option(e) map { e =>
      (e.comments)
    }
}

sealed trait SentenceOrSchema extends BasicExpression {
  def comments: CommentSet
}

object SentenceOrSchema {
  def unapply(e: SentenceOrSchema): Option[(CommentSet)] =
    Option(e) map { e =>
      (e.comments)
    }
}

sealed trait Sentence extends SentenceOrSchema {
  def comments: CommentSet
}

object Sentence {
  def unapply(e: Sentence): Option[(CommentSet)] =
    Option(e) map { e =>
      (e.comments)
    }
}

sealed trait SimpleSentence extends Sentence {
  def comments: CommentSet
}

object SimpleSentence {
  def unapply(e: SimpleSentence): Option[(CommentSet)] =
    Option(e) map { e =>
      (e.comments)
    }
}

sealed trait BooleanSentence extends Sentence {
  def comments: CommentSet
}

object BooleanSentence {
  def unapply(e: BooleanSentence): Option[(CommentSet)] =
    Option(e) map { e =>
      (e.comments)
    }
}

sealed trait QuantifiedSentence extends Sentence {
  def comments: CommentSet
  def bindings: BindingSet
  def body: Sentence
}

object QuantifiedSentence {
  def unapply(e: QuantifiedSentence): Option[(CommentSet, BindingSet, Sentence)] =
    Option(e) map { e =>
      (e.comments, e.bindings, e.body)
    }
}

sealed trait Statement extends BasicExpression {
  def comments: CommentSet
}

object Statement {
  def unapply(e: Statement): Option[(CommentSet)] =
    Option(e) map { e =>
      (e.comments)
    }
}

sealed trait DiscourseStatement extends Statement {
  def comments: CommentSet
  def terms: TOSMSet
}

object DiscourseStatement {
  def unapply(e: DiscourseStatement): Option[(CommentSet, TOSMSet)] =
    Option(e) map { e =>
      (e.comments, e.terms)
    }
}

sealed trait Text extends BasicExpression {
  def comments: CommentSet
}

object Text {
  def unapply(e: Text): Option[(CommentSet)] =
    Option(e) map { e =>
      (e.comments)
    }
}

case class AtomicSentence(
  comments: CommentSet,
  operator: Term,
  args: TermSequence) extends SimpleSentence

case class Equation(
    comments: CommentSet,
    terms: TermSet) extends SimpleSentence {

  require(!((terms.size < 1) || (terms.size > 2)), "Terms size must be 1 or 2")

  def this(comments: CommentSet, left: Term, right: Term) {
    this(comments, Set(left, right))
  }
}

case class Biconditional(
    comments: CommentSet,
    sentences: SentenceSet) extends BooleanSentence {

  require(!((sentences.size < 1) || (sentences.size > 2)),
    "Sentences size must be 1 or 2")

  def this(comments: CommentSet, left: Sentence, right: Sentence) {
    this(comments, Set(left, right))
  }

}

case class Implication(
  comments: CommentSet,
  antecedent: Sentence,
  consequent: Sentence) extends BooleanSentence

case class Conjunction(
  comments: CommentSet,
  conjuncts: SentenceSet) extends BooleanSentence

case class Disjunction(
  comments: CommentSet,
  disjuncts: SentenceSet) extends BooleanSentence

case class Negation(
  comments: CommentSet,
  body: Sentence) extends BooleanSentence

case class Existential(
  comments: CommentSet,
  bindings: BindingSet,
  body: Sentence) extends QuantifiedSentence

case class Universal(
  comments: CommentSet,
  bindings: BindingSet,
  body: Sentence) extends QuantifiedSentence

case class InDiscourseStatement(
  comments: CommentSet,
  terms: TOSMSet) extends DiscourseStatement

case class OutDiscourseStatement(
  comments: CommentSet,
  terms: TOSMSet) extends DiscourseStatement

case class Titling(
  comments: CommentSet,
  title: Name,
  body: Text) extends Statement

case class Schema(
  comments: CommentSet,
  seqbindings: SeqBindingSet,
  body: SentenceOrSchema) extends Statement with SentenceOrSchema

case class TextConstruction(
  comments: CommentSet,
  expressions: BasicExpressionSet) extends Text

case class DomainRestriction(
  comments: CommentSet,
  domain: Term,
  body: Text) extends Text

case class Importation(comments: CommentSet, title: Name) extends Text
