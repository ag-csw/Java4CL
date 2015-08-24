package cl2

import org.scalatest.FunSuite

import org.scalatest._
import org.scalatest.FunSuite
import org.scalatest.matchers._

import org.typelevel.discipline.scalatest._

import collection.JavaConversions._

import api4kbj.KnowledgeSourceLevel._

import cl2a._

import cl2array._

class CLAtomicSentenceLawTest extends FunSuiteLike with Discipline {
  
    checkAll("CLAtomicSentence", CLAtomicSentenceLaws.atom)


}
