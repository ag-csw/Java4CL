package cl2
package cl2.cl2laws

import org.scalatest.FunSuite
import org.scalatest._
import org.scalatest.matchers._
import org.typelevel.discipline.scalatest._
import collection.JavaConversions._
import api4kbj.KnowledgeSourceLevel._
import cl2a._
import cl2array._

class CLAtomicSentenceLawTest extends FunSuite with Discipline {
  
  checkAll("CLAtomicSentence", CLAtomicSentenceLaws.atom /* put your own `RuleSet` here */)


}
