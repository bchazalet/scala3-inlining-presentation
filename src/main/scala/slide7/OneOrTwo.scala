package slide7

import scala.compiletime.{ error, codeOf }

// I want to have compile-time validation for my sealed trait but it doesn't quite work.
// See https://stackoverflow.com/questions/67032401/scala-3-inlining-fails-a-very-simple-example

sealed trait OneOrTwo {
  val code: String
}

object OneOrTwo {

    case object One extends OneOrTwo {
      override val code : "one" = "one" 
    }

    case object Two extends OneOrTwo {
      override val code : "two" = "two"
    }

    inline def from(s: String): Option[OneOrTwo] = 
        inline s match {
            case One.code => Some(One)
            case Two.code => Some(Two)
            case _ => None
        }

    // would be ideal but doesn't work:
    inline def inlinedFrom3(s: String): OneOrTwo = {
        inline val res = from(s)
        res.getOrElse(error("can't make a OneOrTwo out of " + codeOf(s)))
    }
   
    // val test2 = OneOrTwo.inlinedFrom3("one") // doesn't compile -> can't make a OneOrTwo out of "one"
    // val test4 = OneOrTwo.inlinedFrom3("three") // doesn't compile as expected


}