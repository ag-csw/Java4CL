package scl

import com.typesafe.scalalogging._
import org.apache.commons.codec.binary.Base64
import org.apache.commons.lang3.{ SerializationUtils, StringEscapeUtils }
import scala.language.{ postfixOps, existentials }
import scala.util.{ Try, Success, Failure }
import scala.xml.{ Elem, Node, NodeSeq }
import SCL._
import SXCLParser._

/**
 * @author taraathan
 */
trait SXCLParser[T] {
  def fromXML(x: Elem): Try[T]

  def comments(x: Elem): Try[CommentSet] =
    Try(
      x match { case e: Elem if (isXCLCommentable(e)) => commentSetParse(x) })

  def xmlComments(x: Elem): Try[NodeSeq] =
    Try(
      x match {
        case e: Elem if (isXCLCommentable(e)) =>
          (x child) filter (isXCLComment _)
      })

  def operator(x: Elem): Try[Term] =
    Try(
      x match {
        case e: Elem if (isXCLFunctionalTerm(e) || isXCLAtomicSentence(e)) => {
          val args = termSeqParse(x)
          args head
        }
      })

  def xmlOperator(x: Elem): Try[Elem] =
    Try(
      x match {
        case e: Elem if (isXCLFunctionalTerm(e) || isXCLAtomicSentence(e)) =>
          (x child) filter (isXCLTerm _) head match { case g: Elem => g }
      })
}

object SXCLParser {
  implicit object SXCLParserComment
      extends SXCLParser[Comment] {
    // TODO parse data datatype
    def fromXML(x: Elem): Try[Comment] =
      Try(
        x match {
          case e: Elem if (isXCLComment(e)) =>
            new StringComment(StringEscapeUtils unescapeXml (x text))
        })
  }

  implicit object SXCLParserInterpretableName
      extends SXCLParser[InterpretableName] {
    // TODO parse symbol datatype
    def fromXML(x: Elem): Try[InterpretableName] =
      Try(
        x match {
          case e: Elem if (isXCLInterpretableName(e)) =>
            new StringInterpretableName(StringEscapeUtils unescapeXml (x text))
        })
  }

  implicit object SXCLParserInterpretedName
      extends SXCLParser[InterpretedName] {
    // TODO parse datatype
    def fromXML(x: Elem): Try[InterpretedName] =
      Try(
        x match {
          case e: Elem if (isXCLInterpretedName(e)) =>
            new StringIriInterpretedName(StringEscapeUtils unescapeXml (x text))
        })
  }

  implicit object SXCLParserSequenceMarker
      extends SXCLParser[SequenceMarker] {
    // TODO parse symbol datatype
    def fromXML(x: Elem): Try[SequenceMarker] =
      Try(
        x match {
          case e: Elem if (isXCLSequenceMarker(e)) =>
            new StringSequenceMarker(StringEscapeUtils unescapeXml (x text))
        })
  }

  implicit object SXCLParserFunctionalTerm
      extends SXCLParser[FunctionalTerm] {
    def fromXML(x: Elem): Try[FunctionalTerm] = Try({
      val comments: CommentSet = commentSetParse(x)
      val argsAll: TermSequence = tosmSeqParse(x)
      val operator: Term = (argsAll head) match {
        case t: Term => t
      }
      val args: TermSequence = (argsAll tail)
      FunctionalTerm(comments, operator, args)
    })
  }

  implicit object SXCLParserName
      extends SXCLParser[Name] {
    def fromXML(x: Elem): Try[Name] =
      Try(
        x match {
          case e: Elem if (isXCLInterpretableName(e)) =>
            SXCLParserInterpretableName.fromXML(e) get
          case e: Elem if (isXCLInterpretedName(e)) =>
            SXCLParserInterpretedName.fromXML(e) get
        })
  }

  implicit object SXCLParserTerm
      extends SXCLParser[Term] {
    def fromXML(x: Elem): Try[Term] =
      Try(
        x match {
          case e: Elem if (isXCLName(e)) =>
            SXCLParserName.fromXML(e) get
          case e: Elem if (isXCLFunctionalTerm(e)) =>
            SXCLParserFunctionalTerm.fromXML(e) get
        })
  }

