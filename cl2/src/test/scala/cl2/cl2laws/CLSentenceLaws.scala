package cl2
package cl2.cl2laws

import cl2._
import cl2a._
import java.util.function._
import org.scalatest._
import org.scalatest.matchers._
import prop.PropertyChecks
import scala.collection.immutable.List
import org.scalacheck.Gen
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
  
    def atomIdentityIdentity: Prop = Prop.forAll { (atom: CLAtomicSentence) =>
    atom == atom.copy(
      (s => identity[CLCommentSequence](s)).asInstanceOf[UnaryOperator[CLCommentSequence]],
      (s => identity[CLTerm](s)).asInstanceOf[UnaryOperator[CLTerm]],
      (s => identity[CLTermSequence](s)).asInstanceOf[UnaryOperator[CLTermSequence]])
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