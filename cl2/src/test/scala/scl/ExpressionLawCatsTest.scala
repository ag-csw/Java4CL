package scl

import cats._, cats.std.all._
import cats.std.AllInstances
import cats.syntax.AllSyntax
import org.scalatest.{ FunSuite, PropSpec, Matchers }
import org.scalatest.prop.PropertyChecks

import org.scalacheck.{ Arbitrary, Gen }
import org.scalacheck.Arbitrary.arbitrary

import scala.util.{ Failure, Success, Try }
import org.scalacheck.Prop._

/**
 * @author taraathan
 */
class ExpressionLawCatsTest extends CatsSuite {

  //checkAll("", (new discipline.ExpressionTests[?] {}).expression)

}
