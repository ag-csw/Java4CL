package cl2

import org.scalacheck.Gen
import org.scalatest._
import org.scalatest.matchers._
import prop.PropertyChecks
import scala.collection.immutable.List

import collection.JavaConversions._
import java.util.Arrays;

import api4kbj.KnowledgeSourceLevel._
import cl2a._
import cl2array._

object CLGenerators {

  val clstringsymbolgen: Gen[String] = for (
      a <- Gen listOf (
        for (i <- Gen choose (9, Character MAX_CODE_POINT))
          yield String copyValueOf (Character toChars i))
    ) yield (a mkString) replaceAll ("[\\p{Cc}&&[^\r\n\t]]", "")

  val clstringinamegen: Gen[CLStringInterpretableName] = for (s <- clstringsymbolgen)
      yield new CLStringInterpretableName(s)
  
  val clliststringinamesymbolgen: Gen[List[String]] = 
      Gen listOf (clstringsymbolgen)
  
  val clliststringinamegen: Gen[List[CLStringInterpretableName]] = 
    Gen listOf (clstringinamegen)

  val clstringcommentgen: Gen[CLStringComment] =
    for (s <- clstringsymbolgen)
      yield new CLStringComment(s)

  val clliststringcommentgen: Gen[List[CLStringComment]] = 
    Gen listOf (clstringcommentgen)

  val clstringcommentsequencegen: Gen[CLCommentSequence] = 
    for (a <- Gen listOf (clstringcommentgen))
      yield new CLCommentSequenceArray(a.toArray[CLStringComment]: _*)
  
  val cltermsequencegen: Gen[CLTermSequence] = 
    for (a <- clliststringinamegen)
      yield new CLTermSequenceArray(a.toArray[CLStringInterpretableName]: _*)
  
  val clstringsequencemarkergen = 
       for (s<-clstringsymbolgen) 
         yield new CLStringSequenceMarker(s)

  
}
