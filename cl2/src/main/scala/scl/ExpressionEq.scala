package scl

import cats.Eq
import cats.Show
import scala.language.{ postfixOps, existentials }
import SXCLManifester._

/**
 * @author taraathan
 */
object ExpressionEq {
  implicit class basicExpressionEq(x: BasicExpression) extends Eq[BasicExpression] {
    def eqv(a1: BasicExpression, a2: BasicExpression): Boolean = a1 equals a2
  }
  // TODO move into another object
  implicit class basicExpressionShow(x: BasicExpression) extends Show[BasicExpression] {
    def show(f: scl.BasicExpression): String = (f toXML) toString
  }
}
