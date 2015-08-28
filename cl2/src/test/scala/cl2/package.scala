// Copyright (C) 2015 Athan Services.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package cl2

import java.util.function.{ Function ⇒ JFunction, Predicate ⇒ JPredicate, BiPredicate }
import scala.language.implicitConversions

/**
 * @author taraathan
 * From http://www.michaelpollmeier.com/2014/10/12/calling-java-8-functions-from-scala/
 */
package object cl2 {

//usage example: `i: Int ⇒ 42`
implicit def toJavaFunction[A, B](f: Function1[A, B]) = new JFunction[A, B] {
  override def apply(a: A): B = f(a)
}

//usage example: `i: Int ⇒ true`
implicit def toJavaPredicate[A](f: Function1[A, Boolean]) = new JPredicate[A] {
  override def test(a: A): Boolean = f(a)
}

//usage example: `(i: Int, s: String) ⇒ true`
implicit def toJavaBiPredicate[A, B](predicate: (A, B) ⇒ Boolean) =
  new BiPredicate[A, B] {
    def test(a: A, b: B) = predicate(a, b)
  }
}