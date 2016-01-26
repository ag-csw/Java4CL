package scl
package discipline

import org.scalacheck.Arbitrary
import org.scalacheck.Prop
import Prop._

/**
 * @author taraathan
 */
trait ExpressionTests[T <: Expression] extends Laws {
  def laws: ExpressionLaws[T]
  def f = laws.expressionUsesLanguageIdentity _

  def expression: RuleSet = {
    new DefaultRuleSet(
      name = "expression",
      parent = None,
      "CL expression uses CL language identity" -> forAll(f))
  }

  object ExpressionTests {
    def apply[T <: Expression]: ExpressionTests[T] =
      new ExpressionTests[T] { def laws: ExpressionLaws[T] = ExpressionLaws[T] }
  }

}
