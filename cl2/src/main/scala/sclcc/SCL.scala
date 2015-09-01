package sclcc

import scala.language.postfixOps
import com.typesafe.scalalogging._
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
sealed trait ExpressionLike extends BasicKnowledgeExpressionLike with LazyLogging {
  def isBasic() = true
  def language() = SCL.LANG
  def level() = EXPRESSION
}
sealed trait Fragment extends ExpressionLike
sealed trait TermOrSequenceMarker extends Fragment
sealed trait Term extends TermOrSequenceMarker
sealed trait Name extends Term

case class Comment(data: Object) extends Fragment
case class InterpretableName(symbol: Object) extends Name
class StringInterpretableName(symbol: String) extends InterpretableName(symbol)
case class InterpretedName(symbol: Object, datatype: Object) extends Name
class StringIriInterpretedName(symbol: String, datatype: String) extends InterpretedName(symbol, datatype)
case class SequenceMarker(symbol: Object) extends TermOrSequenceMarker
class StringSequenceMarker(symbol: String) extends SequenceMarker(symbol)
case class FunctionalTerm(comments: Set[Comment], operator: Term,
  args: List[TermOrSequenceMarker]) extends Term

sealed trait Expression extends ExpressionLike
sealed trait Sentence extends Expression
sealed trait SimpleSentence extends Sentence
sealed trait BooleanSentence extends Sentence
sealed trait QuantifiedSentence extends BooleanSentence

sealed trait Statement extends Expression
sealed trait DiscourseStatement extends Statement
sealed trait Text extends Expression

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
  body: Sentence) extends QuantifiedSentence

case class TextConstruction(comments: Set[Comment],
  expressions: Set[Expression]) extends Text
case class DomainRestriction(comments: Set[Comment], domain: Term,
  body: Text) extends Text
case class Importation(comments: Set[Comment], title: Name) extends Text
