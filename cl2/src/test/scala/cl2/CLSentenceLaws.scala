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

/**
 * Laws that must be obeyed by any `CL atomic sentence`.
 */
trait CLSentenceLaws extends Laws {
  
  val emptyComments = new CLCommentSequenceArray()

  def sentenceIsBasicIdentity: Prop = Prop.forAll { (sent: CLSentence) =>
    sent.isBasic() == true
  }
  
  def sentenceEqualsItselfIdentity: Prop = Prop.forAll { (sent: CLSentence) =>
    sent.equals(sent) == true
  }
  
  def sentenceNotEqualNullIdentity: Prop = Prop.forAll { (sent: CLSentence) =>
    sent.equals(null) == false
  }
  
  def sentenceNotEqualTermIdentity: Prop = Prop.forAll { ((sent: CLSentence), (term:CLTerm) ) =>
    sent.equals(term) == false
  }

  def sentenceEqualsCopyWithEmptyCommentsInsertedIdentity: Prop = Prop.forAll { (sent: CLSentence) =>
    sent.equals(sent.insertComments(emptyComments)) == true
  }

  def sentence:RuleSet = new RuleSet {
     def name = "sentence"
      def bases: Seq[(String, Laws#RuleSet)] = Seq()
      def parents: Seq[RuleSet] = Seq()
     def props = Seq(
       ("Sentence is Basic", sentenceIsBasicIdentity),    
       ("Sentence equals itself", sentenceEqualsItselfIdentity),   
       ("Sentence not equal null", sentenceNotEqualNullIdentity),   
       ("Sentence not equal term", sentenceNotEqualTermIdentity),   
       ("Sentence equals copy with empty comments inserted", sentenceEqualsCopyWithEmptyCommentsInsertedIdentity)   
     )
  }

}

object CLAtomicSentenceLaws extends CLSentenceLaws {
      
    def atomIdentityIdentity: Prop = Prop.forAll { (atom: CLAtomicSentence) =>
    atom == atom.copy(
      cl2.toJavaFunction {s => s},
      cl2.toJavaFunction {s => s},
      cl2.toJavaFunction {s => s}
      ) &&
    atom.hashCode() == atom.copy(
      cl2.toJavaFunction {s => s},
      cl2.toJavaFunction {s => s},
      cl2.toJavaFunction {s => s}
      ).hashCode()
  }

  def atomComposeIdentity(
    atom: CLAtomicSentence,
    f1: Function[CLCommentSequence, CLCommentSequence],
    f2: Function[CLTerm, CLTerm],
    f3: Function[CLTermSequence, CLTermSequence],
    g1: Function[CLCommentSequence, CLCommentSequence],
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