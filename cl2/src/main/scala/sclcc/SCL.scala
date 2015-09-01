package sclcc

import scala.language.postfixOps
import com.typesafe.scalalogging._
import api4kbj.KnowledgeExpressionLike
import api4kbj.BasicKnowledgeExpressionLike
import api4kba.AbstractKRRLanguage
import api4kba.AbstractKRRLogic
import api4kbj.KnowledgeSourceLevel.EXPRESSION

/**
 * @author taraathan
 */
object SCL {
  val COMPLETE_CL_LOGIC = AbstractKRRLogic.logic("Common Logic 2")
  val LANG = AbstractKRRLanguage.language(
    "Common Logic 2", classOf[ExpressionLike], COMPLETE_CL_LOGIC)
}
sealed trait ExpressionLike extends KnowledgeExpressionLike with LazyLogging
sealed trait BasicExpressionLike extends BasicKnowledgeExpressionLike with LazyLogging {
  def language() = SCL.LANG
}
sealed trait Fragment extends BasicExpressionLike
sealed trait TermOrSequenceMarker extends Fragment
sealed trait Term extends TermOrSequenceMarker
sealed trait NameOrSequenceMarker extends TermOrSequenceMarker {
  def symbol: Object
}
object NameOrSequenceMarker {
  def unapply(e: NameOrSequenceMarker): Option[(Object)] =
    Option(e) map { e =>
      (e.symbol)
    }
}
sealed trait Name extends NameOrSequenceMarker with Term {
  def symbol: Object
}
object Name {
  def unapply(e: Name): Option[(Object)] =
    Option(e) map { e =>
      (e.symbol)
    }
}
trait Commentable {
  def comments: Set[Comment]
}
object Commentable {
  def unapply(e: Commentable): Option[(Set[Comment])] =
    Option(e) map { e =>
      (e.comments)
    }
}

case class Comment(data: Object) extends Fragment
case class InterpretableName(symbol: Object) extends Name
class StringInterpretableName(symbol: String) extends InterpretableName(symbol)
case class InterpretedName(symbol: Object, datatype: Object) extends Name
class StringIriInterpretedName(symbol: String, datatype: String) extends InterpretedName(symbol, datatype)
case class SequenceMarker(symbol: Object) extends TermOrSequenceMarker
class StringSequenceMarker(symbol: String) extends SequenceMarker(symbol)
case class FunctionalTerm(comments: Set[Comment], operator: Term,
  args: List[TermOrSequenceMarker]) extends Term with Commentable

sealed trait Expression extends ExpressionLike
sealed trait BasicExpression extends BasicExpressionLike with Expression with Commentable {
  def comments: Set[Comment]
}
sealed trait Sentence extends BasicExpression {
  def comments: Set[Comment]
}
object Sentence {
  def unapply(e: Sentence): Option[(Set[Comment])] =
    Option(e) map { e =>
      (e.comments)
    }
}
sealed trait SimpleSentence extends Sentence {
  def comments: Set[Comment]
}
object SimpleSentence {
  def unapply(e: SimpleSentence): Option[(Set[Comment])] =
    Option(e) map { e =>
      (e.comments)
    }
}
sealed trait BooleanSentence extends Sentence {
  def comments: Set[Comment]
}
object BooleanSentence {
  def unapply(e: BooleanSentence): Option[(Set[Comment])] =
    Option(e) map { e =>
      (e.comments)
    }
}
sealed trait QuantifiedSentence extends BooleanSentence {
  def comments: Set[Comment]
  def bindings: Set[InterpretableName]
  def body: Sentence
}
object QuantifiedSentence {
  def unapply(e: QuantifiedSentence): Option[(Set[Comment], Set[InterpretableName], Sentence)] =
    Option(e) map { e =>
      (e.comments, e.bindings, e.body)
    }
}

sealed trait Statement extends BasicExpression {
  def comments: Set[Comment]
}
object Statement {
  def unapply(e: Statement): Option[(Set[Comment])] =
    Option(e) map { e =>
      (e.comments)
    }
}
sealed trait DiscourseStatement extends Statement {
  def comments: Set[Comment]
  def terms: Set[Term]
}
object DiscourseStatement {
  def unapply(e: DiscourseStatement): Option[(Set[Comment], Set[Term])] =
    Option(e) map { e =>
      (e.comments, e.terms)
    }
}
sealed trait Text extends BasicExpression {
  def comments: Set[Comment]
}
object Text {
  def unapply(e: Text): Option[(Set[Comment])] =
    Option(e) map { e =>
      (e.comments)
    }
}

case class AtomicSentence(comments: Set[Comment], operator: Term,
  args: List[TermOrSequenceMarker]) extends SimpleSentence
case class Equation(comments: Set[Comment], terms: Set[Term]) extends SimpleSentence {
  require(((terms.size < 1) || (terms.size > 2)), "Terms size must be 1 or 2")
}

case class Biconditional(comments: Set[Comment], sentences: Set[Sentence]) extends BooleanSentence {
  require(((sentences.size < 1) || (sentences.size > 2)), "Sentences size must be 1 or 2")
}

case class Implication(comments: Set[Comment], antecedent: Sentence, consequent: Sentence) extends BooleanSentence
case class Conjunction(comments: Set[Comment], conjuncts: Set[Sentence]) extends BooleanSentence
case class Disjunction(comments: Set[Comment], disjuncts: Set[Sentence]) extends BooleanSentence
case class Existential(comments: Set[Comment], bindings: Set[InterpretableName],
  body: Sentence) extends QuantifiedSentence
case class Universal(comments: Set[Comment], bindings: Set[InterpretableName],
  body: Sentence) extends QuantifiedSentence

case class InDiscourseStatement(comments: Set[Comment], terms: Set[Term]) extends DiscourseStatement
case class OutDiscourseStatement(comments: Set[Comment], terms: Set[Term]) extends DiscourseStatement
case class Titling(comments: Set[Comment], title: Name, body: Text) extends Statement
case class Schema(comments: Set[Comment], seqbindings: Set[SequenceMarker],
  body: Sentence) extends Statement

case class TextConstruction(comments: Set[Comment],
  expressions: Set[Expression]) extends Text
case class DomainRestriction(comments: Set[Comment], domain: Term,
  body: Text) extends Text
case class Importation(comments: Set[Comment], title: Name) extends Text