  implicit object SXCLParserTOSM
      extends SXCLParser[TermOrSequenceMarker] {
    def fromXML(x: Elem): Try[TermOrSequenceMarker] = Try(x match {
      case e: Elem if (isXCLTerm(e)) =>
        SXCLParserTerm.fromXML(e) get
      case e: Elem if (isXCLSequenceMarker(e)) =>
        SXCLParserSequenceMarker.fromXML(e) get
    })
  }

  implicit object SXCLParserNOSM
      extends SXCLParser[NameOrSequenceMarker] {
    def fromXML(x: Elem): Try[NameOrSequenceMarker] = Try(x match {
      case e: Elem if (isXCLName(e)) =>
        SXCLParserName.fromXML(e) get
      case e: Elem if (isXCLSequenceMarker(e)) =>
        SXCLParserSequenceMarker.fromXML(e) get
    })
  }

  implicit object SXCLParserAtomicSentence
      extends SXCLParser[AtomicSentence] {
    def fromXML(x: Elem): Try[AtomicSentence] = Try({
      x match {
        case e: Elem if (isXCLAtomicSentence(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val argsAll: TermSequence = tosmSeqParse(x)
          val operator: Term = (argsAll head) match { case t: Term => t }
          val args: TermSequence = argsAll.tail
          AtomicSentence(comments, operator, args)
        }
      }
    })
  }

  implicit object SXCLParserEquation
      extends SXCLParser[Equation] {
    def fromXML(x: Elem): Try[Equation] = Try({
      x match {
        case e: Elem if (isXCLEquation(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val args: TermSet = termSetParse(x)
          Equation(comments, args)
        }
      }
    })
  }

  implicit object SXCLParserBiconditional
      extends SXCLParser[Biconditional] {
    def fromXML(x: Elem): Try[Biconditional] = Try({
      x match {
        case e: Elem if (isXCLBiconditional(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val args: SentenceSet = sentenceSetParse(x)
          Biconditional(comments, args)
        }
      }
    })
  }

  implicit object SXCLParserImplication
      extends SXCLParser[Implication] {
    def fromXML(x: Elem): Try[Implication] = Try({
      x match {
        case e: Elem if (isXCLImplication(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val args: List[Sentence] = sentenceSeqParse(x)
          val antecedent: Sentence = (args head)
          val consequent: Sentence = ((args tail) head)
          Implication(comments, antecedent, consequent)
        }
      }
    })
  }

  implicit object SXCLParserConjunction
      extends SXCLParser[Conjunction] {
    def fromXML(x: Elem): Try[Conjunction] = Try({
      x match {
        case e: Elem if (isXCLConjunction(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val args: SentenceSet = sentenceSetParse(x)
          Conjunction(comments, args)
        }
      }
    })
  }

  implicit object SXCLParserDisjunction
      extends SXCLParser[Disjunction] {
    def fromXML(x: Elem): Try[Disjunction] = Try({
      x match {
        case e: Elem if (isXCLDisjunction(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val args: SentenceSet = sentenceSetParse(x)
          Disjunction(comments, args)
        }
      }
    })
  }

  implicit object SXCLParserNegation
      extends SXCLParser[Negation] {
    def fromXML(x: Elem): Try[Negation] = Try({
      x match {
        case e: Elem if (isXCLNegation(e)) =>
          val comments: CommentSet = commentSetParse(x)
          val args: List[Sentence] = sentenceSeqParse(x)
          val body: Sentence = (args head)
          Negation(comments, body)
      }
    })
  }

  implicit object SXCLParserExistential
      extends SXCLParser[Existential] {
    def fromXML(x: Elem): Try[Existential] = Try({
      x match {
        case e: Elem if (isXCLExistential(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val bindings: BindingSet = bindingSetParse(x)
          val args: List[Sentence] = sentenceSeqParse(x)
          val body: Sentence = (args head)
          Existential(comments, bindings, body)
        }
      }
    })
  }

  implicit object SXCLParserUniversal
      extends SXCLParser[Universal] {
    def fromXML(x: Elem): Try[Universal] = Try({
      x match {
        case e: Elem if (isXCLUniversal(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val bindings: BindingSet = bindingSetParse(x)
          val args: List[Sentence] = sentenceSeqParse(x)
          val body: Sentence = (args head)
          Universal(comments, bindings, body)
        }
      }
    })
  }

  implicit object SXCLParserSimpleSentence
      extends SXCLParser[SimpleSentence] {
    def fromXML(x: Elem): Try[SimpleSentence] =
      Try(
        x match {
          case e: Elem if (isXCLAtomicSentence(e)) =>
            SXCLParserAtomicSentence.fromXML(e) get
          case e: Elem if (isXCLEquation(e)) =>
            SXCLParserEquation.fromXML(e) get
        })
  }

  implicit object SXCLParserBooleanSentence
      extends SXCLParser[BooleanSentence] {
    def fromXML(x: Elem): Try[BooleanSentence] =
      Try(
        x match {
          case e: Elem if (isXCLBiconditional(e)) =>
            SXCLParserBiconditional.fromXML(e) get
          case e: Elem if (isXCLImplication(e)) =>
            SXCLParserImplication.fromXML(e) get
          case e: Elem if (isXCLConjunction(e)) =>
            SXCLParserConjunction.fromXML(e) get
          case e: Elem if (isXCLDisjunction(e)) =>
            SXCLParserDisjunction.fromXML(e) get
          case e: Elem if (isXCLNegation(e)) =>
            SXCLParserNegation.fromXML(e) get
        })
  }

  implicit object SXCLParserQuantifiedSentence
      extends SXCLParser[QuantifiedSentence] {
    def fromXML(x: Elem): Try[QuantifiedSentence] =
      Try(
        x match {
          case e: Elem if (isXCLExistential(e)) =>
            SXCLParserExistential.fromXML(e) get
          case e: Elem if (isXCLUniversal(e)) =>
            SXCLParserUniversal.fromXML(e) get
        })
  }

  // Sentence
  implicit object SXCLParserSentence
      extends SXCLParser[Sentence] {
    def fromXML(x: Elem): Try[Sentence] =
      Try(
        x match {
          case e: Elem if (isXCLSimpleSentence(e)) =>
            SXCLParserSimpleSentence.fromXML(e) get
          case e: Elem if (isXCLBooleanSentence(e)) =>
            SXCLParserBooleanSentence.fromXML(e) get
          case e: Elem if (isXCLQuantifiedSentence(e)) =>
            SXCLParserQuantifiedSentence.fromXML(e) get
        })
  }

  // Titling
  implicit object SXCLParserTitling
      extends SXCLParser[Titling] {
    def fromXML(x: Elem): Try[Titling] = Try({
      x match {
        case e: Elem if (isXCLTitling(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val names: List[Name] = nameSeqParse(x)
          val title = names head
          val args: List[Text] = textSeqParse(x)
          val body: Text = (args head)
          Titling(comments, title, body)
        }
      }
    })
  }

  implicit object SXCLParserInDiscourseStatement
      extends SXCLParser[InDiscourseStatement] {
    def fromXML(x: Elem): Try[InDiscourseStatement] = Try({
      x match {
        case e: Elem if (isXCLInDiscourseStatement(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val terms: TOSMSet = tosmSetParse(x)
          InDiscourseStatement(comments, terms)
        }
      }
    })
  }

  implicit object SXCLParserOutDiscourseStatement
      extends SXCLParser[OutDiscourseStatement] {
    def fromXML(x: Elem): Try[OutDiscourseStatement] = Try({
      x match {
        case e: Elem if (isXCLOutDiscourseStatement(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val terms: TOSMSet = tosmSetParse(x)
          OutDiscourseStatement(comments, terms)
        }
      }
    })
  }

  implicit object SXCLParserSchema
      extends SXCLParser[Schema] {
    def fromXML(x: Elem): Try[Schema] = Try({
      x match {
        case e: Elem if (isXCLSchema(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val seqbindings: SeqBindingSet = seqBindingSetParse(x)
          val args: List[SentenceOrSchema] = sentenceOrSchemaSeqParse(x)
          val body: SentenceOrSchema = (args head)
          Schema(comments, seqbindings, body)
        }
      }
    })
  }

  implicit object SXCLParserDiscourseStatement
      extends SXCLParser[DiscourseStatement] {
    def fromXML(x: Elem): Try[DiscourseStatement] =
      Try(
        x match {
          case e: Elem if (isXCLInDiscourseStatement(e)) =>
            SXCLParserInDiscourseStatement.fromXML(e) get
          case e: Elem if (isXCLOutDiscourseStatement(e)) =>
            SXCLParserOutDiscourseStatement.fromXML(e) get
        })
  }

  implicit object SXCLParserStatement
      extends SXCLParser[Statement] {
    def fromXML(x: Elem): Try[Statement] =
      Try(
        x match {
          case e: Elem if (isXCLDiscourseStatement(e)) =>
            SXCLParserDiscourseStatement.fromXML(e) get
          case e: Elem if (isXCLTitling(e)) =>
            SXCLParserTitling.fromXML(e) get
          case e: Elem if (isXCLSchema(e)) =>
            SXCLParserSchema.fromXML(e) get
        })
  }

  implicit object SXCLParserSentenceOrSchema
      extends SXCLParser[SentenceOrSchema] {
    def fromXML(x: Elem): Try[SentenceOrSchema] =
      Try(
        x match {
          case e: Elem if (isXCLSentence(e)) =>
            SXCLParserSentence.fromXML(e) get
          case e: Elem if (isXCLSchema(e)) =>
            SXCLParserSchema.fromXML(e) get
        })
  }

  implicit object SXCLParserTextConstruction
      extends SXCLParser[TextConstruction] {
    def fromXML(x: Elem): Try[TextConstruction] = Try({
      x match {
        case e: Elem if (isXCLTextConstruction(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val texts: BasicExpressionSet = basicExpressionSetParse(x)
          TextConstruction(comments, texts)
        }
      }
    })
  }

  implicit object SXCLParserDomainRestriction
      extends SXCLParser[DomainRestriction] {
    def fromXML(x: Elem): Try[DomainRestriction] = Try({
      x match {
        case e: Elem if (isXCLDomainRestriction(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val terms: List[Term] = termSeqParse(x)
          val domain: Term = (terms head)
          val texts: List[Text] = textSeqParse(x)
          val body: Text = (texts head)
          DomainRestriction(comments, domain, body)
        }
      }
    })
  }

  implicit object SXCLParserImportation
      extends SXCLParser[Importation] {
    def fromXML(x: Elem): Try[Importation] = Try({
      x match {
        case e: Elem if (isXCLImportation(e)) => {
          val comments: CommentSet = commentSetParse(x)
          val names: List[Name] = nameSeqParse(x)
          val title: Name = (names head)
          Importation(comments, title)
        }
      }
    })
  }

  implicit object SXCLParserText
      extends SXCLParser[Text] {
    def fromXML(x: Elem): Try[Text] =
      Try(
        x match {
          case e: Elem if (isXCLTextConstruction(e)) =>
            SXCLParserTextConstruction.fromXML(e) get
          case e: Elem if (isXCLDomainRestriction(e)) =>
            SXCLParserDomainRestriction.fromXML(e) get
          case e: Elem if (isXCLImportation(e)) =>
            SXCLParserImportation.fromXML(e) get
        })
  }

  implicit object SXCLParserBasicExpression
      extends SXCLParser[BasicExpression] {
    def fromXML(x: Elem): Try[BasicExpression] =
      Try(
        x match {
          case e: Elem if (isXCLSentence(e)) =>
            SXCLParserSentence.fromXML(e) get
          case e: Elem if (isXCLStatement(e)) =>
            SXCLParserStatement.fromXML(e) get
          case e: Elem if (isXCLText(e)) =>
            SXCLParserText.fromXML(e) get
        })
  }

  implicit object SXCLParserFragment
      extends SXCLParser[Fragment] {
    def fromXML(x: Elem): Try[Fragment] =
      Try(
        x match {
          case e: Elem if (isXCLComment(e)) =>
            SXCLParserComment.fromXML(e) get
          case e: Elem if (isXCLTOSM(e)) =>
            SXCLParserTOSM.fromXML(e) get
        })
  }

  implicit object SXCLParserBasicExpressionLike
      extends SXCLParser[BasicExpressionLike] {
    def fromXML(x: Elem): Try[BasicExpressionLike] =
      Try(
        x match {
          case e: Elem if (isXCLBasicElement(e)) =>
            SXCLParserBasicExpression.fromXML(e) get
          case e: Elem if (isXCLFragment(e)) =>
            SXCLParserFragment.fromXML(e) get
        })

  }

  def isInXCLNamespace(x: Node): Boolean =
    ((x namespace) equals SCL.URI_XCL2)

  def isXCLComment(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Comment")

  def isXCLInterpretableName(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Name")

  def isXCLInterpretedName(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Data")

  def isXCLFunctionalTerm(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Apply")

  def isXCLSequenceMarker(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Marker")

  def isXCLName(x: Node): Boolean =
    isXCLInterpretableName(x) || isXCLInterpretedName(x)

  def isXCLNOSM(x: Node): Boolean =
    isXCLName(x) || isXCLSequenceMarker(x)

  def isXCLTerm(x: Node): Boolean =
    isXCLName(x) || isXCLFunctionalTerm(x)

  def isXCLTOSM(x: Node): Boolean =
    isXCLTerm(x) || isXCLSequenceMarker(x)

  def isXCLFragment(x: Node): Boolean =
    isXCLComment(x) || isXCLTOSM(x)
  def isXCLAtomicSentence(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Atom")

  def isXCLEquation(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Equal")

  def isXCLSimpleSentence(x: Node): Boolean =
    isXCLAtomicSentence(x) ||
      isXCLEquation(x)

  def isXCLBiconditional(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Biconditional")

  def isXCLImplication(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Implies")

  def isXCLConjunction(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "And")

  def isXCLDisjunction(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Or")

  def isXCLNegation(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Not")

  def isXCLBooleanSentence(x: Node): Boolean =
    isXCLBiconditional(x) ||
      isXCLImplication(x) ||
      isXCLConjunction(x) ||
      isXCLDisjunction(x) ||
      isXCLNegation(x)

  def isXCLExistential(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Exists")

  def isXCLUniversal(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Forall")

  def isXCLQuantifiedSentence(x: Node): Boolean =
    isXCLExistential(x) ||
      isXCLUniversal(x)

  def isXCLSentence(x: Node): Boolean =
    isXCLSimpleSentence(x) ||
      isXCLBooleanSentence(x) ||
      isXCLQuantifiedSentence(x)

  def isXCLTitling(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Titling")

  def isXCLInDiscourseStatement(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "In")

  def isXCLOutDiscourseStatement(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Out")

  def isXCLSchema(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Schema")

  def isXCLDiscourseStatement(x: Node): Boolean =
    isXCLInDiscourseStatement(x) ||
      isXCLOutDiscourseStatement(x)

  def isXCLStatement(x: Node): Boolean =
    isXCLTitling(x) ||
      isXCLDiscourseStatement(x) ||
      isXCLSchema(x)

  def isXCLTextConstruction(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Construct")

  def isXCLDomainRestriction(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Restrict")

  def isXCLImportation(x: Node): Boolean =
    isInXCLNamespace(x) && ((x label) equals "Import")

  def isXCLText(x: Node): Boolean =
    isXCLTextConstruction(x) ||
      isXCLDomainRestriction(x) ||
      isXCLImportation(x)

  def isXCLBasicElement(x: Node): Boolean =
    isXCLText(x) ||
      isXCLStatement(x) ||
      isXCLSentence(x)

  def isXCLCommentable(x: Node): Boolean =
    isXCLBasicElement(x) ||
      isXCLFunctionalTerm(x)

  def _tryCommentParse()(implicit ev: SXCLParser[Comment]): PartialFunction[Node, Try[Comment]] =
    { case x: Elem => (ev fromXML x) }

  def _commentParse: PartialFunction[Node, Comment] =
    SXCLParser._tryCommentParse() andThenPartial { case s: Success[Comment] => s get }

  def commentSetParse(x: Node): CommentSet =
    (x child) collect (_commentParse) toSet

  def _tryNameParse()(implicit ev: SXCLParser[Name]): PartialFunction[Node, Try[Name]] =
    { case x: Elem => (ev fromXML x) }
  def _nameParse()(implicit ev: SXCLParser[Name]): PartialFunction[Node, Name] =
    _tryNameParse andThenPartial { case s: Success[Name] => s get }

  def nameSeqParse(x: Node)(implicit ev: SXCLParser[Name]): List[Name] = (x child) collect _nameParse toList

  def _tryTosmParse()(implicit ev: SXCLParser[TermOrSequenceMarker]): PartialFunction[Node, Try[TermOrSequenceMarker]] =
    { case x: Elem => (ev fromXML x) }
  def _tosmParse(): PartialFunction[Node, TermOrSequenceMarker] =
    _tryTosmParse() andThenPartial { case s: Success[TermOrSequenceMarker] => s get }

  def tosmSeqParse(x: Node): TermSequence = (x child) collect _tosmParse() toList

  def tosmSetParse(x: Node)(implicit ev: SXCLParser[TermOrSequenceMarker]): TOSMSet = (x child) collect _tosmParse toSet

  def _tryTermParse(): PartialFunction[Node, Try[Term]] =
    { case x: Elem => (SXCLParserTerm fromXML x) }
  def _termParse(): PartialFunction[Node, Term] =
    _tryTermParse andThenPartial { case s: Success[Term] => s get }

  def termSeqParse(x: Node): List[Term] = (x child) collect _termParse toList

  def termSetParse(x: Node): TermSet = (x child) collect _termParse toSet

  def _trySentenceParse(): PartialFunction[Node, Try[Sentence]] =
    { case x: Elem => (SXCLParserSentence fromXML x) }
  def _sentenceParse(): PartialFunction[Node, Sentence] =
    _trySentenceParse andThenPartial { case s: Success[Sentence] => s get }

  def sentenceSeqParse(x: Node): List[Sentence] =
    (x child) collect _sentenceParse toList

  def sentenceSetParse(x: Node): SentenceSet =
    (x child) collect _sentenceParse toSet

  def _tryInterpretableNameParse(): PartialFunction[Node, Try[InterpretableName]] =
    { case x: Elem => (SXCLParserInterpretableName fromXML x) }
  def _bindingParse(): PartialFunction[Node, InterpretableName] =
    _tryInterpretableNameParse andThenPartial
      { case s: Success[InterpretableName] => s get }

  def bindingSetParse(x: Node): BindingSet = (x child) collect _bindingParse toSet

  def _trySequenceMarkerParse(): PartialFunction[Node, Try[SequenceMarker]] =
    { case x: Elem => (SXCLParserSequenceMarker fromXML x) }
  def _seqBindingParse(): PartialFunction[Node, SequenceMarker] =
    _trySequenceMarkerParse andThenPartial
      { case s: Success[SequenceMarker] => s get }

  def seqBindingSetParse(x: Node): SeqBindingSet = (x child) collect _seqBindingParse toSet

  def _trySentenceOrSchemaParse(): PartialFunction[Node, Try[SentenceOrSchema]] =
    { case x: Elem => (SXCLParserSentenceOrSchema fromXML x) }
  def _sentenceOrSchemaParse(): PartialFunction[Node, SentenceOrSchema] =
    _trySentenceOrSchemaParse andThenPartial
      { case s: Success[SentenceOrSchema] => s get }

  def sentenceOrSchemaSeqParse(x: Node): List[SentenceOrSchema] =
    (x child) collect _sentenceOrSchemaParse toList

  def _tryTextParse(): PartialFunction[Node, Try[Text]] =
    { case x: Elem => (SXCLParserText fromXML x) }
  def _textParse(): PartialFunction[Node, Text] = _tryTextParse andThenPartial {
    case s: Success[Text] => s get
  }
  def textSetParse(x: Node): Set[Text] = (x child) collect _textParse toSet

  def textSeqParse(x: Node): List[Text] = (x child) collect _textParse toList

  def _tryBasicExpressionParse(): PartialFunction[Node, Try[BasicExpression]] =
    { case x: Elem => (SXCLParserBasicExpression fromXML x) }
  def _basicExpressionParse(): PartialFunction[Node, BasicExpression] =
    _tryBasicExpressionParse andThenPartial
      { case s: Success[BasicExpression] => s get }

  def basicExpressionSetParse(x: Node): Set[BasicExpression] =
    (x child) collect _basicExpressionParse toSet

}
