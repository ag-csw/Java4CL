package scl.example
import cats._
import cats.laws.discipline.FunctorTests

class EitherSpec extends CatsSpec {
  def is = s2"""
  Either[Int, ?] forms a functor                           $e1
  """
  type IntOrA[A] = Either[Int, A]

  def e1 = checkAll("Either[Int, Int]", FunctorTests[IntOrA].functor[Int, Int, Int])
}
