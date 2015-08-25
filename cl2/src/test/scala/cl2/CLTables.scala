package cl2

import org.scalatest.prop.TableDrivenPropertyChecks._

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
      (31)
    ) 
   
}
