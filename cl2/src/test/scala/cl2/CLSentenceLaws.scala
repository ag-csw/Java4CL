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

}

object CLAtomicSentenceLaws extends CLSentenceLaws {
  
    val idCLCommentSequence:Function[CLCommentSequence, CLCommentSequence] = new Function[CLCommentSequence, CLCommentSequence] {
      override def apply(a: CLCommentSequence): CLCommentSequence = a
    }

    val idCLTerm = new Function[CLTerm, CLTerm] {
      override def apply(a: CLTerm): CLTerm = a
    }
    
    val idCLTermSequence = new Function[CLTermSequence, CLTermSequence] {
      override def apply(a: CLTermSequence): CLTermSequence = a
    }
    
    def atomIdentityIdentity: Prop = Prop.forAll { (atom: CLAtomicSentence) =>
    atom == atom.copy(
      idCLCommentSequence,
      idCLTerm,
      idCLTermSequence
      )
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
      def parents: Seq[RuleSet] = Seq()
     def props = Seq(
       ("Identity Copy", atomIdentityIdentity)    
     )
  }

}