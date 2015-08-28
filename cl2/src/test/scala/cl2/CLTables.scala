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

import org.scalatest.prop.TableDrivenPropertyChecks.Table

object CLTables {
  val badcodepoints =
    Table(
      ("int"),
      (0),
      (1),
      (2),
      (3),
      (4),
      (5),
      (6),
      (7),
      (8),
      (11),
      (12),
      (14),
      (15),
      (16),
      (17),
      (18),
      (18),
      (20),
      (21),
      (22),
      (22),
      (23),
      (24),
      (25),
      (26),
      (27),
      (28),
      (29),
      (30),
      (31))

}
