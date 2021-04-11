package slide5

import scala.compiletime.{ error, codeOf }

// I want to have compile-time validation for my sealed trait but it doesn't quite work.
// See https://stackoverflow.com/questions/67032401/scala-3-inlining-fails-a-very-simple-example

sealed trait OneOrTwo {
  val code: String
}

object OneOrTwo {

    case object One extends OneOrTwo {
      override val code = "one" 
    }

    case object Two extends OneOrTwo {
      override val code  = "two"
    }

    def from(s: String): Option[OneOrTwo] = 
        s match {
            case One.code => Some(One)
            case Two.code => Some(Two)
            case _ => None
        }

    inline def inlinedFrom1(s: String): OneOrTwo = {
        inline s match {
            case "one" => One
            case "two" => Two
            case _ => error("can't make a OneOrTwo out of " + codeOf(s))
        } 
    }

    val test1 = OneOrTwo.inlinedFrom1("one") // compiles 
    // val test12 = OneOrTwo.inlinedFrom1("three") // doesn't compile as expected -> can't make a OneOrTwo out of "three"

}