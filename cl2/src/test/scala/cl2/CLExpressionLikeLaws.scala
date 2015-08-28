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
 * Laws that must be obeyed by any `CL expression-like entity`.
 */
trait CLExpressionLikeLaws extends Laws {
  
  val emptyComments = new CLCommentSetArray()

  def expressionlikeUsesCLLanguageIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    (clentity language) == CL.LANG
  }
  
  //TODO lift to api4kbj.Basic
  def expressionlikeIsBasicIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    (clentity isBasic) == true
  }
  
  //TODO lift to api4kbj.KnowledgeExpressionLike
  def expressionlikeHasExpressionAbstractionLevelIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    (clentity level) == EXPRESSION
  }

  //TODO lift to api4kbj.Immutable
  def expressionlikeEqualsItselfIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    clentity.equals(clentity) == true
  }
  
  //TODO lift to api4kbj.Immutable
  def expressionlikeEqualsCopyIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    clentity.equals(clentity.copy()) == true
  }
  
  //TODO lift to api4kbj.Immutable
  def expressionlikeNotEqualNullIdentity: Prop = Prop.forAll { (clentity: CLExpressionLike) =>
    clentity.equals(null) == false
  }
  
  def expressionlike:RuleSet = new RuleSet {
     def name = "expressionlike"
     def bases: Seq[(String, Laws#RuleSet)] = Seq()
     def parents: Seq[RuleSet] = Seq()
     def props = Seq(
       ("A CLExpressionLike is basic (has no structure)", expressionlikeIsBasicIdentity),    
       ("A CLExpressionLike uses the Common Logic language", expressionlikeUsesCLLanguageIdentity),    
       ("A CLExpressionLike has the EXPRESSION abstraction level", expressionlikeHasExpressionAbstractionLevelIdentity),    
       ("A CLExpressionLike equals itself", expressionlikeEqualsItselfIdentity),   
       ("A CLExpressionLike equals copy", expressionlikeEqualsCopyIdentity),   
       ("A CLExpressionLike is not equal null", expressionlikeNotEqualNullIdentity)   
     )
  }

}

trait CLCommentLaws extends CLExpressionLikeLaws {

  def commentNotEqualTermIdentity: Prop = Prop.forAll { ((comment: CLComment), (term:CLTerm) ) =>
    comment.equals(term) == false
  }

   def comment:RuleSet = new RuleSet {
     def name = "comment"
      def bases: Seq[(String, Laws#RuleSet)] = Seq()
      def parents: Seq[RuleSet] = Seq(expressionlike)
     def props = Seq(
       ("CL Comments are Disjoint from CL Terms", commentNotEqualTermIdentity)    
     )
  }

}

object CLCommentLaws extends CLCommentLaws

