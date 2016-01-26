package scl

import api4kbj.{
  BasicKnowledgeManifestation,
  KRRDialectType,
  KRRDialect,
  DialectTypeEnvironment
}
import scala.xml.Elem
import SXCLParser._

/**
 * @author taraathan
 */
object SCLManifestation {

}

trait BasicManifestation extends BasicKnowledgeManifestation

class EagerXCLBasicManifestation(elem: Elem) extends BasicManifestation {
  require(isXCLBasicElement(elem), "XCL element must have valid qualified name")

  /**
   * As seen from class EagerXCLBasicManifestation, the missing signatures are as follows.
   *  For convenience, these are usable as stub implementations.
   */
  def build[T](x$1: KRRDialectType[T]): T = ???
  def dialect(): KRRDialect = ???
  def environment(): DialectTypeEnvironment = ???
}
