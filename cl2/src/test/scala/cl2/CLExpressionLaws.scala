package cl2

import cl2._
import java.util.function._
import scala.compat.java8._
import org.scalatest._
import org.scalatest.matchers._
import org.scalacheck.Prop
import collection.JavaConversions._
import api4kbj.KnowledgeSourceLevel._
import cl2a._
import cl2array._
import CLGenerators._
import org.typelevel.discipline._
import scala.language.postfixOps

/**
 * Laws that must be obeyed by any `CL expression`.
 */
trait CLExpressionLaws extends Laws {
  
  val emptyComments = new CLCommentSetArray()

  def expressionUsesCLLanguageIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    (expression language) == CL.LANG
  }
  
  //TODO lift to api4kbj.Basic
  def expressionIsBasicIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    (expression isBasic) == true
  }
  
  //TODO lift to api4kbj.KnowledgeExpressionLike
  def expressionHasExpressionAbstractionLevelIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    (expression level) == EXPRESSION
  }

  //TODO lift to api4kbj.Immutable
  def expressionEqualsItselfIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    expression.equals(expression) == true
  }
  
  //TODO lift to api4kbj.Immutable
  def expressionEqualsCopyIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    expression.equals(expression.copy()) == true
  }
  
  //TODO lift to api4kbj.Immutable
  def expressionNotEqualNullIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    expression.equals(null) == false
  }
  
  //TODO lift to CLCommentable
  def expressionEqualsCopyWithEmptyCommentsInsertedIdentity: Prop = Prop.forAll { (expression: CLExpression) =>
    expression.equals(expression.insertComments(emptyComments)) == true
  }

  def expression:RuleSet = new RuleSet {
     def name = "expression"
     def bases: Seq[(String, Laws#RuleSet)] = Seq()
     def parents: Seq[RuleSet] = Seq()
     def props = Seq(
       ("A CLExpression is basic (has no structure)", expressionIsBasicIdentity),    
       ("A CLExpression uses the Common Logic language", expressionUsesCLLanguageIdentity),    
       ("A CLExpression has the EXPRESSION abstraction level", expressionHasExpressionAbstractionLevelIdentity),    
       ("A CL Expression equals itself", expressionEqualsItselfIdentity),   
       ("A CL Expression equals copy", expressionEqualsCopyIdentity),   
       ("A CL Expression is not equal null", expressionNotEqualNullIdentity),   
       ("A CL Expression equals copy with empty comments inserted", expressionEqualsCopyWithEmptyCommentsInsertedIdentity)   
     )
  }

}

trait CLSentenceLaws extends CLExpressionLaws {

  def sentenceNotEqualTermIdentity: Prop = Prop.forAll { ((sent: CLSentence), (term:CLTerm) ) =>
    sent.equals(term) == false
  }

   def sentence:RuleSet = new RuleSet {
     def name = "sentence"
      def bases: Seq[(String, Laws#RuleSet)] = Seq()
      def parents: Seq[RuleSet] = Seq(expression)
     def props = Seq(
       ("CL Sentences are Disjoint from CL Terms", sentenceNotEqualTermIdentity)    
     )
  }

}

object CLSentenceLaws extends CLSentenceLaws

object CLAtomicSentenceLaws extends CLSentenceLaws {
      
    def atomIdentityIdentity: Prop = Prop.forAll { (atom: CLAtomicSentence) =>{
    //val f1:Function[CLCommentSet, CLCommentSet] = {s:CLCommentSet => s}
    //val f2:Function[CLTerm, CLTerm] = {s:CLTerm => s}
    //val f3:Function[CLTermSequence, CLTermSequence] = {s:CLTermSequence => s}
    atom == atom.copy(
      {s:CLCommentSet => s}, {s:CLTerm => s}, {s:CLTermSequence => s}
      ) &&
    atom.hashCode() == atom.copy(
      {s:CLCommentSet => s}, {s:CLTerm => s}, {s:CLTermSequence => s}
      ).hashCode() &&
    atom.toString() == atom.copy(
      {s:CLCommentSet => s}, {s:CLTerm => s}, {s:CLTermSequence => s}
      ).toString()
    }
  }

  def atomComposeIdentity(
    atom: CLAtomicSentence,
    f1: Function[CLCommentSet, CLCommentSet],
    f2: Function[CLTerm, CLTerm],
    f3: Function[CLTermSequence, CLTermSequence],
    g1: Function[CLCommentSet, CLCommentSet],
    g2: Function[CLTerm, CLTerm],
    g3: Function[CLTermSequence, CLTermSequence]): Prop = Prop.forAll { (atom: CLAtomicSentence) =>
    atom.copy(f1, f2, f3).
      copy(g1, g2, g3) ==
      atom.copy(
        f1 compose g1,
        (f2 compose g2),
        (f3 compose g3))
  }
  
   def atom:RuleSet = new RuleSet {
     def name = "atom"
      def bases: Seq[(String, Laws#RuleSet)] = Seq()
      def parents: Seq[RuleSet] = Seq(sentence)
     def props = Seq(
       ("Identity Copy", atomIdentityIdentity)    
     )
  }
}
object CLBiconditionalLaws extends CLSentenceLaws {
      
    def biconditionalIdentityIdentity: Prop = Prop.forAll { (bicond: CLBiconditional) =>
    //val f1:Function[CLCommentSet, CLCommentSet] = {s:CLCommentSet => s}
    //val f2:Function[CLSentence, CLSentence] = {s:CLSentence => s}
    bicond.equals(bicond.copy()) &&  
    bicond == bicond.copy(
      {s:CLCommentSet => s}, {s:CLSentence => s}, {s:CLSentence => s}
      ) &&
    bicond.hashCode() == bicond.copy(
      {s:CLCommentSet => s}, {s:CLSentence => s}, {s:CLSentence => s}
      ).hashCode() &&
    bicond.toString() == bicond.copy(
      {s:CLCommentSet => s}, {s:CLSentence => s}, {s:CLSentence => s}
      ).toString()
  }

   def bicond:RuleSet = new RuleSet {
     def name = "bicond"
      def bases: Seq[(String, Laws#RuleSet)] = Seq()
      def parents: Seq[RuleSet] = Seq(sentence)
     def props = Seq(
       ("Identity Copy", biconditionalIdentityIdentity)    
     )
  }
   
}